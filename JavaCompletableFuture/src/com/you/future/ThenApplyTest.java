package com.you.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenApplyTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> result =
                CompletableFuture.supplyAsync(ThenApplyTest::randomInteger)
                        .thenApply((i) -> i * 8);
        System.out.println(result.get());
    }

    public static Integer randomInteger() {
        return 10;
    }
}