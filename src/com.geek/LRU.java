package com.geek;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * lru算法实现，缓存淘汰算法
 * <p>
 * 我们维护一个有序单链表，越靠近链头尾部的结点是越早之前访问的。当有一个新的数据被访问时，我们从链表尾开始顺序遍历链表。
 * <p>
 * 1. 如果此数据之前已经被缓存在链表中了，我们遍历得到这个数据对应的结点，并将其从原来的位置删除，然后再插入到链表的尾部。
 * <p>
 * 2. 如果此数据没有在缓存链表中，又可以分为两种情况：
 * <p>
 * 如果此时缓存未满，则将此结点直接插入到链表的尾部；
 * 如果此时缓存已满，则链表头结点删除，将新的数据结点插入链表的尾部。
 */
public class LRU<T> {
    private int capacity;

    static class Node<T> {
        private int key;
        private T data;

        public Node(int key, T data) {
            this.key = key;
            this.data = data;
        }
    }

    private LinkedList<Node<Object>> list = new LinkedList<>();

    public T get(int key) {
        Object data = null;
        Iterator<Node<Object>> nodeIterator = list.descendingIterator();
        while (nodeIterator.hasNext()) {
            Node<Object> node = nodeIterator.next();
            if (node.key == key) {
                data = node.data;
                nodeIterator.remove();
                list.add(node);
                break;
            }
        }
        if (data == null) {
            if (list.size() == capacity) {
                list.removeFirst();
            }
            list.add(getDate(key));
        }
        return (T) data;
    }

    public void put(Node node) {
        Iterator<Node<Object>> nodeIterator = list.descendingIterator();
        while (nodeIterator.hasNext()) {
            Node<Object> next = nodeIterator.next();
            if (next.key == node.key) {
                nodeIterator.remove();
                list.add(node);
                return;
            }
        }
        if (list.size() == capacity) {
            list.removeFirst();
        }
        list.add(node);
    }

    private Node<Object> getDate(int key) {
        System.out.println("获取数据");
        return new Node<>(key, "");
    }


}
