package com.you.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ThenAcceptTest {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(ThenAcceptTest::getList)
                .thenAccept(
                        strList -> strList.forEach(System.out::println)
                );
    }

    public static List<String> getList() {
        return Arrays.asList("a", "b", "c");
    }
}