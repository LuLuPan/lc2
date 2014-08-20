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

/*
Sort a linked list using insertion sort.
Solution: Insert sort
Corner case:
*/
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val >= prev.val) {
                prev = cur;
                cur = cur.next;
            } else {
                ListNode p = dummy;
                while (p.next != cur) {
                    if (p.next.val < cur.val)
                        p = p.next;
                    else {
                        prev.next = cur.next;
                        cur.next = p.next;
                        p.next = cur;
                        cur = prev.next;
                        //Error: should break here, or p will keep move on 
                        // and cur will become null
                        break;
                    }
                }
            }
        }

        return dummy.next;
    }
}