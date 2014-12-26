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
        doSum(root, sum, new ArrayList<Integer>(), result);
        return result;
    }
    
    private void doSum(TreeNode root, int sum, List<Integer> path, List<List<Integer>> result) {
        if (root == null) return;
        if (root.left == null && root.right == null && root.val == sum) {
            path.add(root.val);
            result.add(new ArrayList<Integer>(path));
            path.remove(path.size() - 1);
            return;
        }
        
        path.add(root.val);
        doSum(root.left, sum - root.val, path, result);
        doSum(root.right, sum - root.val, path, result);
        path.remove(path.size() - 1);
    }
}