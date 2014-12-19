/*
Given a sorted array, remove the duplicates in place such that each element 
appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with 
constant memory.

For example,
Given input array A = [1,1,2],

Your function should return length = 2, and A is now [1,2].
*/
public class Solution {
    public int removeDuplicates(int[] A) {
        int n = A.length;
        if (n < 2) return n;
        // index points to the position of the firse element
        // with the same value, when a different value found,
        // ++index to leave only one this element
        int index = 0;
        for (int i = 1; i < n; i++) {
        	if (A[index] != A[i])
        		A[++index] = A[i];
        }
        // Error: return index + 1
        return index + 1;
    }
}