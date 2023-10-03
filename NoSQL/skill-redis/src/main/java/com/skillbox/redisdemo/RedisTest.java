package com.skillbox.redisdemo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.log;
import static java.lang.System.out;

public class RedisTest {

    private static final int DELETE_SECONDS_AGO = 2;
    private static final int RPS = 500;
    private static final int SLEEP = 1000; // Изменено на 1 секунду

    private static final SimpleDateFormat DF = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        // Уберем создание нового контейнера Redis
        // startRedisContainer();

        // Используйте хост и порт для подключения к Redis
        RedisStorage redis = new RedisStorage();
        redis.init();

        List<Integer> usersList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            usersList.add(i);
        }

        Random random = new Random();
        int paidUserInterval = 10; // Каждый 10-й пользователь оплачивает услугу
        int currentUserIndex = 0;

        // Бесконечный цикл
        while (true) {
            int userIdToDisplay = usersList.get(currentUserIndex);
            out.println("— На главной странице показываем пользователя " + userIdToDisplay);

            // Проверка на оплату услуги
            if (random.nextInt(paidUserInterval) == 0) {
                int paidUserId = usersList.get(random.nextInt(usersList.size()));
                out.println("> Пользователь " + paidUserId + " оплатил платную услугу");
            }

            currentUserIndex = (currentUserIndex + 1) % usersList.size(); // Переход к следующему пользователю

            redis.logPageVisit(userIdToDisplay);

            redis.deleteOldEntries(DELETE_SECONDS_AGO);
            int usersOnline = redis.calculateUsersNumber();
            log(usersOnline);

            Thread.sleep(SLEEP);
        }

        // Уберем остановку контейнера Redis
        // stopRedisContainer();
    }

    /*
    private static void startRedisContainer() {
        //  код для запуска Docker-контейнера Redis
    }
    */

    /*
    private static void stopRedisContainer() {
        //  код для остановки Docker-контейнера Redis
    }
    */
}

