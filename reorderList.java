/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.

Solution: 1. Find second half of list
          2. Reverse it
          3. Merge two half
Complexity: O(n) + O(1)
*/
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public void reorderList(ListNode head) {
    	// for 1->2, return directly
        if (head == null || head.next == null || head.next.next == null)
            return;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        ListNode slow = dummy;

        while (cur != null && cur.next != null) {
        	cur = cur.next.next;
        	slow = slow.next;
        }

        cur = slow.next.next;
        ListNode prev = slow.next;
        while (cur != null) {
        	prev.next = cur.next;
        	cur.next = slow.next;
        	slow.next = cur;
        	cur = prev.next;
        }

        ListNode prev2 = slow.next;
        ListNode cur2 = prev2.next;
        slow.next = null;
        prev = head;
        cur = prev.next;
        while (cur != null && cur2 != null) {
        	prev.next = prev2;
        	prev2.next = cur;
        	prev = cur;
        	cur = cur.next;
        	prev2 = cur2;
        	cur2 = cur2.next;
        }
        // Error: No condition here
        prev.next = prev2;
    }
}