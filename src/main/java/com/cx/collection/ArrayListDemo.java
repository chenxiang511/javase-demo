package com.cx.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ArrayListDemo {

    public static void main(String[] args) {
        //创建ArrayList，普通方式
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        arrayList1.add(1);
        arrayList1.add(2);
        arrayList1.add(3);
        System.out.println("arraylist1="+arrayList1);

        //内部类方式
        ArrayList<Integer> arrayList2 = new ArrayList<>(){{
            add(1);
            add(2);
            add(3);
        }};
        System.out.println("arraylist2="+arrayList2);

        //Arrays.asList()方式
        ArrayList<Integer> arrayList3 = new ArrayList<>(Arrays.asList(1,2,3));
        System.out.println("arraylist3="+arrayList3);

        ArrayList<Integer> arraylist4 = new ArrayList<>(Collections.nCopies(10, 0));
        System.out.println("arraylist4="+arraylist4);

        //1.指定位置插入首先要判断rangeCheckForAdd，size的长度。
        //2.通过上面的元素插入我们知道，每插入一个元素，size自增一次size++。
        //3.所以即使我们申请了10个容量长度的ArrayList，但是指定位置插入会依赖于size进行判断，所以会抛出IndexOutOfBoundsException异常。
        ArrayList<Integer> list = new ArrayList<>(10);
        list.add(2,1);
        System.out.println(list.size());
    }
}
