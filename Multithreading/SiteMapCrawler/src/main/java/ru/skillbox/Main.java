package ru.skillbox;

import java.util.concurrent.ForkJoinPool;

import static ru.skillbox.SiteMapCrawler.baseUrl;

public class Main {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SiteMapCrawler crawler = new SiteMapCrawler(baseUrl, 0);
        forkJoinPool.invoke(crawler);
    }
}


