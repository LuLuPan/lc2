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
        ListNode cur = dummy.next;
        while (cur.next != null) {
        	if (cur.val == cur.next.val)
        		cur = cur.next;
        	else {
        		// find first un-equal
        		// if prev.next = cur, means continous non-equal elements
        		// keep prev anc cur moving forward
        		if (prev.next == cur) {
        			prev = prev.next;
        			cur = cur.next;
        		} else {
        			// if not, means continous equaled element found
        			// need to ignore them
        			// if two or more continous equaled range found
        			// keep moving until the first non-continous element found
        		    cur = cur.next;
        		    if (cur.next != null && cur.val == cur.next.val)
        			    continue;
        		    else {
        			    // find continous two elments are not equal 
        			    prev.next = cur;
        			    prev = prev.next;
        			    if (cur.next != null)
        			       cur = cur.next;
        			}
        		}
        	}
        }

        // in case of continous equaled elements at the end
        if (prev.next != null && prev.next.next != null) {
        	if (prev.next.val == prev.next.next.val)
        		prev.next = null;
        }

        return dummy.next;
    }
}