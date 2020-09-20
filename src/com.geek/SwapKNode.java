package com.geek;

/**
 * 25. K 个一组翻转链表
 */
public class SwapKNode {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode tail = head;
        for (int i = 0; i <k; i++) {
            if (tail == null) {
                return head;
            }
            tail = tail.next;

        }
        ListNode node = reverseList(head,tail);
        head.next = reverseKGroup(tail, k);

        return node;
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    private ListNode reverseList(ListNode head,ListNode tail) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != tail) {
            ListNode next = cur.next;
            cur.next = prev;

            prev = cur;
            cur = next;
        }
        return prev;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = null;

        SwapKNode swapKNode = new SwapKNode();
        ListNode node = swapKNode.reverseKGroup(head, 2);
        System.out.println(node);
    }
}
