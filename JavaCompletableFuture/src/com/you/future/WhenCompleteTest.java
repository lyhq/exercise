package com.you.future;

import java.util.concurrent.CompletableFuture;

public class WhenCompleteTest {
    public static void main(String[] args) {
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> String.valueOf(null));
        CompletableFuture<String> cf2 = cf1.whenComplete((v, e) ->
                System.out.printf("value:%s, exception:%s", v, e));
        System.out.println(cf2.join());
    }
}