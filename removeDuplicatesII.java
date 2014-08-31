/*
Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array A = [1,1,1,2,2,3],

Your function should return length = 5, and A is now [1,1,2,2,3].
*/
public class Solution {
    public int removeDuplicates(int[] A) {
    	int n = A.length;
        if (n < 3) return n;

        int index = 2;
        for (int i = 2; i < n; i++) {
        	if (A[index - 2] != A[i])
        		A[index++] = A[i];
        }

        return index;
    }
}