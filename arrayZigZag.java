/*
A sequence of numbers is called a zig-zag sequence if the differences between 
successive numbers strictly alternate between positive and negative. 
The first difference (if one exists) may be either positive or negative. 
A sequence with fewer than two elements is trivially a zig-zag sequence.

For example, 1,7,4,9,2,5 is a zig-zag sequence because the differences 
(6,-3,5,-7,3) are alternately positive and negative. In contrast, 1,4,7,2,5 
and 1,7,4,5,5 are not zig-zag sequences, the first because its first two 
differences are positive and the second because its last difference is zero.

Given a sequence of integers, sequence, return the length of the longest 
subsequence of sequence that is a zig-zag sequence. A subsequence is obtained 
by deleting some number of elements (possibly zero) from the original sequence, 
leaving the remaining elements in their original order.
*/
public int arrayZigZag(int[] A) {
    if (A == null || A.length < 3) return 0;
    int n = A.length;
    // dp[0][i] means 0 to i, zigzag length which last two element's diff is +
    // dp[1][i] means 0 to i, zigzag length which last two element's diff is -
    int[][] dp = new int[2][n];
    for (int i = 0; i  < n; i++) {
        dp[0][i] = dp[1][i] = 1;
        
        for (int j = 0; j < i; j++) {
            if (A[i] > A[j]) // diff is +
                dp[0][i] = Math.max(dp[1][j] + 1, dp[0][i]);
            else if (A[i] < A[j]) // diff is -
                dp[1][i] = Math.max(dp[0][j] + 1, dp[1][i]); 
        }
    }
    
    return Math.max(dp[0][n - 1], dp[1][n - 1]);
}
        