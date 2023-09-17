package ru.skillbox;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Set<String> visitedUrls = new HashSet<>(); // Создаем множество посещенных URL

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SiteMapCrawler crawler = new SiteMapCrawler("https://skillbox.com/", 0, startTime, visitedUrls); // Обновляем передачу URL и множества
        forkJoinPool.invoke(crawler);

        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);
        executorService.schedule(() -> {
            // Прерываем выполнение всех задач в пуле
            forkJoinPool.shutdownNow();
            try {

                if (!forkJoinPool.awaitTermination(1, TimeUnit.MINUTES)) {
                    logger.log(Level.SEVERE, "Timeout: Not all tasks completed.");
                }
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, "Error waiting for tasks to complete: " + e.getMessage());
            } finally {
                // Завершаем выполнение ScheduledExecutorService
                executorService.shutdown();
                System.exit(0); // Выходим из программы после завершения
            }
        }, 1, TimeUnit.MINUTES);
    }
}
