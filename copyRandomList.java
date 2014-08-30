/*
A linked list is given such that each node contains an additional random pointer
which could point to any node in the list or null.

Return a deep copy of the list.

Solution: 1. Copy node and insert cloned node back to original one.
             Then a doubled linked list created.
          2. Traverse the doubled list, if first node has random, second node's
             random pointis random's next
          3. break list into two

*/

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        // double the list
        RandomListNode cur = head;
        while (cur != null) {
        	RandomListNode newNode = new RandomListNode(cur.label);
        	newNode.next = cur.next;
        	cur.next = newNode;
        	cur = cur.next.next;
        }

        // clone new random
        cur = head;
        while (cur != null) {
        	if (cur.random != null)
        		cur.next.random = cur.random.next;
        	cur = cur.next.next;
        }

        // break the list into two
        cur = head;
        RandomListNode head2 = cur.next;
        RandomListNode cur1 = cur.next;
        while (cur.next != null && cur1.next != null) {
        	cur.next = cur.next.next;
        	cur = cur.next;
        	cur1.next = cur1.next.next;
        	cur1 = cur1.next;
        }
        cur.next = null;

        return head2;
    }
}