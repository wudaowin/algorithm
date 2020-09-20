package com.geek;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 */
public class SwapPairs {
    public ListNode swapPairs(ListNode head) {
        //终止条件：链表只剩一个节点或者没节点了，没得交换了。返回的是已经处理好的链表
        if (head == null || head.next == null) {
            return head;
        }
        //一共三个节点:head, next, swapPairs(next.next)
        //下面的任务便是交换这3个节点中的前两个节点
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        //根据第二步：返回给上一级的是当前已经完成交换后，即处理好了的链表部分
        return next;
    }

    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode temp = new ListNode(-1);
        temp.next = head;
        ListNode prev = temp;
        while (head != null && head.next != null) {
            ListNode secondNode = swap(head, prev);

            head = secondNode.next;
            prev = secondNode;
        }
        return temp.next;
    }

    private ListNode swap(ListNode head, ListNode prev) {
        ListNode secondNode = head.next;

        //swap
        head.next = secondNode.next;
        secondNode.next = head;
        prev.next = secondNode;
        return head;
    }
}
