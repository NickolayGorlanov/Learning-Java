package com.skillbox.redisdemo;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Date;
import java.util.Random;

import static java.lang.System.out;

public class RedisStorage {

    private RedissonClient redisson;
    private RKeys rKeys;
    private RScoredSortedSet<String> onlineUsers;
    private final static String KEY = "ONLINE_USERS";

    public RedisStorage() {
    }

    private double getTs() {
        return new Date().getTime() / 1000;
    }

    public void init() {
        Config config = new Config();
        String redisHost = "localhost"; //  хост Redis
        int redisPort = 6379; //  порт Redis
        config.useSingleServer().setAddress("redis://" + redisHost + ":" + redisPort);
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(exc.getMessage());
        }
        rKeys = redisson.getKeys();
        onlineUsers = redisson.getScoredSortedSet(KEY);
        rKeys.delete(KEY);
    }

    void shutdown() {
        redisson.shutdown();
    }

    void logPageVisit(int user_id) {
        Random random = new Random();
        double score = getTs() + random.nextDouble();
        onlineUsers.add(score, String.valueOf(user_id));
    }

    void deleteOldEntries(int secondsAgo) {
        onlineUsers.removeRangeByScore(0, false, getTs() - secondsAgo, false);
    }

    int calculateUsersNumber() {
        return onlineUsers.count(Double.NEGATIVE_INFINITY, false, Double.POSITIVE_INFINITY, false);
    }

}
