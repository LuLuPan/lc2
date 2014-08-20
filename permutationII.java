/*
Given a collection of numbers that might contain duplicates, 
return all possible unique permutations.

For example,
[1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].

Solution: 1. HashSet to deduplicate when enumating, will time out
          2. Sort at first, for duplicated element, element before it should 
             already be used, then it can be visited
Complexity: O(n!) + O(n)
Corner case:
*/
public class Solution {
    public List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int n = num.length;
        if (n == 0) return result;

        Arrays.sort(num);
        boolean[] visited = new boolean[n];
        List<Integer> permute = new ArrayList<Integer>();

        dfs(num, permute, visited, result);
        return result;
    }

    private void dfs(int[] num, List<Integer> permute, boolean[] visited,
        List<List<Integer>> result) {
        if (permute.size() == num.length) {
            result.add(new ArrayList<Integer>(permute));
            return;
        }

        for (int i = 0; i < num.length; i++) {
            if (visited[i] == false) {
                if (i > 0 && num[i - 1] == num[i] && visited[i - 1] == false)
                    continue;
                visited[i] = true;
                permute.add(num[i]);
                dfs(num, permute, visited, result);
                permute.remove(permute.size() - 1);
                visited[i] = false;
            }
        }
    }
}