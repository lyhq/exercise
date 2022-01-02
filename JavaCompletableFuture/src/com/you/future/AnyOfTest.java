package com.you.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AnyOfTest {
    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            randomSleep();
            System.out.println("hello");
            return "hello";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            randomSleep();
            System.out.println("world");
            return "world";
        });
        CompletableFuture<Object> result = CompletableFuture.anyOf(future1, future2);
        System.out.println(result.get());
    }

    private static void randomSleep() {
        try {
            Thread.sleep(RANDOM.nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}