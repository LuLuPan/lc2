/*
Reverse a linked list from position m to n. [[Do it in-place and in one-pass]].

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) return head;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        ListNode prev = dummy;
        ListNode start = null;
        ListNode end = null;
        int count = 1;
        while (cur != null) {
        	if (count == m) {
        		start = prev;
        	} else if (count == n) {
        		end = cur.next;
        		prev = start.next;
        		cur = prev.next;
        		while (cur != end) {
        			prev.next = cur.next;
        			cur.next = start.next;
        			start.next = cur;
        			cur = prev.next;
        		}
        		break;
        	}
        	cur = cur.next;
        	prev = prev.next;
        	count++;
        }

        return dummy.next;
    }
}