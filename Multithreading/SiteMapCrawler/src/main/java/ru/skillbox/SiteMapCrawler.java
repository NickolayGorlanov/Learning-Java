package ru.skillbox;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.RecursiveTask;
import java.util.HashSet;

public class SiteMapCrawler extends RecursiveTask<String> {
    private String url; // базовый URL, от которого начинается просмотр
    private ArrayList<String> allLinks; // список всех ссылок

    public SiteMapCrawler(String url, ArrayList<String> allLinks) {
        this.url = url;
        this.allLinks = allLinks;
    }

    public SiteMapCrawler(String url) {
        this.url = url;
        this.allLinks = new ArrayList<>();
    }

    @Override
    protected String compute() {
        String tabulate = StringUtils.repeat("\t", url.lastIndexOf("/") != url.length() - 1 ? StringUtils.countMatches(url, "/") - 2 : StringUtils.countMatches(url, "/") - 3);

        // Создаем StringBuilder, в который будем собирать ссылки
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(tabulate); // Добавляем отступ
        stringBuilder.append(url); // Добавляем URL
        stringBuilder.append("\n"); // Переходим на новую строку

        // Создаем сет, в котором будем хранить наши задачи
        Set<SiteMapCrawler> allTask = new HashSet<>();

        try {
            Thread.sleep(150); // чтобы не заблокировали
            Document document = Jsoup.connect(url).ignoreContentType(true).maxBodySize(0).get();
            Elements elements = document.select("a[href]");

            for (Element element : elements) {
                String attributeUrl = element.absUrl("href");
                System.out.println(attributeUrl);

                if (attributeUrl.startsWith(url) && !attributeUrl.contains("#") && !allLinks.contains(attributeUrl)) {
                    SiteMapCrawler task = new SiteMapCrawler(attributeUrl, allLinks);
                    task.fork();
                    allTask.add(task); // Добавляем задачу в сет
                    allLinks.add(attributeUrl); // Добавляем URL в список всех ссылок
                }
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        // Получаем результат задач
        for (SiteMapCrawler link : allTask) {
            stringBuilder.append(link.join()); // Добавляем результат задачи
        }

        return stringBuilder.toString();
    }
}
