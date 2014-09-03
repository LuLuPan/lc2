/*
Given a singly linked list where elements are sorted in ascending order, 
convert it to a height balanced BST.

Solution: 
1. DSW: Unbalanced tree => list => balanced BST
2. Recursive..
   Bottom to Top to establish tree
   0) Binary divide to make sure BST attribute that mid as root in subtree
   1) Use inorder traversal method to build left subtree, root and right tree
      Since in inoder traveral, left most leave node will be accessed first,
      and in a sorted list, left most node is the first to be accessed too,
      and left most leave node is list head when flatten a BST to a list.

*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        ListNode cur = head;
        int len = 0;
        while (cur != null) {
        	len++;
        	cur = cur.next;
        }
        // need a list to store node as parameter passing,
        // change the reference value cannot reflect to calling function
        List<ListNode> list = new ArrayList<ListNode>();
        list.add(head);
        return buildBST(0, len - 1, list);
    }

    private TreeNode buildBST(int start, int end, List<ListNode> list) {
    	if (start > end) return null;
    	int mid = start + (end - start) / 2;
    	TreeNode left = buildBST(start, mid - 1, list);
    	// reach the middle and create root
    	TreeNode root = new TreeNode(list.get(0).val);
    	root.left = left;
    	// cur node been accessed, move to next
    	list.set(0, list.get(0).next);
    	root.right = buildBST(mid + 1, end, list);
    	return root;
    }
}