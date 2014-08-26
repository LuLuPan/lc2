/*
Find the contiguous subarray within an array (containing at least one number)
which has the largest sum.

For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
the contiguous subarray [4,−1,2,1] has the largest sum = 6.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution using the
divide and conquer approach, which is more subtle.

Solution: DP
          f(j) = max{f(j-1) + A[j], A[j]}
          f(j) is the max subarry sum terminated at index j
Corner case: [-1]

*/
public class Solution {
    public int maxSubArray(int[] A) {
        // since may compare with negative, use int_min other than 0
        int max_sum = Integer.MIN_VALUE;
        int f = 0; //f(j)
        for (int i = 0; i < n; i++) {
            f = Math.max(f + A[i], A[i]);
            max_sum = Math.max(max_sum, f);
        }

        return max_sum;
    }
}