/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, 
otherwise return -1.

You may assume no duplicate exists in the array.

Solution: Binary search with compare to find its relative position to these
          two halves
Complexity: O(logn) + O(1)
*/
public class Solution {
    public int search(int[] A, int target) {
        int n = A.length;
        return bsearch(A, 0, n - 1, target);
    }

    private int bsearch(int[] A, int start, int end, int target) {
        // Error: start >= end. since if input is [1]...
     	if (start > end) return -1;
    	int mid = start + (end -start) / 2;
    	if (A[mid] == target) return mid;
    	else if (A[start] > A[mid]) {
    		// 1st > mid, rotate poit is at first half
    		// second half is incremental
    		if (target > A[mid] && target <= A[end])
    			return bsearch(A, mid + 1, end, target);
    		else
    			return bsearch(A, start, mid - 1, target);
    	} else {
    		// first half is incremental
    		if (target >= A[first] && target < A[mid])
    			return bsearch(A, start, mid - 1, target);
    		else
    			return bsearch(A, mid + 1, end, target);
    	}
    }
}