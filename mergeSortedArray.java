/*
Given two sorted integer arrays A and B, merge B into A as one sorted array.

Note:
You may assume that A has enough space (size that is greater or equal to m + n)
to hold additional elements from B. The number of elements initialized in A and
B are m and n respectively.


Solution: Reverse scan, put bigger one in A and B to the right side of A,
          compare from right to left.
          If B still has element left, copy them into A.
Corner case: A or B is empty
*/
public class Solution {
    public void merge(int A[], int m, int B[], int n) {
        int i = m - 1;
        int j = n - 1;
        while (i >= 0 && j >= 0) {
            if (A[i] > B[j])
                A[i + j + 1] = A[i--];
            else
                A[i + j + 1] = B[j--];
        }
        
        while (j >= 0)
            A[j] = B[j--];
    }
}