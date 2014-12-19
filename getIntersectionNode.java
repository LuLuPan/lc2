/**
* Write a program to find the node at which the intersection of two singly linked 
* lists begins.
*/
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        
        ListNode curA = headA;
        ListNode curB = headB;
        /**
         * Exchange cur pointer to the others header when it reach tail
         * First time is the shorter cur pointer, after exchange,
         * longer cur and shorter cur are on the longer list and distance
         * is the length of shorter list.
         * Second time is the longer cur pointer exchange to shorter list.
         * At this time, two cur pointer should have same distance to each
         * list's tail. If encounter at some time, they intersect.
         * If they are both null, there is no intersection.
         */
        while (curA != null || curB != null) {
            if (curA == null)
                curA = headB;
            else if (curB == null)
                curB = headA;
            else if (curA == curB)
                return curA;
            else {
                curA = curA.next;
                curB = curB.next;
            }
        }
        
        return null;
    }
}
