package com.anand.springproject.library.redis;

import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisDataException;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Java Redis Client
 * Reference: https://www.baeldung.com/jedis-java-redis-client-library
 *
 * String
 * List
 * Set
 * Map
 * Sorted Sets
 * Transactions
 * Pipeline
 * Pub Sub
 *
 * Connection Pool
 * Redis Cluster
 *
 */
public class JedisClientExample {

    /**
     *
     * @param args
     */
    public static void main(String []args){

        Jedis jedis = new Jedis();

        // For Redis Cluster, connect with one of master node
        // use the jedisCluster resource as if it was a normal Jedis resource
        // Transactions and Pipelines are not supported due to multiple instances (Atomicity & Thread safety not guaranteed).

        JedisCluster jedisCluster;
        try {
            jedisCluster = new JedisCluster(new HostAndPort("localhost", 6379));
        } catch (JedisDataException e) {
            System.out.println(e.getMessage());
        }

        /*
         * String
         */
        jedis.set("key1", "value1");
        String cachedResponse = jedis.get("key1");


        /*
         * List
         */
        jedis.lpush("queue#tasks", "firstTask");
        jedis.lpush("queue#tasks", "secondTask");

        String task = jedis.rpop("queue#tasks");


        /*
         * Set
         */
        jedis.sadd("nicknames", "nickname#1");
        jedis.sadd("nicknames", "nickname#2");
        jedis.sadd("nicknames", "nickname#1");


        /*
         * Map
         */
        jedis.hset("user#1", "name", "Anker");
        jedis.hset("user#1", "job", "engineer");

        String name = jedis.hget("user#1", "name");

        Map<String, String> fields = jedis.hgetAll("user#1");
        String job = fields.get("job");


        /*
         * Sorted Sets
         */
        Map<String, Double> scores = new HashMap<>();
        scores.put("PlayerOne", 3000.0);
        scores.put("PlayerTwo", 1500.0);
        scores.put("PlayerThree", 8200.0);

        scores.forEach((player, score) -> {
            jedis.zadd("ranking", score, player);
        });

        String player = jedis.zrevrange("ranking", 0, 1).iterator().next();
        long rank = jedis.zrevrank("ranking", "PlayerOne");

        /*
         * Transactions
         */
        String friendsPrefix = "friends#";
        String userOneId = "4352523";
        String userTwoId = "5552321";

        Transaction t = jedis.multi();

        t.sadd(friendsPrefix + userOneId, userTwoId);
        t.sadd(friendsPrefix + userTwoId, userOneId);
        t.watch("friends#deleted#" + userOneId);
        t.exec();


        /*
         * Pipeline
         */
        userOneId = "4352523";
        userTwoId = "4849888";

        Pipeline p = jedis.pipelined();
        p.sadd("searched#" + userOneId, "India");
        p.zadd("ranking", 126, userOneId);
        p.zadd("ranking", 325, userTwoId);
        Response<Boolean> pipeExists = p.sismember("searched#" + userOneId, "India");
        Response<List<String>> pipeRanking = p.zrange("ranking", 0, -1);
        p.sync();

        Boolean exists = pipeExists.get();
        List<String> ranking = pipeRanking.get();


        /*
         * Pub Sub
         */
        new Thread(() ->
            jedis.subscribe(new JedisPubSub() {
                @Override
                public void onMessage(String channel, String message) {
                    // handle message
                    System.out.println(message);

                    unsubscribe();
                }
            }, "my_channel")
        ).start();

        new Jedis().publish("my_channel", "test message 1");
        new Jedis().publish("my_channel", "test message 2");

        /*
         * Connection Pool
         */
        final JedisPoolConfig poolConfig = buildPoolConfig();
        JedisPool jedisPool = new JedisPool(poolConfig, "localhost");

        try (Jedis jedis1 = jedisPool.getResource()) {
            // do operations with jedis resource
            // with try resource jedis instance is auto closed implemented by AutoCloseable
            jedis1.set("key2", "value2");
        }

        System.out.println("Complete!");
    }

    private static JedisPoolConfig buildPoolConfig() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(32);
        poolConfig.setMaxIdle(32);
        poolConfig.setMinIdle(8);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);

        return poolConfig;
    }
}
