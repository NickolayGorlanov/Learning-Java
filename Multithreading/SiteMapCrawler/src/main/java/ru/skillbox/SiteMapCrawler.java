package ru.skillbox;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

public class SiteMapCrawler extends RecursiveTask<String> {
    private static final int MAX_DEPTH = 5; // Максимальная глубина поиска ссылок
    private static final long TIMEOUT = 120000; // Максимальное время выполнения в миллисекундах

    private final String url;
    private final int depth;
    private final long startTime;
    private final Set<String> visitedUrls; // Храним посещенные URL

    public SiteMapCrawler(String url, int depth, long startTime, Set<String> visitedUrls) {
        this.url = url;
        this.depth = depth;
        this.startTime = startTime;
        this.visitedUrls = visitedUrls;
    }

    @Override
    protected String compute() {
        if (depth > MAX_DEPTH || System.currentTimeMillis() - startTime > TIMEOUT || visitedUrls.contains(url)) {
            return "";
        }

        visitedUrls.add(url); // Добавляем посещенный URL в список

        try {
            Connection connection = Jsoup.connect(url);
            connection.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
            Document doc = connection.get();

            Set<String> childUrls = new HashSet<>();
            Elements links = doc.select("a[href]");

            for (Element link : links) {
                String childUrl = link.absUrl("href");
                if (isValidUrl(childUrl)) {
                    childUrls.add(childUrl);
                }
                Thread.sleep(100);
            }

            StringBuilder result = new StringBuilder();
            result.append(url).append("\n");

            if (!childUrls.isEmpty()) {
                List<SiteMapCrawler> tasks = new ArrayList<>();
                for (String childUrl : childUrls) {
                    SiteMapCrawler task = new SiteMapCrawler(childUrl, depth + 1, startTime, visitedUrls);
                    task.fork();
                    tasks.add(task);
                }

                for (SiteMapCrawler task : tasks) {
                    result.append(task.join());
                }
            }

            return result.toString();
        } catch (IOException e) {
            System.err.println("Error fetching URL: " + url);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Переустановка флага прерывания
            System.err.println("Thread interrupted: " + e.getMessage());
        }

        return "";
    }

    private boolean isValidUrl(String url) {
        return url.startsWith("https://skillbox.ru/") && !url.contains("#");
    }
}