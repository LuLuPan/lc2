/*
Given an array of non-negative integers, you are initially positioned at the 
first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from 
index 0 to 1, then 3 steps to the last index.)

Solution: Greedy as Jump Game I
          But in order to get min step, only increase minstep when have to..
*/
public class Solution {
    public int jump(int[] A) {
        if (A == null || A.length == 0) return 0;
        int n = A.length;
        int steps = 0, reach = 0, last = 0;
        // check raechability
        for (int i = 0; i < n && i <= reach; i++) {
            if (i > last) {
                steps++;
                last = reach;
            }
            reach = Math.max(reach, A[i] + i);
        }
        // check raechability
        if (reach < n - 1) return 0;
        
        return steps;
    }
}