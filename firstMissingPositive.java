/*
Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.

Solution: Based on bucket sort
If want to find fist missing positive, could rerange element and try to
make them A[i] == i + 1.
If cannot make all element obey this rule, then return first voliation.
If all obey this rule, then return n + 1

Corner case:
*/


public class Solution {
    public int firstMissingPositive(int[] A) {
        int n = A.length;
        if (n == 0) return 1;

        for (int i = 0; i < n; i++) {
            while (A[i] != i + 1) {
                if (A[i] <= 0 || A[i] > n || A[i] == A[A[i] - 1])
                    break; 
                //swap A[i] and A[A[i] - 1]
                int tmp = A[i];
                A[i] = A[tmp - 1];
                A[tmp - 1] = tmp;
            }
        }

        for (int i = 0; i < n; i++)
            if (A[i] != i + 1)
                return i + 1;

        return n + 1;
    }
}