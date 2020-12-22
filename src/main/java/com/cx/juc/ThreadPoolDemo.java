package com.cx.juc;

import java.util.concurrent.*;

/**
 * @Author cx
 * @Date 2020/12/18 13:15
 * 线程池
 **/

public class ThreadPoolDemo {

    public static void main(String[] args) {
        //固定线程数的创建方式，阻塞队列长度为int取值范围，可能造成大量线程阻塞有OOM风险
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //固定一个线程，阻塞队列长度为int取值范围，可能造成大量线程阻塞有OOM风险
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //核心线程数为0，根据业务扩容，最大可以扩容int取值范围个线程，大量线程的创建也有OOM的风险
        //ExecutorService threadPool = Executors.newCachedThreadPool();
        //同上，无限扩容也会造成OOM
        //ExecutorService threadPool = Executors.newScheduledThreadPool(5);

        //使用ThreadPoolExecutor方式创建一个线程池，可以对线程池的参数进行调整，根据硬件性能，业务需要创建最合适的线程池
        System.out.println("CPU核心数量："+Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors()+1,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        //业务代码
        try {
            for (int i=0;i<10;i++){
                threadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
