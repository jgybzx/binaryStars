package com.jgybzx.thread;

import java.util.concurrent.*;

/**
 * @author jgybzx
 * @date 2021/1/12 14:37
 * @description 线程练习
 */
public class MyRunnable implements Callable<Integer> {
    private int x;
    private int y;

    public MyRunnable(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return x + y;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        MyRunnable myRunnable = new MyRunnable(10, 20);
        for (int i = 0; i < 10; i++) {
            Future<Integer> submit = executorService.submit(myRunnable);
            System.out.println(submit.get());
        }
        executorService.shutdown();
    }


}
