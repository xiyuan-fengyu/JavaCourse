package com.xiyuan.template.test.designPattern.composite;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by xiyuan_fengyu on 2018/8/3 15:23.
 */
public class TrieTree {

    private final Node root = new Node(null);

    public TrieTree() {
    }

    public TrieTree(Iterable<String> strs) {
        addAll(strs);
    }

    public void addAll(Iterable<String> strs) {
        for (String str : strs) {
            add(str);
        }
    }

    public void add(String str) {
        if (str == null) return;

        if (str.isEmpty()) root.hasLeafChild = true;

        Node curNode = root;
        for (int i = 0, len = str.length(); i < len; i++) {
            curNode = curNode.getOrAddChild(str.charAt(i));
        }
        curNode.hasLeafChild = true;
    }

    public boolean contains(String str) {
        if (str == null) return false;

        if (str.isEmpty()) return root.hasLeafChild;

        Node curNode = root;
        for (int i = 0, len = str.length(); i < len; i++) {
            curNode = curNode.getChild(str.charAt(i));
            if (curNode == null) return false;
        }
        return curNode.hasLeafChild;
    }

    private static class Node {

        private final Character value;

        private final Map<Character, Node> children;

        private boolean hasLeafChild;

        private Node(Character value) {
            this.value = value;
            this.children = new ConcurrentHashMap<>();
        }

        private Node getChild(Character character) {
            return children.get(character);
        }

        private Node getOrAddChild(Character c) {
            return children.computeIfAbsent(c, Node::new);
        }

    }

}
