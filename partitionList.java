/*
Given a linked list and a value x, partition it such that all nodes less than
x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the 
two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5
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
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur1 = dummy;
        ListNode prev2 = cur1;
        ListNode cur2 = cur1.next;

        while (cur2 != null) {
        	if (cur2.val < x) {
        		if (cur1 != prev2) {
        			prev2.next = cur2.next;
        			cur2.next = cur1.next;
        			cur1.next = cur2;
        			cur2 = prev2.next;
        			cur1 = cur1.next;
        			continue;
        		}
        		cur1 = cur1.next;
        	}
        	prev2 = prev2.next;
        	cur2 = cur2.next;
        }

        return dummy.next;
    }
}