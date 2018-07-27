package com.xiyuan.template.test.designPattern.adapter.example2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by xiyuan_fengyu on 2018/7/27 17:45.
 */
public class Test {

    private static void printStringList(List<String> list) {
        System.out.println("[" + list.stream().collect(Collectors.joining(", ")) + "]");
    }

    private static class StringListAdapter extends ArrayList<String> {

        private static final long serialVersionUID = -3483537340133271866L;

        public StringListAdapter(int[] origin) {
            for (int i : origin) {
                add("" + i);
            }
        }

    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2};
        printStringList(new StringListAdapter(nums));
    }

}
