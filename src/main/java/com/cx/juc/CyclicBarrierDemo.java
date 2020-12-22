package com.cx.juc;

import java.util.concurrent.CyclicBarrier;

/**
 * @Author cx
 * @Date 2020/12/15 16:21
 *  创建一个新的 CyclicBarrier ,当给定数量的线程(线程)等待时,它将跳闸,当屏障跳闸时执行给定的屏障动作,由最后一个进入屏障的线程执行
 **/

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        //执行满7次时执行
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> System.out.println("召唤神龙！"));
        for (int i=0;i<7;i++){
            int temp = i;
            new Thread(() -> System.out.println("收集到第"+(temp+1)+"颗龙珠"),String.valueOf(i+1)).start();
        }
    }
}
