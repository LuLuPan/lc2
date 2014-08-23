/*
Given an array of non-negative integers, you are initially positioned at 
the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

Solution: Greedy: for i, it could reach index A[i] + i, compare it with previous
          'reach' means the max index of some previous postition could reach,
          if current pos cannot reach that far, 'reach' keeps unchanged,
          or it will be replaces by cur pos' range.
          At lease, 'reach' should long enough to arrive cur pos. 

          DP: f[i] = max(f[i - 1], A[i - 1]) - 1, if f[i] < 0, then cannot make
          
Complexity: O(n)

Corner case: start with 0: 0 1 2

*/

public class Solution {
    public boolean canJump(int[] A) {
        if (A.length == 0) return false;
        int n = A.length;
        // max index could be reached
        int reach = 0;
        // Error: intermediate stage needs condition: i <= reach
        for (int i = 0; i < n && i <= reach; i++) {
            reach = Math.max(reach, A[i] + i);
        }

        return reach >= n - 1;
    }
}

public class Solution {
    public boolean canJump(int[] A) {
        if (A.length == 0) return false;
        int n = A.length;
        int[] f = new int[n];
        f[0] = 0;
        for (int i = 1; i < n; i++) {
            f[i] = Math.max(f[i - 1], A[i - 1]) - 1;
            // Notice!
            if (f[i] < 0)
                return false;
        }
        return true;
    }
}