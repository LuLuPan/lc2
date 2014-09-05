/*
Given an array of integers, every element appears twice except for one. 
Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement 
it without using extra memory?

Solution: A XOR A = 0, A XOR B XOR A = B.
*/
public class Solution {
    public int singleNumber(int[] A) {
        int result = 0;
        for (int i = 0; i < A.length; i++) {
        	result ^= A[i];
        }

        return result;
    }
}