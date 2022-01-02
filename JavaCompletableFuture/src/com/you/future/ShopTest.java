package com.you.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/***
 * ShopTest 类
 *
 * @author: YangRun
 * @date: 1/2/2022
 */
public class ShopTest {

    private static List<Shop> shopList = Arrays.asList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll")
    );

    /**
     * 同步方法
     *
     * @param product
     * @return
     */
    private static List<String> findPriceSync(String product) {
        // %s: 字符串
        // %.2f: 单精度浮点保留两位小数
        return shopList.stream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice(product)))  //格式转换
                .collect(Collectors.toList());
    }

    /**
     * 异步方法
     *
     * @param product
     * @return
     */
    private static List<String> findPriceAsync(String product) {
        List<CompletableFuture<String>> completableFutureList = shopList.stream()
                //转异步执行
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f",
                                shop.getName(), shop.getPrice(product))))  //格式转换
                .collect(Collectors.toList());

        return completableFutureList.stream()
                .map(CompletableFuture::join)  //获取结果不会抛出异常
                .collect(Collectors.toList());
    }

    /**
     * 传统Future方式
     *
     * @param product
     * @return
     */
    private static List<String> findPriceFutureAsync(String product) {
        ExecutorService es = Executors.newCachedThreadPool();
        List<Future<String>> futureList = shopList.stream().map(
                shop -> es.submit(
                        () -> String.format(
                                "%s price is %.2f",
                                shop.getName(),
                                shop.getPrice(product)
                        )
                )
        ).collect(Collectors.toList());

        return futureList.stream()
                .map(f -> {
                    String result = null;
                    try {
                        result = f.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }

                    return result;
                }).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ShopTest.findPriceSync("ABC");
        long end = System.currentTimeMillis();
        System.out.println("Find Price Sync Done in " + (end - start));

        long start2 = System.currentTimeMillis();
        ShopTest.findPriceAsync("ABC");
        long end2 = System.currentTimeMillis();
        System.out.println("Find Price Async Done in " + (end2 - start2));

        long start3 = System.currentTimeMillis();
        ShopTest.findPriceFutureAsync("ABC");
        long end3 = System.currentTimeMillis();
        System.out.println("Find Price FutureAsync Done in " + (end3 - start3));
    }
}
