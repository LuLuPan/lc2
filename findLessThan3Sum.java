/*
 * x + y + z <= target
 */
 public class Solution {
    public List<List<Integer>> findLessThan3Sum(int[] num, int target) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if (num == null || num.length < 3) return result;
    	
    	int n = num.length;
    	Arrays.sort(num);
    	// x from left to right
    	// z from right to left
        // y from left to z until sum > target
        // O(n^3)
    	for (int i = 0; i < n - 2; i++) {
    		for (int j = n - 1; j > i + 1; j--) {
    			int left = i;
    			int right = j;
    			int sumTwo = num[left] + num[right];
    			int k = i + 1;
    			for (; k <= j - 1; k++) {
    				if (num[k] + sumTwo > target)
    					break;
    			}
    			
    			for (int p = i + 1; p < k; p++) {
    				Integer[] arr = {num[left], num[p], num[right]};
    				List<Integer> list = Arrays.asList(arr);
    				if (!result.contains(list))
    					result.add(list);
    			}
    		}
    	}
    	
    	return result;
    }
}