/*
Say you have an array for which the ith element is the price of a 
given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most 
two transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must 
sell the stock before you buy again).

Solution: DP
          Pmax = max{f(0...i) + g(i...n-1)}, 0<=i<=n-1
          Two directions scan to get all max values for f(0..i) and g(i..n-1)
Corner case:
Complextity:
*/
public class Solution {
    public int maxProfit(int[] prices) {
        int result = 0;
        if (prices.length == 0) return result;
        int n = prices.length;
        int[] f = new int[n];
        f[0] = 0;
        for (int i = 1, min_price = prices[0]; i < n; i++) {
            min_price = Math.min(min_price, prices[i]);
            f[i] = Math.max(f[i - 1], prices[i] - min_price);
        }

        for (int i = n - 2, max_price = prices[n - 1]; i >= 0; i--) {
            max_price = Math.max(max_price, prices[i]);
            int profit = max_price - prices[i];
            profit = profit > 0 ? profit : 0; // g[i]
            result = Math.max(result, profit + f[i]);
        }

        return result;
    }
}