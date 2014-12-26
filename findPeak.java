/*
A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
*/
public class Solution {
    public int findPeakElement(int[] num) {
        if (num == null || num.length == 0)
            return -1;
        int n = num.length;
        int start = 0;
        int end = n - 1;
        
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (start == end) 
                return start;
            else if (start == end - 1)
                return num[start] > num[end] ? start : end;
            else if ((num[mid] > num[mid - 1]) && (num[mid] > num[mid + 1]))
                return mid;
            else if (num[mid - 1] > num[mid])
                end = mid - 1;
            else start = mid + 1;
        }
        
        return -1;
    }
}