/*
Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
   */
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; left = null; right = null; }
 * }
 */
public class Solution {
    public List<TreeNode> generateTrees(int n) {
        return buildTrees(1, n);
    }
    
    private List<TreeNode> buildTrees(int left, int right) {
        List<TreeNode> result = new ArrayList<TreeNode>();
        if (left > right) {
            result.add(null);
            return result;
        }
        
        for (int i = left; i <= right; i++) {
            List<TreeNode> leftList = buildTrees(left, i - 1);
            List<TreeNode> rightList = buildTrees(i + 1, right);
            for (int j = 0; j < leftList.size(); j++) {
                for (int k = 0; k < rightList.size(); k++) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftList.get(j);
                    root.right = rightList.get(k);
                    result.add(root);
                }
            }
        }
        
        return result;
    }
}