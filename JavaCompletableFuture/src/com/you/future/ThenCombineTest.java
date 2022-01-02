package com.you.future;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenCombineTest {
    private static final Random random = new Random();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> result =
                CompletableFuture.supplyAsync(ThenCombineTest::randomInteger).thenCombine(
                        CompletableFuture.supplyAsync(ThenCombineTest::randomInteger), (i, j) -> i * j
                );

        System.out.println(result.get());
    }

    public static Integer randomInteger() {
        int nextInt = random.nextInt(100);
        System.out.println(nextInt);
        return nextInt;
    }
}