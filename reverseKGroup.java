/*
Given a linked list, reverse the nodes of a linked list k at a time and return
 its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end
 should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // case to exam: 1->2, 2
        // if count = 0 as init and count % k
        // then cur become null and count to be 2, while will exit and 
        // doesn't excute reverse at all
        ListNode cur = head;
        ListNode prev = dummy;
        // cur is the first node after reversing range, so it could be
        // null. count is initiated as 0.
        int count = 0;
        // Note 1: true
        while (true) {
            // Note 2: count = 0 need to be checked intially
            if (count > 0 && count % k == 0) {
                ListNode p = prev.next;
                ListNode q = p.next;
                while (q != cur) {
                    p.next = q.next;
                    q.next = prev.next;
                    prev.next = q;
                    q = p.next;
                }
                prev = p;
            }
            
            // Note 2
            if (cur == null) break;
            count++;
            cur = cur.next;
        }

        return dummy.next;
    }