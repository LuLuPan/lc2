/*
Given a collection of numbers, return all possible permutations.

For example,
[1,2,3] have the following permutations:
[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].

Solution: DFS search all permutations
*/

public class Solution {
    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int n = num.length;
        if (n == 0) return result;

        List<Integer> permute = new ArrayList<Integer>();
        dfs(num, permute, result);
        return result;
    }

    private void dfs(int[] num, List<Integer> permute,
        List<List<Integer>> result)
    {
        if (permute.size() == num.length) {
            result.add(new ArrayList<Integer>(permute));
            return;
        }

        for (int i = 0; i < num.length; i++) {
            if (!permute.contains(num[i])) {
                permute.add(num[i]);
                dfs(num, permute, result);
                permute.remove(permute.size() - 1);
            }
        }
    }
}