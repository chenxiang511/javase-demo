package com.cx.juc.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author cx
 * @Date 2020/12/15 13:17
 * 读写锁，写锁独占，任何线程不能进入对象。读锁共享，可以允许其他读操作线程同时获取资源
 **/

class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    Lock readLock = readWriteLock.readLock();
    Lock writeLock = readWriteLock.writeLock();

    public void put(String key,Object value){
        writeLock.lock();
        try {
            System.out.println("======"+Thread.currentThread().getName()+"写入数据开始=====");
            map.put(key, value);
            System.out.println("======"+Thread.currentThread().getName()+"写入数据结束=====");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            writeLock.unlock();
        }
    }

    public void get(String key){
        readLock.lock();
        try {
            System.out.println("======"+Thread.currentThread().getName()+"读取数据开始=====");
            Object value = map.get(key);
            TimeUnit.SECONDS.sleep(3);
            System.out.println("======"+Thread.currentThread().getName()+"读取数据结束=====值为"+value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readLock.unlock();
        }

    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i=0;i<5;i++){
            final int temp = i;
            new Thread(() -> myCache.put(String.valueOf(temp),String.valueOf(temp)),"写线程"+(i+1)).start();
            new Thread(() -> myCache.get(String.valueOf(temp)),"读线程"+(i+1)).start();
        }
    }
}
