/*
Say you have an array for which the ith element is the price of a given
stock on day i.

If you were only permitted to complete at most one transaction 
(ie, buy one and sell one share of the stock), design an algorithm to 
find the maximum profit.

Solution: Greedy

*/
public class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;

        int n = prices.length;
        int min_price = Integer.MAX_VALUE;
        int max_profit = 0;
        for (int i = 1; i < n; i++) {
            min_price = Math.min(prices[i], min_price);
            max_profit = Math.max(max_profit, prices[i] - min_price);
        }

        if (max_profit < 0)
            max_profit = 0;
        return max_profit;
    }
}