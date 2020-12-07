package com.cx.juc.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author cx
 * @Date 2020/12/4 13:01
 * 线程之前交互: 现在四个线程，可以操作初始值未0的一个变量，
 * 实现两个线程对改变量加1，两个线程对改变量减1
 * 实现交替，10轮，变量的初始值为0
 **/

class ResourceA{//资源类,使用synchronized实现

    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        //判断
        while (number != 0){
            this.wait();
        }
        //干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //通知
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        //判断
        while (number == 0){
            this.wait();
        }
        //干活
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //通知
        this.notifyAll();
    }
}

class ResourceB{//资源类，使用ReentrantLock

    private int number = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        //判断
        try{
            while ( number != 0){
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //通知
            condition.signalAll();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void decrement() {
        lock.lock();
        //判断
        try{
            while ( number == 0){
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //通知
            condition.signalAll();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
/**
 * 1 高聚合低耦合的前提下，线程操作资源类
 * 2 判断/干活、通知
 * 3 多线程交互中，必须要防止多线程的虚假唤醒，也即（判断只能用while，不能用if）
 */
public class ThreadWaitNotifyDemo {

    public static void main(String[] args) {
        //ResourceA resourceA = new ResourceA();
        ResourceB resourceB = new ResourceB();

        new Thread(() -> {for(int i=0;i<10;i++) resourceB.increment();},"线程A").start();
        new Thread(() -> {for(int i=0;i<10;i++) resourceB.decrement();},"线程B").start();
        new Thread(() -> {for(int i=0;i<10;i++) resourceB.increment();},"线程C").start();
        new Thread(() -> {for(int i=0;i<10;i++) resourceB.decrement();},"线程D").start();

//        new Thread(()->{
//            try {
//                for(int i=0;i<10;i++) resourceA.increment();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },"线程A").start();
//        new Thread(()->{
//            try {
//                for(int i=0;i<10;i++) resourceA.decrement();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },"线程B").start();
//        new Thread(()->{
//            try {
//                for(int i=0;i<10;i++) resourceA.increment();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },"线程C").start();
//        new Thread(()->{
//            try {
//                for(int i=0;i<10;i++) resourceA.decrement();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },"线程D").start();
    }
}
