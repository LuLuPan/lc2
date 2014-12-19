/*
Given an array S of n integers, are there elements a, b, c in S 
such that a + b + c = 0? Find all unique triplets in the array 
which gives the sum of zero.

Note:
Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
    For example, given array S = {-1 0 1 2 -1 -4},

    A solution set is:
    (-1, 0, 1)
    (-1, -1, 2)


Solution: Sort and Move toward middle from left and right
          And use a set to deduplication
Time complexity: O(n^2)
 */
 public class Solution {
    public List<List<Integer>> threeSum(int[] num) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
        int n = num.length;
        if (n < 3) return result;
        // sort array
        Arrays.sort(num);
        HashSet<List<Integer>> set = new HashSet<List<Integer>>();
        for (int i = 0; i < n - 1; i++) {
        	int left = i + 1;
        	int right = n - 1;
            int target = -num[i];
        	while (left < right) {
        		int sum = num[left] + num[right];
        		if (sum == target) {
        			List<Integer> triple = new ArrayList<Integer>();
        			triple.add(num[i]);
        			triple.add(num[left]);
        			triple.add(num[right]);
        			if (set.add(triple))
        			    result.add(triple);
        			//Error: left and right need to change or infinite loop
        			// left and right change at the same time
        			// since if only chagne other, the other two are still
        			// the same, this will bring duplicated triple
        			left++;
        			right--;
        		}
        		else if (sum < target)
        			left++;
        		else
        			right--;
        	}
        }

        return result;
    }
}