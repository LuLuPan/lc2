/*
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true


Solution:
1. Automata
   Analyze all possible inputs and combinations and trade them as states
   Build states transfer table
2. Straitforward check:
   Put character check at where it is valid and could handle, 
   otherwise, it is invalid
*/
public class Solution {
    public enum input_type {
        INVALID, 
        SPACE, // ' '
        SIGN,  // '+/-'
        DIGIT, // '0-9'
        DOT,   // '.'
        EXP,   // 'e/E'
        INPUT_NUM
    }
    static int[][] state_table = {
        {-1,  0,  3,  1,  2, -1},//state when no input or just space  
        {-1,  8, -1,  1,  4,  5},//state after input digits  
        {-1, -1, -1,  4, -1, -1},//state after input dot and no digit before  
        {-1, -1, -1,  1,  2, -1},//state after input sign and no digit before  
        {-1,  8, -1,  4, -1,  5},//state with digits and dot before  
        {-1, -1,  6,  7, -1, -1},//state after input E
        {-1, -1, -1,  7, -1, -1},//state after input E and followed by sign  
        {-1,  8, -1,  7, -1, -1},//state after input E and followed by digit  
        {-1,  8, -1, -1, -1, -1}//state after valid input and followed space
    };
    public boolean isNumber(String s) {
        int n = s.length();
        if (n == 0) return false;
        input_type input = input_type.INVALID;
        int state = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '.')
                input = input_type.DOT;
            else if (c == 'E' || c == 'e')
                input = input_type.EXP;
            else if (c == '-' || c == '+')
                input = input_type.SIGN;
            else if (c == ' ')
                input = input_type.SPACE;
            else if (c >= '0' && c <= '9')
                input = input_type.DIGIT;
            else
                input = input_type.INVALID;

            state = state_table[state][input.ordinal()];
            if (state == -1)
                return false;
        }

        return state == 1 || state == 4 || state == 7 || state == 8;
    }
}

// Typical case: -123.E+5
// Valid combinations:
// " | +/- | 0-9 | . | 0-9 | e/E | +/- | 0-9 |"

class Solution {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) return false;
        int i = 0;
        // skip spaces
        while (i < s.length() && s.charAt(i) == ' ')
            i++;
        
        if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-'))
            i++;
        // get digits number 1
        int numDigits1 = skipDigits(i, s);
        i += numDigits1;

        if (i < s.length() && s.charAt(i) == '.')
            i++;
        // get digits number 2
        int numDigits2 = skipDigits(i, s);
        i += numDigits2;
        // invalid:
        // 1) no digits after "+/-" 
        // 2) no digits before and after '.'
        if (numDigits1 + numDigits2 == 0) return false;

        if (i < s.length() && (s.charAt(i) == 'e' || s.charAt(i) == 'E')) {
            i++;
            // second "+/-" should show just after 'e/E'
            if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-'))
                i++;
            int numDigits3 = skipDigits(i, s);
            i += numDigits3;
            // invalid checked:
            // 1) somthing between "e/E" and second "+/-" 
            // 2) if no digits after second "+/-"
            // 3) no digits after "e/E"
            if (numDigits3 == 0) return false;
        }
        // skip spaces
        while (i < s.length() && s.charAt(i) == ' ')
            i++;

        // invalid cases could handle here
        // 1) more than twice "+/-"
        // 2) second "+/-" show before "e/E"
        // 3) digits show before the first "+/-"
        // 4) more than one "e/E"
        // 5) more than one "."
        return i == s.length();
    }

    private int skipDigits(int i, String s) {
        int num = 0;
        while (i < s.length() && isDigits(s.charAt(i))) {
            i++;
            num++;
        }
        return num;
    }

    private boolean isDigits(char c) {
        if (c >= '0' && c <= '9')
            return true;
        return false;
    }
}