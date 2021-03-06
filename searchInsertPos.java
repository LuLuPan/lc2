/*
Given a sorted array and a target value, return the index if the target is 
found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0

Solution: Same as lower_bound
Corner case: smaller than first one or bigger than last one
*/

public class Solution {
    public int searchInsert(int[] A, int target) {
        int n = A.length;
        if (n == 0) return 0;

        int start = 0;
        int end = n - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;

            if (A[mid] < target)
                start = mid + 1;
            else
                end = mid;
        }

        /*
        start is the lower bound for target, A[starts] supposed to be <= target

        if A[start] == target ,return start
        if A[start] < target, return start + 1
        if A[start] > target, which means target doesn't exist, return start
        */
        if (A[start] >= target)
            return start;
        else
            return start + 1;
    }
}

public class Solution {
    public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0) return 0;
        int start = findBound(A, 0, A.length - 1, target);
        if (A[start] >= target)
            return start;
        else
            return start + 1;
    }
    
    private int findBound(int[] A, int start, int end, int target) {
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] < target)
                start = mid + 1;
            else end = mid;
        }
        
        return start;
    }
}