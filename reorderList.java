/*
Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.

Solution: 1. Find second half of list
          2. Reverse it
          3. Merge two half
Complexity: O(N) + O(1)
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
        if (head == null || head.next == null || head.next.next == null)
            return;
            
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        // split into two
        fast = slow.next;
        slow.next = null;
        
        // reverse big list
        ListNode last = new ListNode(Integer.MIN_VALUE);
        last.next = fast;
        ListNode prev = fast;
        ListNode cur = fast.next;
        while (cur != null) {
            prev.next = cur.next;
            cur.next = last.next;
            last.next = cur;
            cur = prev.next;
        }
        
        // merge two
        // No need to use prev, use tmp to store cur2.next
        ListNode cur1 = head;
        ListNode cur2 = last.next;
        while (cur1 != null && cur2 != null) {
            ListNode n = cur2.next; // tmp
            cur2.next = cur1.next;
            cur1.next = cur2;
            cur1 = cur2.next;
            cur2 = n;
        }
    }
}