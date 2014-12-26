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
        RandomListNode cur = head;
        // double the list
        while (cur != null) {
            RandomListNode node = new RandomListNode(cur.label);
            node.next = cur.next;
            cur.next = node;
            cur = node.next;
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
        RandomListNode dummy = new RandomListNode(-1);
        RandomListNode cur2 = dummy;
        while (cur != null) {
            cur2.next = cur.next;
            cur.next = cur.next.next;
            cur = cur.next;
            cur2 = cur2.next;
        }
        
        return dummy.next;
    }
}