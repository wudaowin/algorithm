package com.geek;

/**
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 */
public class ReversePrintList {
    public int[] reversePrint(ListNode head) {
        ListNode node = head;
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        int[] array = new int[count];
        node = head;
        for (int i = count - 1; i >= 0; i--) {
            array[i] = node.val;
            node = node.next;
            count--;
        }
        return array;
    }

}
