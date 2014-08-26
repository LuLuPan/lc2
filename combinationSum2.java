/*
Given a [collection] of candidate numbers (C) and a target number (T), 
find all unique combinations in C where the candidate numbers sums to T.

Each number in C may only be used once in the combination.

Note:
All numbers (including target) will be positive integers.
Elements in a combination (a1, a2, … , ak) must be in non-descending order.
 (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations.
For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
A solution set is: 
[1, 7] 
[1, 2, 5] 
[2, 6] 
[1, 1, 6] 

Solution: Same as combination Sum I. But need to avoid duplication.
Complexity:
Corner case:
*/
public class Solution {
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num.length ==0) return result;
        Arrays.sort(num);
        if (target < num[0]) return result;

        List<Integer> path = new ArrayList<Integer>();
        dfs(num, target, 0, 0, path, result);
        return result;
    }

    private void dfs(int[] num, int target, int start, int sum, 
        List<Integer> path, List<List<Integer>> result)
    {
        if (sum == target) {
            result.add(new ArrayList<Integer>(path));
            return;
        }

        for (int i = start; i < num.length; i++) {
            if (i != start && num[i] == num[i - 1])
                continue;
            // sorted, no need to try more
            if (sum + num[i] > target)
                break;
            path.add(num[i]);
            // Error: should be i + 1, since each one only could be used once
            dfs(num, target, i + 1, sum + num[i], path, result);
            path.remove(path.size() - 1);
        }
    }
}