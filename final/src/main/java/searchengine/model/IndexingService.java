package searchengine.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

@Service
public class IndexingService {
    private final SiteConfigurations siteConfigurations;
    private final DatabaseService databaseService;
    private final ForkJoinPool forkJoinPool;


    private static final Logger logger = LoggerFactory.getLogger(IndexingService.class);

    @Autowired
    public IndexingService(SiteConfigurations siteConfigurations, DatabaseService databaseService) {
        this.siteConfigurations = siteConfigurations;
        this.databaseService = databaseService;
        this.forkJoinPool = new ForkJoinPool(); // Создаем ForkJoinPool
    }

    public boolean startIndexing() {
        List<SiteConfig> sites = siteConfigurations.getSites();

        for (SiteConfig siteConfig : sites) {
            // Удаляем все имеющиеся данные по этому сайту
            clearSiteData(siteConfig.getUrl());

            // Создаем запись о сайте со статусом INDEXING
            Site site = createSiteRecord(siteConfig);

            try {
                // Обход и индексация страниц сайта
                indexSitePages(site, siteConfig.getUrl());
            } catch (IOException e) {
                // Если произошла ошибка, изменяем статус на FAILED и сохраняем ошибку
                markSiteAsFailed(site, e.getMessage());
                logger.error("Ошибка индексации сайта: {}", e.getMessage()); // Логируйте ошибку
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return true;
    }

    public boolean stopIndexing() {
        // Останавливаем все активные потоки индексации
        forkJoinPool.shutdownNow();
        return true;
    }

    private Site createSiteRecord(SiteConfig siteConfig) {
        Site site = new Site();
        site.setUrl(siteConfig.getUrl());
        site.setName(siteConfig.getName());
        site.setStatus(SiteStatus.INDEXING);
        // Устанавливаем дату и время
        site.setStatusTime(LocalDateTime.now());
        databaseService.saveSite(site);
        return site;
    }

    private void markSiteAsFailed(Site site, String errorMessage) {
        site.setStatus(SiteStatus.FAILED);
        site.setLastError(errorMessage);
        // Обновляем дату и время
        site.setStatusTime(LocalDateTime.now());
        databaseService.updateSite(site);
    }

    private void indexSitePages(Site site, String baseUrl) throws IOException, InterruptedException {
        RecursiveAction action = new IndexSiteAction(site, baseUrl);
        forkJoinPool.invoke(action);
    }

    private void clearSiteData(String baseUrl) {
        // Удаляем все данные по сайту (записи из таблиц site и page)
        databaseService.deleteSiteData(baseUrl);
    }

    public boolean stop() {
        return false;
    }

    private class IndexSiteAction extends RecursiveAction {
        private final Site site;
        private final String baseUrl;

        IndexSiteAction(Site site, String baseUrl) {
            this.site = site;
            this.baseUrl = baseUrl;
        }

        @Override
        protected void compute() {
            try {
                Thread.sleep(500); // Задержка между запросами
                Document document = Jsoup.connect(baseUrl)
                        .userAgent("HeliontSearchBot")
                        .referrer("http://www.google.com")
                        .ignoreContentType(true)
                        .maxBodySize(0)
                        .get();
                String htmlContent = document.html();

                // Сохраняем главную страницу в базу данных
                savePage(site, baseUrl, 200, htmlContent);

                // Обходим ссылки на другие страницы сайта
                Elements links = document.select("a[href]");
                for (Element link : links) {
                    String href = link.attr("abs:href");
                    if (!isVisited(href)) {
                        indexSitePages(site, href);
                    }
                }

                // Обновляем дату и время статуса
                site.setStatusTime(LocalDateTime.now());
                site.setStatus(SiteStatus.INDEXED);
                databaseService.updateSite(site);

            } catch (IOException | InterruptedException e) {
                // Если произошла ошибка, изменяем статус на FAILED и сохраняем ошибку
                markSiteAsFailed(site, e.getMessage());
                logger.error("Ошибка индексации сайта: {}", e.getMessage());
            }
        }

        private boolean isVisited(String url) {
            // Проверяем, заходил ли уже сервис по этой ссылке
            return databaseService.isPageVisited(url);
        }
    }

    private void savePage(Site site, String path, int statusCode, String content) {
        Page page = new Page();
        page.setSite(site);
        page.setPath(path);
        page.setCode(statusCode);
        page.setContent(content);
        // Вместо использования репозитория, используйте databaseService.savePage(page);
        databaseService.savePage(page);
    }
}
