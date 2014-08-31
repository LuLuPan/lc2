/*
Given an unsorted array of integers, find the length of the longest 
consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.

Solution: Since O(n) required, cannot sort
          Put all elements into HashMap and record if it exists and used
          For one num, search its num - 1, num-2.. and num + 1, num+2, etc
          if one of them exists, keep extending
Complexity: O(n) + O(n)
*/
public class Solution {
    public int longestConsecutive(int[] num) {
        int n = num.length;
        if (n == 0 || n == 1) return n;

        HashMap<Integer, Boolean> numMap = new HashMap<Integer, Boolean>();
        for (int i = 0; i < n; i++)
        	numMap.put(num[i], false);

        int result = 1;
        for (int i : num) {
        	if (numMap.get(i) == true)
        		continue;
        	
        	numMap.put(i, true);
        	int length = 1;
        	for (int j = 1 + i; numMap.containsKey(j); j++) {
        	    numMap.put(j, true);
        		length++;
        	}

        	for (int j = i - 1; numMap.containsKey(j); j--) {
        	    numMap.put(j, true);
        		length++;
        	}

        	result = Math.max(result, length);
        }

        return result;
    }
}