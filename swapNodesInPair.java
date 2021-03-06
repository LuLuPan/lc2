/*
Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values
 in the list, only nodes itself can be changed.
 */
 /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        ListNode prev = dummy;

        // Error: case to exam: 1->2->null
        while (cur != null && cur.next != null) {
        	cur = cur.next;
        	prev.next.next = cur.next;
        	cur.next = prev.next;
        	prev.next = cur;
        	prev = cur.next;
        	cur = prev.next;
        }

        return dummy.next;
    }
}