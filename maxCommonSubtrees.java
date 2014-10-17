import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
 
public class MaxSubtree {
    
    /** Basic node class */
    private static class Node {
        private final int id;
        private final List<Node> children;

        Node(int id) {
            this.id = id;
            this.children = new ArrayList<Node>();
        }

        @Override
        public String toString() {
            return String.valueOf(id);
        }
    }

    /**
    * @return {@link List} of {@link Node}s which form the largest common
    *       subtrees
    */
    private static List<Node> getLargestCommonSubtrees(Node root) {
          List<Node> result = new ArrayList<Node>();
          if (root == null || root.children.size() == 0)
              return result;
          
          HashMap<Node, String> subtrees = new HashMap<Node, String>();
          HashMap<String, List<Node>> map = new HashMap<String, List<Node>>();
          
          formatSubTree2(root, map);
          int maxLen = Integer.MIN_VALUE;
          Iterator<Entry<String, List<Node>>> iter = map.entrySet().iterator();
          while (iter.hasNext()) {
              Entry<String, List<Node>> pair = iter.next();
              if (pair.getValue().size() >= 2 && pair.getKey().length() > maxLen) {
                  maxLen = pair.getKey().length();
                  result = pair.getValue();
              }
          }
          
          return result;
     }
    
    /*
     * format subtrees with ()
     * HashMap<Subtree string, list of node> represents same subtree structure from different root node
     */
    public static String formatSubTree2(Node root, HashMap<String, List<Node>> subtrees) {
        if (root == null) return "()";
        StringBuilder treeSb = new StringBuilder();
        // leaf node
        if (root.children.size() == 0) {
            if (!subtrees.containsKey("#")) {
                List<Node> list = new ArrayList<Node>();
                list.add(root);
                subtrees.put("#", list);
            } else {
                subtrees.get("#").add(root);
            }
            return "(#)";
        }
        
        treeSb.append("(#");
        for (Node tn : root.children)
            treeSb.append(formatSubTree2(tn, subtrees));
        treeSb.append(")");
        String subtreeStr = treeSb.toString();
        if (!subtrees.containsKey(subtreeStr)) {
            List<Node> list = new ArrayList<Node>();
            list.add(root);
            subtrees.put(subtreeStr, list);
        } else {
            subtrees.get(subtreeStr).add(root);
        }
        return subtreeStr;
    }
    
    /** Useful for testing */
    private static void basicTest() {
        Node root = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        root.children.add(node1);
        root.children.add(node2);
        node1.children.add(node3);
        node1.children.add(node4);
        
        Node node7 = new Node(7);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node2.children.add(node5);
        node5.children.add(node6);
        node5.children.add(node7);

        List<Node> result = getLargestCommonSubtrees(root);
        if (!result.contains(node1))
            throw new AssertionError(String.format(
                    "Expected to find node 1 but found nodes %s", result));
        if (!result.contains(node2))
            throw new AssertionError(String.format(
                    "Expected to find node 2 but found nodes %s", result));
        System.out.println("Test finished.");
    }
    
    public static void main(String[] args) {
        basicTest();
    }
}