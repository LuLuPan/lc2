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

/*
Sort a linked list in O(n log n) time using constant space complexity.
double linked list: quick sort
single linked list: mege sort
Solution: Divide and conqur: binary divide into two list recursively
sort each list and merge two list
Corner case:
*/
public class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        // slow is the last node for fist half list
        // Error: need consider carefull about condition
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = slow.next;
        slow.next = null;
        l1 = sortList(head);
        l2 = sortList(fast);

        return merge2Lists(l1, l2);
    }

    private ListNode merge2Lists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode cur = dummy;
        while (cur1 != null && cur2 != null) {
            if (cur1.val < cur2.val) {
                cur.next = cur1;
                cur1 = cur1.next;
            } else {
                cur.next = cur2;
                cur2 = cur2.next;
            }

            cur = cur.next;
        }

        cur.next = cur1 == null ? cur2 : cur1;

        return dummy.next;
    }
}