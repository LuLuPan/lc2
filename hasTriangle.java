public class Solution {
    public boolean hasTriangle(int edges[]) {
    	if (edges == null || edges.length < 3)
    		return false;
    	
    	Arrays.sort(edges);
    	
    	/*
    	 * Triangle condition: two edges sum large than the third edge
    	 *  1) a[i] + a[left] > a[right]
         *  2) a[i] + a[right] > a[left], fulfilled after sort
    	 *  3) a[left] + a[right] > a[i], fulfilled after sort
    	 *  So focus on 1) a[i] > a[right] - a[left]
    	 *  For the edges larger than a[i], a[i] should be larger than min(a[right] - a[left])
    	 *  the min of a[right] - a[left] will only exist in the adjacent two position in (i, n-1]
    	 *  So
    	 *  (1) Generate a gap array of adjacent two position gap[i] = a[i+1] - a[i]
    	 *  (2) i start from n - 3 to 0, compare with gap[i+1],
    	 *       if a[i] <= gap[i+1], it means other edges at the left of a[i] < gap[i+1],
    	 *       so gap[i+1] will not be included in next round, move to left gap
    	 */
    	int n = edges.length;
    	int[] gap = new int[n - 1];
    	for (int i = n - 1; i >= 1; i--)
    		gap[i - 1] = edges[i] - edges[i - 1];
    	for (int i = n - 3; i >= 0; i--) {
    		if (edges[i] <= gap[i + 1])
    			continue;
    		return true;
    	}
    	
    	return false;
    }
}