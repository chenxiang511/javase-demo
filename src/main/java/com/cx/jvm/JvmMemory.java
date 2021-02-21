package com.cx.jvm;

public class JvmMemory {

    public static void main(String[] args) {
        System.out.println("CPU核心数量:"+Runtime.getRuntime().availableProcessors());
        var maxMemory = Runtime.getRuntime().maxMemory();
        var totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("Xmx:MAX_MEMORY = "+maxMemory + "B\t"+(maxMemory/1024/1024) + "MB");
        System.out.println("Xms:TOTAL_MEMORY = "+totalMemory + "B\t"+(totalMemory/1024/1024) + "MB");
    }
}
