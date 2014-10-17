class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) { 
        label = x; 
        neighbors = new ArrayList<UndirectedGraphNode>(); 
    }
}

public class Solution {
	private boolean hasTriangle(UndirectedGraphNode nbr1, UndirectedGraphNode nbr2) 
	{
		Iterator<UndirectedGraphNode> iter = nbr1.neighbors.iterator();
		while (iter.hasNext()) {
			(iter.next() == nbr2)
			    return true;
		}

		return false;
	}
	public int triangleInGraph(UndirectedGraphNode node) {
		int result = 0;
		if (node == null) return result;
		Set<UndirectedGraphNode> visited = new HashSet<UndirectedGraphNode>();
		Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
		queue.offer(node);
		visited.add(node);
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				UndirectedGraphNode nbr = queue.poll();
				if (!visited.add(nbr))
					continue;
			}
		}
        return result;
	}
}