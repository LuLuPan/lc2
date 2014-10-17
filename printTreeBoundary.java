public class Solution {
	// print leaves from left to right
	private void printLeaves(TreeNode root) {
		if (root != null) {
			printLeaves(root.left);

			if (root.left == null && root.right == null)
				System.out.println(root.val);
			printLeaves(root.right);
		}
	}

    // left edge iteratively
	private void printLeftEdge(TreeNode root) {
		TreeNode cur = root;
		while (cur != null) {
			// skip the right left node which will be handled by leave printing
			if (cur.left == null && cur.right == null)
				return;
			System.out.println(cur.val);
			if (cur.left != null)
				cur = cur.left;
			else cur = cur.right;
		}
	}

    // right edge recursively
	private void printRightEdge(TreeNode root) {
		if (root != null) {
			// skip the right most leaf which will be handled by leave printing
			if (root.left == null || root.right == null)
				return;
			if (root.right != null)
			    printRightEdge(root.right);
			else
			    printRightEdge(root.left);
			System.out.println(root.val);
		}
	}

	public void printBoundaries(TreeNode root) {
		if (root == null) return;
		printLeftEdge(root);
		printLeaves(root);
		printRightEdge(root);
	}
}