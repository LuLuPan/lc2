/*
Given a sorted linked list, delete all nodes that have duplicate numbers, 
leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = head;
        while (cur != null) {
            // flag to mark equal pair
            boolean dup = false;
            while (cur.next != null && cur.val == cur.next.val) {
                dup = true;
                cur = cur.next;
            }
            if (dup) {
                cur = cur.next;
                continue;
            }
            // find the first unequaled pair
            // cur != cur.next
            prev.next = cur;
            prev = prev.next;
            cur = cur.next;
        }
        
        prev.next = cur;
        return dummy.next;
    }
}