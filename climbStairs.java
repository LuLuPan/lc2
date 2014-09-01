/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. 
In how many distinct ways can you climb to the top?

Solution: DP
f(n) = f(n - 1) + f(n - 2)
*/
public class Solution {
    public int climbStairs(int n) {
        if (n == 0 || n == 1) return n;
        int fn0 = 1;
        int fn1 = 1;
        int fn = 0;
        for (int i = 2; i <= n; i++) {
        	fn = fn0 + fn1;
        	fn0 = fn1;
        	fn1 = fn;
        }
        return fn;
    }
}