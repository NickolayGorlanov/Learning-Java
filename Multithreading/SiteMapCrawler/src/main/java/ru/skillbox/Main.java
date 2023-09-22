package ru.skillbox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        // Создаем список для всех ссылок
        ArrayList<String> allLinks = new ArrayList<>();

        // Создаем экземпляр SiteMapCrawler и передаем базовый URL и список ссылок
        SiteMapCrawler crawler = new SiteMapCrawler("https://lenta.ru/", allLinks);

        // Создаем ForkJoinPool
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        // Получаем результат выполнения SiteMapCrawler
        String siteMap = forkJoinPool.invoke(crawler);

        try {
            FileWriter writer = new FileWriter(new File("site_map.txt"));
            writer.write(siteMap);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Завершаем выполнение ForkJoinPool
        forkJoinPool.shutdown();

        // Ждем завершения всех задач
        try {
            if (!forkJoinPool.awaitTermination(1, TimeUnit.MINUTES)) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Timeout: Not all tasks completed.");
            }
        } catch (InterruptedException e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error waiting for tasks to complete: " + e.getMessage());
        }
    }
}
