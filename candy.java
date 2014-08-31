/*
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

Solution: Scan twice, find neighbors ratings relation 
*/
public class Solution {
    public int candy(int[] ratings) {
        int n = ratings.length;
        if (n == 0 || n == 1) return n;
        // at least n
        int result = n;
        int[] candies = new int[n];
        int candy = 1;
        // default is 0 for least one
        // Children with a higher rating get more candies than their neighbors.
        for (int i = 1; i < n; i++) {
        	if (ratings[i] > ratings[i - 1])
        		candies[i] = Math.max(candies[i], candy++);
        	else
        		candy = 1;
        }

        candy = 1;
        for (int i = n - 2; i >= 0; i--) {
        	if (ratings[i] > ratings[i + 1])
        		candies[i] = Math.max(candies[i], candy++);
        	else
        		candy = 1;
        }

        for (int i : candies)
        	result += i;
        return result;
    }
}