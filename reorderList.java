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
        if (head == null || head.next == null)
            return;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        fast = slow.next;
        slow.next = null;
        slow = head;
        
        fast = reverseList(fast);
        // merge two
        // No need to use prev, use tmp to store cur2.next
        while (slow != null && fast != null) {
            ListNode n = fast.next;
            fast.next = slow.next;
            slow.next = fast;
            slow = fast.next;
            fast = n;
        }
    }
    
    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = head;
        ListNode cur = head.next;
        while (cur != null) {
            prev.next = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = prev.next;
        }
        
        return dummy.next;
    }
}