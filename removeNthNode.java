/*
Given a linked list, remove the nth node [from the end] of list and 
return its head.

For example,

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n == 0) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        ListNode prev = dummy;
        int count = 0;
        while (cur != null) {
        	if (count >= n)
        		prev = prev.next;
        	count++;
        	cur = cur.next;
        }
        // Notice: Cannot be prev.next = cur
        // since only need to  remove prev.next
        // there are n nodes between prev and cur
        prev.next = prev.next.next;
        return dummy.next;
    }
}