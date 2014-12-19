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
Merge k sorted linked lists and return it as one sorted list. 
Analyze and describe its complexity.

Solution: Reuse merge two sorted list.. Better solution?
Corner case:
*/
public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {
        int n = lists.size();
        if (n == 0) return null;
        if (n == 1) return lists.get(0);

        ListNode result = lists.get(0);

        for (int i = 1; i < n; i++) {
            result = merge2Lists(result, lists.get(i));
        }

        return result;
    }

    private ListNode merge2Lists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        ListNode cur1 = l1;
        ListNode cur2 = l2;

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

// minHeap
// O(nklogn) + O(k)
public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {
        int n = lists.size();
        if (n == 0) return null;
        if (n == 1) return lists.get(0);
        
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(lists.size(), 
            new Comparator<ListNode>() {
            @Override
            public int compare(ListNode l1, ListNode l2) {
                return l1.val - l2.val; 
            }
        });
        
        Iterator<ListNode> it = lists.iterator();
        while (it.hasNext()) {
            ListNode node = it.next();
            if (node != null)
                minHeap.offer(node);
        }
        
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            if (node != null) {
                cur.next = node;
                cur = cur.next;
                if (node.next != null)
                    minHeap.offer(node.next);
            }
        }
        
        
        return dummy.next;
    }
}


// divide and conquer
// O(knlogn) + O(1)
public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {
        int n = lists.size();
        if (n == 0) return null;
        if (n == 1) return lists.get(0);
        
        int end = lists.size() - 1;
        while (end > 0) {
            // Note: end doesn't need update
            int begin = 0;
            while (begin < end) {
                // inplace
                lists.set(begin, mergeTwo(lists.get(begin), lists.get(end)));
                begin++;
                end--;
            }
        }
        
        return lists.get(0);
    }
    
    private ListNode mergeTwo(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        
        cur.next = l1 != null ? l1 : l2;
        return dummy.next;
    } 
}
