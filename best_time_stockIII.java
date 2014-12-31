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
        // 1st transaction before the ith day
        for (int i = 1, min_price = prices[0]; i < n; i++) {
            min_price = Math.min(min_price, prices[i]);
            f[i] = Math.max(f[i - 1], prices[i] - min_price);
        }

        // 2nd transaction after the ith day
        for (int i = n - 2, max_price = prices[n - 1]; i >= 0; i--) {
            max_price = Math.max(max_price, prices[i]);
            int profit = max_price - prices[i];
            profit = profit > 0 ? profit : 0; // g[i]
            result = Math.max(result, profit + f[i]);
        }

        return result;
    }
}


/*
if transaction is k times other than only two times
Use general DP solition with local optimal result
and global optimal result
*/

public class Solution {
    public int maxProfit(int[] prices, int k) {
        if (prices.length <= 1) return 0;
        int len = prices.length;
        int[][] local = new int[len][k + 1];
        int[][] global = new int[len][k + 1];
       
        for (int i = 1; i < len; i++) {
            int diff = prices[i] - prices[i - 1];
            
            for (int j = 1; j <= k; j++) {
                // local[i][j]: max profit till i day, j transactions, where there is transaction happening on i day
                // local[i-1][j] must include transaction on i-1 day, so plus today's transaction will increase number
                // of transactions, since p[i - 1] - p[i-2] + p[i] - p[i - 1] is the same as p[i]-p[i-2]
                local[i][j] = Math.max(global[i - 1][j - 1] + Math.max(diff, 0), 
                                           local[i - 1][j] + diff);
                // global[i][j]: max profit across i days, j transactions                             
                global[i][j] = Math.max(global[i - 1][j], local[i][j]);                
            }
        }
        
        return global[len - 1][k];
    }
}