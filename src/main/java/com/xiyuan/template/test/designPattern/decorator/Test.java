package com.xiyuan.template.test.designPattern.decorator;

import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiyuan_fengyu on 2018/8/2 16:10.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 123);
        map.put("name", "Tom");
        System.out.println(new PrettyPrintMap<>(map).pretty());

        Map<String, Object> mapCopy = new DeepClonableMap<>(map).deepClone();
        System.out.println(mapCopy);

    }

}