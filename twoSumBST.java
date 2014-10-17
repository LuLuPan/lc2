public class Solution {
    private void buildLeftEdge(TreeNode root, Stack<TreeNode> edge) {
    	if (root == null) return;
    	edge.push(root);
    	buildLeftEdge(root.left, edge);
    }

    private void buildRightEdge(TreeNode root, Stack<TreeNode> edge) {
    	if (root == null) return;
    	edge.push(root);
    	buildRightEdge(root.right, edge);
    }

	public boolean twoSumBST(TreeNode root, int target) {
		if (root == null) return false;
		Stack<TreeNode> leftEdge = new Stack<TreeNode>();
		Stack<TreeNode> rightEdge = new Stack<TreeNode>();

		buildLeftEdge(root, leftEdge);
		buildRightEdge(root, rightEdge);

		while (!leftEdge.isEmpty() && !rightEdge.isEmpty()) {
			// avoid stack cross
			if (leftEdge.peek().val  >= right.peek().val)
				break;
			int sum = leftEdge.peek().val + rightEdge.peek().val;
			if (sum == target)
				return true;
			else if (sum < target) {
				TreeNode node = leftEdge.pop();
				// build left most edge for right subtree
				if (node.right != null)
					buildLeftEdge(node.right, leftEdge);
			} else {
				TreeNode node = rightEdge.pop();
				// build right most edge for the left subtree
				if (node.left != null)
					buildRightEdge(node.left, rightEdge);
			}
		}

		return false;
	}
}