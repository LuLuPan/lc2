/*
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
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

/*
Solution1: keep two pointer, fast one is k step further than slow one
after fast one become null, it means slow one arrive at the point to be broken
*/
public class Solution {
    public ListNode rotateRight(ListNode head, int n) {
        if (head == null || head.next == null || n == 0) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        ListNode prev = dummy;
        ListNode last = cur;
        // n could be bigger than length
        int len = 0;
        while (cur != null) {
        	len++;
        	cur = cur.next;
        }

        n %= len;
        int count = 0;
        cur = head;
        while (cur != null) {
        	if (count >= n) {
        		prev = prev.next;
        	}
        	count++;
        	if (cur.next == null)
        		last = cur;
        	cur = cur.next;
        }
        // Error: n could be equal to length and list will keep same
        // then prev will not be moved at all
        if (prev != dummy) {
            last.next = dummy.next;
            dummy.next = prev.next;
            prev.next = null;
        }

        return dummy.next;
    }
}

/*
Solution2: Since k could be bigger than length, scan once to get length
and connect tail with head, and keep scanning length - k % length, when it
will be the head
*/
public class Solution {
    public ListNode rotateRight(ListNode head, int n) {
    	if (head == null || head.next == null || n == 0) return head;
    	int len = 1;
    	ListNode cur = head;
    	while (cur.next != null) {
    		len++;
    		cur = cur.next;
    	}

    	cur.next = head;

    	n %= len;

    	for (int i = 0; i < len - n; i++)
    		cur = cur.next;

    	head = cur.next;
    	cur.next = null;
    	return head;
    }
}