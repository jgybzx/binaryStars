package com.jgybzx.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jgybzx
 * @date 2021/1/12 16:13
 * @description 使用线程池
 */
public class MyThread {
    public static void main(String[] args) {
        int count = 10;
        for (int i1 = 0; i1 < count; i1++) {
            new Thread(() -> {
                for (int i = 0; i < count; i++) {
                    System.out.println(Thread.currentThread().getName() + "::::" + i);
                }
            }).start();
        }
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < count; i++) {
            executorService.submit(new Thread(() -> {
                for (int i1 = 0; i1 < count; i1++) {
                    System.out.println(Thread.currentThread().getName() + "::::" + i1);
                }
            }));
        }
    }
}
