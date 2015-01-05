/*
Given two strings S and T, determine if they are both one edit distance apart
*/
/*
1.  If S matches all characters in T, then check if there is an extra character at the end 
of T. (Modify operation)
2.  If | n – m | == 1, that means we must skip this non-matching character only in T
and make sure the remaining characters between S and T are exactly matching. 
(Insert operation)
3.  If | n – m | == 0, then we skip both non-matching characters in S and T and make 
sure the remaining characters between S and T are exactly matching. (Append
operation)
*/
public boolean isOneEditDistance(String s, String t) {
    int m = s.length(), n = t.length();
    if (m > n) return isOneEditDistance(t, s);
    if (n - m > 1) return false;
    int i = 0, shift = n - m;
    while (i < m && s.charAt(i) == t.charAt(i)) i++;
    // Insert at the end of T? Othwise S == T, distance is 0
    if (i == m) return shift > 0;
    // Replace?
    if (shift == 0) i++;
    // Insert?
    while (i < m && s.charAt(i) == t.charAt(i + shift)) i++;
    return i == m;
}