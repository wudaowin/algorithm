package com.geek;

/**
 * 合并两个链表，输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class MergeTwoLists {
    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }
        ListNode node = new ListNode(-1);
        ListNode head = node;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                node.next = l1;
                node = node.next;
                l1 = l1.next;
            } else {
                node.next = l2;
                node = node.next;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            node.next = l2;
        } else {
            node.next = l1;
        }
        return head.next;
    }



}
