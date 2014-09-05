/*
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
*/
public class Solution {
    public String countAndSay(int n) {

        if (n == 0) return new String();
        // Error: i Should be 1
        // since result is already one
        int i = 1;
        String result = new String("1");
        while (i < n) {
        	StringBuilder sb = new StringBuilder();
        	char[] charArr = result.toCharArray();
        	int dup = 1;
        	for (int j = 1; j < charArr.length; j++) {
        		if (charArr[j - 1] == charArr[j])
        			dup++;
        		else {
        			sb.append(dup);
        			sb.append(charArr[j - 1]);
        			dup = 1;
        		}
        	}
        	sb.append(dup);
        	sb.append(charArr[charArr.length - 1]);

        	result = sb.toString();
        	i++;
        }

        return result;
    }
}