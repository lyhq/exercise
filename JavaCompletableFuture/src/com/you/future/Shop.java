package com.you.future;

import java.util.Random;

/***
 * 店铺 类
 *
 * @author: YangRun
 * @date: 1/2/2022
 *
 * 查询商品的价格为同步方法，并通过sleep方法模拟其他操作。
 * 这个场景模拟了当需要调用第三方API，但第三方提供的是同步API，
 * 在无法修改第三方API时如何设计代码调用提高应用的性能和吞吐量，
 * 这时候可以使用CompletableFuture类（Completable是Future接口的实现类，在JDK1.8中引入）
 */
public class Shop {

    private String name;

    private final Random random = new Random();

    /**
     * 根据产品名查找价格：此为同步方法
     */
    public double getPrice(String product) {
        return calculatePrice(product);
    }

    /**
     * 计算价格
     *
     * @param product
     * @return
     */
    private double calculatePrice(String product) {
        // 模拟耗时
        delay();
        // random.nextDouble()随机返回折扣
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    /**
     * 通过睡眠模拟其他耗时操作
     */
    private void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public Shop(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Shop shop = new Shop("ABC");
        double price = shop.getPrice("12");
        System.out.println(price);
    }
}
