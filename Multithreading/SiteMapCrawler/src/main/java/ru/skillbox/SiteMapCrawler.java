package ru.skillbox;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.RecursiveAction;

public class SiteMapCrawler extends RecursiveAction {
    private static final int MAX_DEPTH = 2; // Максимальная глубина поиска ссылок
    private static final long TIMEOUT = 120000; // Максимальное время выполнения в миллисекундах
    private static final String outputFile = "sitemap.txt";

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
    protected void compute() {
        if (depth > MAX_DEPTH || System.currentTimeMillis() - startTime > TIMEOUT || visitedUrls.contains(url)) {
            return;
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

            writeToFile(url, depth);
            if (!childUrls.isEmpty()) {
                SiteMapCrawler[] tasks = new SiteMapCrawler[childUrls.size()];
                int i = 0;
                for (String childUrl : childUrls) {
                    tasks[i] = new SiteMapCrawler(childUrl, depth + 1, startTime, visitedUrls);
                    i++;
                }

                // Вызывайте invokeAll только для созданных задач
                invokeAll(tasks);
            }
        } catch (IOException e) {
            System.err.println("Error fetching URL: " + url);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Переустановка флага прерывания
            System.err.println("Thread interrupted: " + e.getMessage());
        }
    }

    private boolean isValidUrl(String url) {
        return url.startsWith("https://skillbox.com/") && !url.contains("#");
    }

    private void writeToFile(String url, int depth) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))) {
            for (int i = 0; i < depth; i++) {
                writer.write("\t");
            }
            writer.write(url + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
