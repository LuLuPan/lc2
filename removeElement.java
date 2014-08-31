/*
Given an array and a value, remove all instances of that value in place and 
return the new length.

The order of elements can be changed. It doesn't matter what you leave beyond 
the new length.

Solution: Array may not be sorted, similar to remove duplication in array
Corner Case: [1], 1
*/
public class Solution {
    public int removeElement(int[] A, int elem) {
        int n = A.length;
        if (n == 0 || n == 1) return n;
        int index = 0;
        for (int i = 0; i < n; i++) {
        	if (A[i] != elem)
        		A[index++] = A[i];
        }

        return index;
    }
}