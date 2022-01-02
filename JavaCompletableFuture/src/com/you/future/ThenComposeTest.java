package com.you.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenComposeTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> result =
                CompletableFuture.supplyAsync(ThenComposeTest::getInteger)
                        .thenCompose(i -> CompletableFuture.supplyAsync(() -> expandValue(i)));
        System.out.println(result.get());
    }

    private static int getInteger() {
        return 666;
    }

    private static int expandValue(int num) {
        return num * 10;
    }
}