package com.geek;

import java.util.Stack;

/**
 * 编写一个函数，检查输入的链表是否是回文的。
 * <p>
 * 输入： 1->2->2->1
 * 输出： true
 */
public class IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode fast = head;
        ListNode slow = head;
        for (; ; ) {
            fast = fast.next.next;
            stack.push(slow);
            slow = slow.next;
            if (fast == null) {
                break;
            }
            if (fast.next == null) {
                slow = slow.next;
                break;
            }
        }

        while (!stack.empty()) {
            if (slow.val != stack.pop().val) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
