/*
Given a linked list, return the node where the cycle begins. 
If there is no cycle, return null.

Follow up:
Can you solve it without using extra space?

Solution: slow has s steps, fast has 2s steps
          when fast chased slow, slow hasn't finished one cycle.
          and fast already finished nr steps
          2s = s + nr

          List length is L, distance between cycle entry point
          and slow/fast met point is a, distance between head and
          cycle entry point is x

          x + a = s = nr = (n - 1)r + r = (n-1)r + L - x
          so
          x = (n-1)r + L -x - a
          L - x - a is distance from met point to cycle entry point
          It means a point start from head, has x steps, and slow
          start from met point, has L - X - a steps plus n - 1 cycle
          they will meet 
*/
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
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode fast = head;
        ListNode slow = head;
        int count = 0;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            count++;
            if (slow == fast) {
            	ListNode slow2 = head;
            	while (slow != slow2) {
            		slow = slow.next;
            		slow2 = slow2.next;
            	}

            	return slow2;
            }       	
        }

        return null;
    }
}