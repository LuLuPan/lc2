/*
Given an array of size n, find the majority element. 
The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always 
exist in the array.

Solution:
Moore's voting algorithm
http://www.geeksforgeeks.org/majority-element/
Use inequal elements to elimiate candidates, if still could exist count != 0
then it is candidate

Moors's voting could only find majority if it is more than n / 2
otherwise a incorrect element will be found
so need extra check to find a majority element exists or not at all

*/

public class Solution {
    public int majorityElement(int[] num) {
        if (num == null || num.length == 0) return -1;
        int result = findCandidate(num);
        if (checkCandidate(num, result) == true)
            return result;
        else
            return -1;
    }
    
    private int findCandidate(int[] num) {
        int count = 1;
        int majIdx = 0;
        for (int i = 1; i < num.length; i++) {
            if (num[majIdx] == num[i])
                count++;
            else
                count--;
            if (count == 0) {
                count = 1;
                majIdx = i;
            }
        }
        
        return num[majIdx];
    }
    
    private boolean checkCandidate(int[] num, int candidate) {
        int size = 0;
        for (int i : num) {
            if (i == candidate)
                size++;
        }
        
        return size > num.length / 2;
    }
}
