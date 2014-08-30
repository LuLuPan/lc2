/*
Given an array of integers, find two numbers such that they add up to a 
specific target number.

The function twoSum should return indices of the two numbers such that they 
add up to the target, where index1 must be less than index2. 
Please note that your returned answers (both index1 and index2) 
are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2

Solution 1: Sort and move to middle from left and right
Complexity: O(nlogn) since sorting and O(1)
But sort will change original index, so cannot use this method

Solution 2: HashMap<value, index>
            scan once, delta = target - num
            if HashMap contains delta, then pair found
*/
public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int n = numbers.length;
        if (n == 0 || n == 1) return result;

        HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++)
            indexMap.put(numbers[i], i);

        for (int i = 0; i < n; i++) {
            int delta = target - numbers[i];
            if (indexMap.containsKey(delta)) {
                if (indexMap.get(delta) != i) {
                    result[0] = i + 1;
                    result[1] = indexMap.get(delta) + 1;
                    return result;
                }
            }
        }

        return result;
    }
}