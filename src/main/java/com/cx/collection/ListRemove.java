package com.cx.collection;

import java.util.ArrayList;
import java.util.List;

public class ListRemove {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("abc111");
        list.add("abc222");
        list.add("abc");
        list.add("abc");
        list.add("abc333");
        list.add("abc");
        list.add("abc444");
        System.out.println(list.toString());

        //利用stream流式计算，删除指定元素
        //List<String> strings = list.stream().filter(v -> !"abc".equals(v)).collect(Collectors.toList());
        //System.out.println(strings);

        //利用ArrayList自带方法removeIf
        list.removeIf(o -> o.equals("abc"));
        System.out.println(list.toString());

        String a = new String("abc");
        String b = new String("abc");
        System.out.println(a==b);
        System.out.println(a.equals(b));
        String c = "abc";
        String d = "abc";
        System.out.println(c==d);
    }
}
