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
        if (A == null || A.length == 0) return -1;
        int result = 0;
        for (int i : A)
            result ^= i;
            
        return result;
    }
}