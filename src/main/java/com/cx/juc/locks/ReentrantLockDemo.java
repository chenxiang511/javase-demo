package com.cx.juc.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author cx
 * @Date 2020/12/2 13:01
 * 可重入锁:ReentrantLock 实现三个线程抢票
 **/
class Ticket{//资源类
    private int number = 300;
    Lock lock = new ReentrantLock();
    void sale(){
        lock.lock();
        try {
            if (number>0) {
                System.out.println(Thread.currentThread().getName()+":当前剩余"+number--+"张");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

/**
 *高聚合低耦合的前提下，线程操作资源类
 *Lambda表达式：拷贝小括号,写死右箭头,落地大括号
 */
public class ReentrantLockDemo {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //启用三个线程操作抢票资源类
        new Thread(() -> {for (int i=0;i<400;i++) ticket.sale();},"渠道A").start();
        new Thread(() -> {for (int i=0;i<400;i++) ticket.sale();},"渠道B").start();
        new Thread(new Runnable() {//匿名内部类的形式
            @Override
            public void run() {
                for (int i=0;i<400;i++) ticket.sale();
            }
        }, "渠道C").start();
    }
}
