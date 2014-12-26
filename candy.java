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
        if (ratings == null || ratings.length == 0)
            return 0;
        int n = ratings.length;
        int[] increment = new int[n];
        for (int i = 1, inc = 0; i < n; i++) {
            if (ratings[i] > ratings[i - 1])
                increment[i] = Math.max(increment[i], ++inc);
            else
                inc = 0;
        }
        
        for (int i = n - 2, inc = 0; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1])
                increment[i] = Math.max(increment[i], ++inc);
            else
                inc = 0;
        }
        
        int sum = 0;
        for (int i : increment)
            sum += i;
        return sum + n;
    }
}