/*
Follow up for "Search in Rotated Sorted Array":
What if [duplicates] are allowed?

Would this affect the run-time complexity? How and why?

Write a function to determine if a given target is in the array.
*/
public class Solution {
    public boolean search(int[] A, int target) {
        int n = A.length;
        if (n == 0) return false;

        int start = 0;
        int end = n - 1;
        while (start <= end) {
        	int mid = start + (end - start) / 2;
        	if (A[mid] == target) return true;
        	if (A[mid] > A[start]) {
        		// first half is incremental
        		if (target >= A[start] && target < A[mid])
        			end = mid - 1;
        		else
        			start = mid + 1;
        	} else if (A[mid] < A[start]) {
        		// second half is incremental
        		if (target > A[mid] && target <= A[end])
        			start = mid + 1;
        		else
        			end = mid - 1;
        	} else {
        		// skip duplication
        		start++;
        	}
        }

        return false;
    }
}