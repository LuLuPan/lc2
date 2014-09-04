/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's 
sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
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

// DFS
public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;
        List<Integer> path = new ArrayList<Integer>();
        // Error: put root into path at first and deduct its value
        path.add(root.val);
        findPath(root, path, result, root.val, sum);

        return result;
    }

    private void findPath(TreeNode root, List<Integer> path, 
    	List<List<Integer>> result, int curSum, int sum)
    {
    	if (root.left == null && root.right == null) {
    		if (curSum == sum) {
    			result.add(new ArrayList<Integer>(path));
    		}
    		return;
    	}

    	if (root.left != null) {
    		path.add(root.left.val);
    		findPath(root.left, path, result, curSum + root.left.val, sum);
    		path.remove(path.size() - 1);
    	}
    	if (root.right != null) {
    		path.add(root.right.val);
    		findPath(root.right, path, result, curSum + root.right.val, sum);
    		path.remove(path.size() - 1);
    	}
    }
}