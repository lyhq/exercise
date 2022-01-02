package com.you.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AllOfTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("world");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        CompletableFuture<Void> result = CompletableFuture.allOf(future1, future2);
        // 阻塞， 直到future1、future2都执行完成
        Void unused = result.get();
        System.out.println(unused);
    }
}