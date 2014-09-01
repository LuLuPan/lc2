/*
There are N gas stations along a circular route, where the amount of gas at 
station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel 
from station i to its next station (i+1). You begin the journey with an empty 
tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit 
once, otherwise return -1.

Note:
The solution is guaranteed to be unique.

Solution:
1. Emunate: left[i+1] = left[i] + gas[i] - cost[i]
            left[i] >= 0
            if left[i] < 0, cur start cannot make a while cycle
2. http://blog.csdn.net/linhuanmars/article/details/22706553
   If a negative path occurred, all points in the path cannot be a start.
   If total gas is positive, then there must be a start point could make it.
   Maintain total gas and current path's sum gas, if sum gas become negative,
   start from next point. If at the end, total is positive, get the start.
*/
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0 || cost == null || 
        	cost.length == 0 || cost.length != gas.length)
        	return -1;

        int total = 0;
        int sum = 0;
        int result = -1;
        for (int i = 0; i < gas.length; i++) {
        	int diff = gas[i] - cost[i];
        	sum += diff;
        	total += diff;
        	if (sum < 0) {
        		sum = 0;
        		// Error: not good to put i + 1 
        		result = i;
        	}
        }
        // Error: should be result + 1
        // For [5], [4], result still be -1.
        // For [2], [2], total could be 0
        return (total >= 0) ? result + 1: -1;
    }
}