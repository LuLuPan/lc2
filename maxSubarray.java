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
        if (A == null || A.length == 0)
            return 0;
        int n = A.length;
        // global
        int max = A[0];
        // local
        int f = A[0];
        for (int i = 1; i < n; i++) {
            f = Math.max(f + A[i], A[i]);
            max = Math.max(max, f);
        }
        
        return max;
    }
}