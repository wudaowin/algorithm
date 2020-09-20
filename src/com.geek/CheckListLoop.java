package com.geek;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 链表中还的检测
 * 1--快慢指针法
 * 两个指针，快指针一次前进两格，慢指针一次前进一格，如果有换环那么经过n次前进，快慢指针会相遇，否则无环。
 * 2--足迹法
 * 顺序遍历链表并将其保存至hashMap中，若同一节点出现了两次，则表明有环。
 */
public class CheckListLoop<T> {
    /**
     * 快慢指针法
     *
     * @param node
     * @return
     */
    private Boolean hasLoop(Node<T> node) {
        if (node == null) {
            return false;
        }
        Node slow = node;
        Node fast = node.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == null) {
                return false;
            }
            if (slow.equals(fast)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 足迹法
     *
     * @param node
     * @return
     */
    private static final HashMap<Node, Integer> hashMap = new HashMap<>();


    public ListNode hasCycle(ListNode node) {
        if (node == null || node.next == null) {
            return null;
        }
        Map<ListNode, Integer> map = new HashMap<>();
        while (node != null) {
            if (map.containsKey(node)) {
                return node;
            }
            map.put(node, node.val);
            node = node.next;
        }
        return null;
    }


    private boolean hasLoopTwo(Node<T> node, Integer count) {
        if (node == null || node.next == null) {
            return false;
        }
        if (hashMap.containsKey(node)) {
            return true;
        } else {
            hashMap.put(node, count);
            return hasLoopTwo(node.next, count++);
        }
    }

    private static class Node<T> {
        Node next;
        T data;

        public Node(Node next, T data) {
            this.next = next;
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public T getData() {
            return data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(next, node.next) &&
                    Objects.equals(data, node.data);
        }

        @Override
        public int hashCode() {
            return Objects.hash(next, data);
        }
    }
}
