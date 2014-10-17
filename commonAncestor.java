public class Solution {
	    public class TNode {

        TNode parent;
        TNode left;
        TNode right;


        public TNode(TNode parent, TNode left, TNode right) {
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        boolean isRoot() {

            return parent == null;
        }
    }
    
    /* geneterate one and two's path to root
     * find the first common node in these two pathes
     */
    public TNode commonAncestor(TNode one, TNode two) {
        TNode ancestor = null;
        if (one == null || two == null) return ancestor;
        
        // Insert to head should use LinkedList!!!!!!
        List<TNode> pathone = new LinkedList<TNode>();
        List<TNode> pathtwo = new LinkedList<TNode>();
        generatePath(pathone, one);
        generatePath(pathtwo, two);
        
        /*
         * path1: one->p1->p2->c1->c2->root
         * path2: two->q1->q2..->c1->c2
         * which is the insertion order to root
         * this may required O(logn*logn) in worse case
        */

        /*
         * Other than insertion order, could maintain path as
         * root->...->one and root->...->two
         * and find the first diverged node which will be O(logn) in worse
         */
        ListIterator<TNode> iter1 = pathone.listIterator();
        ListIterator<TNode> iter2 = pathtwo.listIterator();
        
        while (iter1.hasNext() && iter2.hasNext()) {
            ancestor = iter1.next();
            if (ancestor != iter2.next())
                break;
        }

        return ancestor.parent;
    }
    
    private void generatePath(LinkedList<TNode> path, TNode cur) {
        while (!cur.isRoot()) {
            path.addFirst(cur);
            cur = cur.parent;
        }
        path.add(cur);
    }


    /*
     * recursively
     * if one and two are all in the left or right side of cur node
     * go left or right until they seperate
     */
    public TNode commonAncestor(TNode root, TNode one, TNode two) {
    	if (covers(root.left, one) && covers(root.left, two))
    		return commonAncestor(root.left, one, two);
    	if (covers(root.right, one) && covers(root.right, two))
    		return commonAncestor(root.right, one, two);
    	return root;
    }

    private boolean covers(TNode root, TNode node) {
    	if (root == null) return false;
    	if (root == node) return true;
    	return covers(root.left, node) || covers(root.right, node);
    }
}