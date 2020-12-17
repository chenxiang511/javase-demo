package com.cx.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author cx
 * @Date 2020/12/11 08:56
 * 多线程中第三种创建线程的方式，实现Callable接口
 * 实现Callable接口，可以有返回值，可以抛出异常
 */

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("====="+Thread.currentThread().getName()+"线程执行开始=====");
        Thread.sleep(6000);
        System.out.println("====="+Thread.currentThread().getName()+"线程执行结束=====");
        return 1024;
    }
}

/**
 * futureTask.get()放在主线程最后，否则会引起主线程阻塞后再执行自己
 */
public class CallableDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("=====主线程执行开始======");
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        new Thread(futureTask,"ThreadCallable").start();
        System.out.println("=====主线程业务开始======");
        Thread.sleep(3000);
        System.out.println("=====主线程业务结束======");
        System.out.println(futureTask.get());
        System.out.println("=====主线程执行结束======");
    }
}
