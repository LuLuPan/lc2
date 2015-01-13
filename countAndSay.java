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

        List<Integer> prev = new ArrayList<Integer>();
        prev.add(1);
        for (int i = 2; i <= n; i++) {
            int count = 1;
            List<Integer> cur = new ArrayList<Integer>();
            for (int j = 1; j < prev.size(); j++) {
                if (prev.get(j) == prev.get(j - 1))
                    count++;
                else {
                    cur.add(count);
                    cur.add(prev.get(j - 1));
                    count = 1;
                }
            }
            
            // last characters that cannot be added in the loop
            cur.add(count);
            cur.add(prev.get(prev.size() - 1));
            prev = cur;
        }
        StringBuilder result = new StringBuilder();
        for (int i : prev) 
            result.append(i);
            
        return result.toString();
    }
}

public class Solution {
    public String countAndSay(int n) {
        if (n < 1) return "";
        StringBuilder result = new StringBuilder();
        List<Integer> prev = new ArrayList<Integer>();
        prev.add(1);
        for (int i = 2; i <= n; i++) {
            List<Integer> cur = new ArrayList<Integer>();
            int count = 1;
            for (int j = 1; j <= prev.size(); j++) {
                if (j != prev.size() && prev.get(j) == prev.get(j - 1))
                    count++;
                else {
                    cur.add(count);
                    cur.add(prev.get(j - 1));
                    count = 1;
                }
            }
            
            prev = cur;
        }
        
        for (int i : prev)
            result.append(i);
            
        return result.toString();
    }
}