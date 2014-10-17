/*
Given that integers are read from a data stream. Find median of elements read so
for in efficient way. For simplicity assume there are no duplicates. 
For example, let us consider the stream 5, 15, 1, 3 …
After reading 1st element of stream - 5 -> median - 5
After reading 2nd element of stream - 5, 15 -> median - 10
After reading 3rd element of stream - 5, 15, 1 -> median - 5
After reading 4th element of stream - 5, 15, 1, 3 -> median - 4, so on...
Making it clear, when the input size is odd, we take the middle element of 
sorted data. If the input size is even, we pick average of middle two elements 
in sorted stream.
Note that output is effective median of integers read from the stream so far. 
Such an algorithm is called online algorithm. Any algorithm that can guarantee 
output of i-elements after processing i-th element, is said to be online algorithm.

*/

/*
Solution:
Step 1: Add next item to one of the heaps

   if next item is smaller than maxHeap root add it to maxHeap,
   else add it to minHeap

Step 2: Balance the heaps (after this step heaps will be either balanced or
   one of them will contain 1 more item)

   if number of elements in one of the heaps is greater than the other by
   more than 1, remove the root element from the one containing more elements and
   add to the other one

   If the heaps contain equal elements;
     median = (root of maxHeap + root of minHeap)/2
   Else
     median = root of the heap with more elements
*/
public class Solution {
	public float streamingMedia(int[] num) {
    	float median = 0;
    	
    	if (num == null) return 0;
    	
    	PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    	PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
    	
    	// first two number
    	maxHeap.offer(5);
    	minHeap.offer(15);
    	
    	for (int i = 0; i < num.length; i++) {
    		if (num[i] > maxHeap.peek())
    			minHeap.offer(num[i]);
    		else
    			maxHeap.offer(num[i]);
    		
    		int diff = maxHeap.size() - minHeap.size();
    		while (Math.abs(diff) > 1) {
    			// balance two heap
    			if (diff > 0) {
    				minHeap.offer(maxHeap.poll());
    			} else {
    				maxHeap.offer(minHeap.poll());
    			}
    			diff = maxHeap.size() - minHeap.size();
    		}
    	}
    	
        int size = maxHeap.size() + minHeap.size();
        if (size % 2 == 1) {
        	median = maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
        } else {
        	median = (float) ((float)(maxHeap.peek() + minHeap.peek()) / 2.0);
        }
        
    	return median;
    }
    
}