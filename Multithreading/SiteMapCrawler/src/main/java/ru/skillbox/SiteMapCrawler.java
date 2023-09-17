package ru.skillbox;

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
    static final String baseUrl = "https://stackoverflow.com/";
    private static final String outputFile = "sitemap.txt";
    private static final int MAX_DEPTH = 5; // Максимальная глубина поиска ссылок

    private final String url;
    private final int depth;
    private final long startTime;

    public SiteMapCrawler(String url, int depth, long startTime) {
        this.url = url;
        this.depth = depth;
        this.startTime = startTime;
    }

    @Override
    protected void compute() {
        if (depth > MAX_DEPTH || System.currentTimeMillis() - startTime > 120000) {
            return;
        }

        try {
            Document doc = Jsoup.connect(url).get();
            Set<String> childUrls = new HashSet<>();

            Elements links = doc.select("a[href]");
            for (Element link : links) {
                String childUrl = link.attr("abs:href");
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
                    tasks[i] = new SiteMapCrawler(childUrl, depth + 1, startTime);
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
        return url.startsWith(baseUrl) && !url.contains("#");
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
