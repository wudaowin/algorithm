package com.geek;

/**
 * 链表反转
 */
public class ReverseList {
    private Node reverse(Node head) {
        if (head == null) {
            return null;
        }
        Node prev = null;
        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }


    static class Node {
        String data;
        Node next;
    }
}
