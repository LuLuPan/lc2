/*
Given an array of integers, every element appears three times except for one. 
Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement 
it without using extra memory?

Solution:
Using binary to represent integer.
Since all num are interger, create an array with 32.
Accumulate on each bit
For certain bit, if the number is not multiple of 3, then the single one
contains this bit
*/
public class Solution {
    public int singleNumber(int[] A) {
        int[] count = new int[Integer.SIZE];

        for (int i = 0; i < A.length; i++) {
        	for (int j = 0; j < Integer.SIZE; j++)
        		count[j] += (A[i] >> j) & 1;
        }
        int result = 0;
        for (int i = 0; i < Integer.SIZE; i++) {
        	result += (count[i] % 3) << i;
        }
        return result;
    }
}