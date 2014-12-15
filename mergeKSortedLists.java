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


    // Solution:
    // min heap
    class HeapNode implements Comparable<HeapNode> {
        private ListNode node;
        public HeapNode(ListNode node) {
            this.node = node;
        }

        public int compareTo(HeapNode a) {
            return this.node.val > a.node.val ? 1 : (this.node.val == a.node.val ? 0 : -1);
        }
    }

    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0)
            return null;
        ListNode curNode = new ListNode(-1);
        ListNode head = curNode;
        // first node from each list build the min heap
        PriorityQueue<HeapNode> minHeap = new PriorityQueue<HeapNode>();
        Iterator<ListNode> it = lists.iterator();
        while (it.hasNext()) {
            ListNode node = it.next();
            minHeap.add(new HeapNode(node));
        }

        while (minHeap.size() != 0) {
            curNode.next = minHeap.poll().node;
            curNode = curNode.next;
            if (curNode != null && curNode.next != null) {
                minHeap.add(new HeapNode(curNode.next));
            }
        }

        return head.next;
    }

    public static void main(String[] args) {
        MergeKList rk = new MergeKList();
        List<ListNode> lists = new ArrayList<ListNode>();
        ListNode node1 = rk.new ListNode(1);
        ListNode node2 = rk.new ListNode(2);
        ListNode node3 = rk.new ListNode(3);
        ListNode node4 = rk.new ListNode(4);

        node1.next = node2;
        lists.add(node1);
        node3.next = node4;
        lists.add(node3);

        rk.mergeKLists(lists);
    }
}