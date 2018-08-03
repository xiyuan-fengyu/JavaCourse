package com.xiyuan.template.test.designPattern.composite;

import java.util.Arrays;

/**
 * Created by xiyuan_fengyu on 2018/8/3 16:33.
 */
public class Test {

    public static void main(String[] args) {
        TrieTree trieTree = new TrieTree(Arrays.asList(
                "字典树搜索测试",
                "test",
                "just 123"
        ));
        System.out.println(trieTree.contains("just "));
        System.out.println(trieTree.contains("just 123"));
    }

}