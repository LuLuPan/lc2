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
        HashMap<UndirectedGraphNode, UndirectedGraphNode> copied = 
            new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        clone_graph_dfs(node, copied);
        return copied.get(node);
    }

    private void clone_graph_dfs(UndirectedGraphNode node, 
        HashMap<UndirectedGraphNode, UndirectedGraphNode> copied)
    {
        if (copied.containsKey(node))
            return;

        UndirectedGraphNode new_nd = new UndirectedGraphNode(node.label);
        copied.put(node, new_nd);
        for (UndirectedGraphNode nd : node.neighbors) {
            clone_graph_dfs(nd, copied);
            new_nd.neighbors.add(copied.get(nd));
        }
    }

    private void clone_graph_bfs(UndirectedGraphNode node, 
        HashMap<UndirectedGraphNode, UndirectedGraphNode> copied)
    {
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        UndirectedGraphNode new_nd = new UndirectedGraphNode(node.label);
        copied.put(node, new_nd);
        queue.offer(node);

        while (!queue.isEmpty()) {
            UndirectedGraphNode nd = queue.poll();
            for (UndirectedGraphNode hbr : nd.neighbors) {
                if (copied.containsKey(hbr)) {
                    copied.get(nd).neighbors.add(copied.get(hbr));
                } else {
                    new_nd = new UndirectedGraphNode(hbr.label);
                    copied.put(hbr, new_nd);
                    queue.offer(hbr);
                    // shoud be nd other than hbr
                    copied.get(nd).neighbors.add(new_nd);                    
                }
            }
        }
    }
}