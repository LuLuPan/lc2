/*
Clone an undirected graph. Each node in the graph contains a label and a 
list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and 
each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as 
separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a 
self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/


Solution: HashMap<origin, copied> to record copied node
          DFS
          BFS

Complexity:

Corner case: node is null
*/


/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        HashMap<UndirectedGraphNode, UndirectedGraphNode> copied = 
            new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        
        cloneBFS(node, copied);
        return copied.get(node);
    }
    
    private void cloneDFS(UndirectedGraphNode node, 
                         HashMap<UndirectedGraphNode, UndirectedGraphNode> copied) {
        if (copied.containsKey(node)) return;
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        copied.put(node, newNode);
        for (UndirectedGraphNode nbr : node.neighbors) {
            cloneDFS(nbr, copied);
            newNode.neighbors.add(copied.get(nbr));
        }
    }
    
    private void cloneBFS(UndirectedGraphNode node, 
                          HashMap<UndirectedGraphNode, UndirectedGraphNode> copied) {
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
        copied.put(node, newNode);
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode nd = queue.poll();
            for (UndirectedGraphNode nbr : nd.neighbors) {
                if (copied.containsKey(nbr)) {
                    // if copied, do not add to queue to avoid infinite graph loop
                    copied.get(nd).neighbors.add(copied.get(nbr));
                } else {
                    newNode = new UndirectedGraphNode(nbr.label);
                    copied.put(nbr, newNode);
                    queue.offer(nbr);
                    // shoud be nd other than hbr
                    copied.get(nd).neighbors.add(newNode);
                }
            }
        }
    }
}