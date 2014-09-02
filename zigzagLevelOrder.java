/*
Given a binary tree, return the zigzag level order traversal of 
its nodes' values. (ie, from left to right, then right to left 
for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        boolean leftToRight = true;
        while (!queue.isEmpty()) {
        	List<Integer> level = new ArrayList<Integer>();
        	int size = queue.size();
        	for (int i = 0; i < size; i++) {
        		TreeNode cur = queue.poll();
        		level.add(cur.val);
        		if (cur.left != null)
        			queue.add(cur.left);
        		if (cur.right != null)
        			queue.add(cur.right);
        	}

        	if (leftToRight == false)
        		Collections.reverse(level);

        	leftToRight = !leftToRight;
        	result.add(level);
        }

        return result;  
    }
}