package com.you.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture 测试
 */
public class AcquireResultTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //getNow方法测试
        CompletableFuture<String> cp1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(60 * 1000 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "hello world";
        });

        System.out.println(cp1.getNow("hello h2t"));

        //join方法测试
//        CompletableFuture<Integer> cp2 = CompletableFuture.supplyAsync((() -> 1 / 0));
//        System.out.println(cp2.join());
//
//        //get方法测试
//        CompletableFuture<Integer> cp3 = CompletableFuture.supplyAsync((() -> 1 / 0));
//        System.out.println(cp3.get());

        CompletableFuture completableFuture = new CompletableFuture();
        new Thread(() -> {
            try {
                //doSomething，调用complete方法将其他方法的执行结果记录在completableFuture对象中
                completableFuture.complete(null);
                System.out.println("===========================");
                System.out.println(completableFuture.get());
            } catch (Exception e) {
                //异常处理
                System.out.println("-----------");
                completableFuture.completeExceptionally(e);
            }
        }).start();
    }
}