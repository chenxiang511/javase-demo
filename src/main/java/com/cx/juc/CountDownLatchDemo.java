package com.cx.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @Author cx
 * @Date 2020/12/15 16:20
 * 闭锁（计数器）
 * 实例：模拟班级下课，所有同学离开后，班长关门走人
 **/

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        //模拟六个同学离开
        for (int i=0;i<6;i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t离开教室");
                //离开时计数
                countDownLatch.countDown();
            },String.valueOf(i+1)).start();
        }
        //班长关门,等待计数完毕
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t关门走人");
    }
}
