/*
There are two sorted arrays A and B of size m and n respectively. 
Find the median of the two sorted arrays. 
The overall run time complexity should be O(log (m+n)).

Solution:
Order Statistics:
http://blog.csdn.net/linhuanmars/article/details/19905515
Find kth in two sorted arrays

Compare A[k/2 - 1] with B[k/2 - 1]
Since A[0]..A[k/2 - 1] and B[0]..B[k/2 - 1] has first k element among A and B

A[k/2-1] == B[k/2-1], return A[k/2-1] or B[k/2-1]
A[k/2-1] > B[k/2-1], kth will not be within B[0]..B[k/2-1] since first k/2
                     element in B has less weight than A's
                     So B[0]..B[k/2-1] will be ignored
A[k/2-1] < B[k/2-1]. similar

*/
public class Solution {
    public double findMedianSortedArrays(int A[], int B[]) {
    	int m = A.length;
    	int n = B.length;
    	if ((m + n) % 2 == 1)
    		return findKth(A, B, 0, m - 1, 0, n - 1, (m + n) / 2 + 1);
    	else
    		// Error / 2. 0 since double
    		return (findKth(A, B, 0, m - 1, 0, n - 1, (m + n) / 2 ) +
    			    findKth(A, B, 0, m - 1, 0, n - 1, (m + n) / 2 + 1)) / 2.0;
    }

    private int findKth(int A[], int B[], int startA, int endA, int startB, 
    	int endB, int k) {
    	int m = endA - startA + 1;
    	int n = endB - startB + 1;
    	// by default, A is shorter than B
    	if (m > n) return findKth(B, A, startB, endB, startA, endA, k);
    	if (m == 0) return B[startB + k - 1];
    	if (k == 1) return Math.min(A[startA], B[startB]);
    	// Notice: m could be smaller than k / 2
    	int ia = Math.min(m, k / 2);
    	int ib = k - ia;

    	if (A[startA + ia - 1] > B[startB + ib - 1])
    		// B's first k/2 will be discarded, and only check A's first k/2
    		return findKth(A, B, startA, startA + ia - 1, 
    			startB + ib, endB, k - ib);
    	else if (A[startA + ia - 1] < B[startB + ib - 1])
    		// A's first k/2 will be discarded, and only check B's first k/2
    		return findKth(A, B, startA + ia, endA, 
    			startB, startB + ib - 1, k - ia);
    	else return A[ia - 1];
    }
}