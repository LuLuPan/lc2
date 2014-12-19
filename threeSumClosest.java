/*
Given an array S of n integers, find three integers in S such that the sum 
is closest to a given number, target. Return the sum of the three integers. 
You may assume that each input would have exactly one solution.

    For example, given array S = {-1 2 1 -4}, and target = 1.

    The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

 */
 public class Solution {
    public int threeSumClosest(int[] num, int target) {
        int n = num.length;
        if (n < 3) return 0;
        // sort array
        Arrays.sort(num);
        int delta = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
        	int left = i + 1;
        	int right = n - 1;
        	while (left < right) {
        	    int sum = num[left] + num[right] + num[i];
        	    if (Math.abs(sum - target) < delta) {
        	    	delta = Math.abs(sum - target);
        	    	result = sum;
        	        // Error: different from 3 sum
        	        // still need to approaching to target at this point
        	        // so need to change left or right according to comparsion
        	    	//left++;
        	    	//right--;
        	    }
        	    
        	    if (sum < target)
        	    	left++;
        	    else right--;
        	}
        }

        return result;
    }
}

public class Solution {
    public int threeSumClosest(int[] num, int target) {
        if (num == null || num.length < 3)
            return 0;
        Arrays.sort(num);
        int n = num.length;
        int result = 0;
        int minGap = Integer.MAX_VALUE;
        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = num[i] + num[left] + num[right];
                // Error: Need to use abs
                int gap = Math.abs(target - sum);
                if (gap < minGap) {
                    minGap = gap;
                    result = sum;
                } else if (sum < target)
                    left++;
                else right--;
            }
        }
        
        
        return result;
    }
}