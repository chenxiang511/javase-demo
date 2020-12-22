package com.cx.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author cx
 * @Date 2020/12/15 16:26
 * 信号量
 * 示例：模拟抢车位
 **/

public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);//初始信号量
        for (int i=0;i<6;i++){
            new Thread(() -> {
                try {
                    semaphore.acquire();//请求获得许可，如果有可获得的许可则继续往下执行，许可数减1。否则进入阻塞状态
                    System.out.println(Thread.currentThread().getName()+"抢到了车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"离开了车位");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    semaphore.release();//释放一个许可，将其返回给信号量
                }
            },String.valueOf(i+1)).start();
        }
    }
}
