/*
You are given two linked lists representing two non-negative numbers. 
The digits are stored in reverse order and each of their nodes contain
a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        int carrier = 0;
        while (cur1 != null || cur2 != null) {
            int val = carrier + (cur1 != null ? cur1.val : 0)
                              + (cur2 != null ? cur2.val : 0);
            carrier = val / 10;
            cur.next = new ListNode(val % 10);
            cur = cur.next;
            cur1 = cur1 == null ? null : cur1.next;
            cur2 = cur2 == null ? null : cur2.next;
        }
        
        if (carrier != 0) {
            cur.next = new ListNode(carrier);
        }
        
        return dummy.next;
    }
}