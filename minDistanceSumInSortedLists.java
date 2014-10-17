public class Solution {
    /*
	 * a: a1, a2....
	 * b: b1, b2, ...
	 * c: c1, c2, ....
	 * Min(|b - a| + |a - c| + |c - b|)
	 * Solution: |x - y| + |y - z| + |z -x| => 2*(Max(x, y, z) - Min(x, y, z))
	 */
	
	public int minDistanceSum(List<List<Integer>> lists) {
		int minSum = Integer.MAX_VALUE;
		if (lists == null || lists.size() < 2) return minSum;
		
		Iterator<List<Integer>> iter = lists.iterator();
		List<PriorityQueue<Integer>> heaps = new ArrayList<PriorityQueue<Integer>>();
		while (iter.hasNext()) {
			List<Integer> list = iter.next();
			PriorityQueue<Integer> heap = new PriorityQueue<Integer>(list);
			heaps.add(heap);
		}
		
		while (true) {
    	    int minTop = Integer.MAX_VALUE;
    	    int maxTop = Integer.MIN_VALUE;
    	    PriorityQueue<Integer> smallHeap = null;
    	    for(PriorityQueue<Integer> heap : heaps) {
    	    	if (heap.peek() < minTop) {
    	    		minTop = heap.peek();
    	    		smallHeap = heap;
    	    	}
    	    	if (heap.peek() > maxTop) {
    	    		maxTop = heap.peek();
    	    	}
    	    }
    		
    	    if (maxTop - minTop < minSum)
    	    	minSum = maxTop - minTop;
    	    
    	    smallHeap.poll();
    	    if (smallHeap.size() == 0)
    	    	break;
		}
	    
		return minSum;
	}
}