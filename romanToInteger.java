/*
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
*/
public class Solution {
    private final HashMap<Character, Integer> map = new HashMap<Character, Integer>() {{
                       put('I', 1);
                       put('V', 5);
                       put('X', 10);
                       put('L', 50);
                       put('C', 100);
                       put('D', 500);
                       put('M', 1000);
                   }};
                   
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) return 0;
        int result = 0;
        int prev = 0;
        for (char c : s.toCharArray()) {
            int cur = map.get(c);
            // if subtractive notation, deduct twice since prev has been added
            // once previously
            result += (cur > prev ? cur - 2 * prev : cur);
            prev = cur;
        }
        
        return result;
    }
}