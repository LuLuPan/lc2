/*
Given a binary tree, return the bottom-up level order traversal of its nodes' 
values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
        	List<Integer> level = new ArrayList<Integer>();
        	int size = queue.size();
        	for (int i = 0; i < size; i++) {
        		TreeNode cur = queue.poll();
        		level.add(cur.val);
        		if (cur.left != null)
        		    queue.offer(cur.left);
        		if (cur.right != null)
        		    queue.offer(cur.right);
        	}
        	result.add(level);
        }

        Collections.reverse(result);
        return result;
    }
}