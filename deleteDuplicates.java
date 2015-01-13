/*
Given a sorted linked list, delete all duplicates such that each element 
appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
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
        ListNode cur = head;
        ListNode prev = head;
        while (cur != null) {
            if (cur.val == prev.val)
                cur = cur.next;
            else {
                prev.next = cur;
                prev = cur;
                cur = cur.next;
            }
        }
        
        // Notice: 1->1
        prev.next = cur;
        
        return head;
    }
}