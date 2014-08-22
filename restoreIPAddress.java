/*
Given a string containing only digits, restore it by returning all 
possible valid IP address combinations.

For example:
Given "25525511135",

return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)

Solution: DFS
Complexity:
Corner case: for 0, could be used as single number or postfix, but cannot show
             up as prefix.
*/
public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        if (s.length() > 12 || s.length() == 0) return result;
        dfs(s, 0, 0, "", result);
        return result;
    }
    private void dfs(String s, int start, int step, String path, List<String> result) {
        if (start == s.length() && step == 4) {
            String tmp = new String(path.substring(0, path.length() - 1));
            result.add(tmp);
            return;
        }
        // invalid condition
        if ((s.length() - start) > (4 - step) * 3) return;
        if ((s.length() - start) < (4 - step)) return;

        for (int i = start; i < Math.min(s.length(), start + 3); i++) {
            int num = Integer.parseInt(s.substring(start, i + 1));
            if (num <= 255) {
                // if use path += num, will recover path later
                dfs(s, i + 1, step + 1, path + num + '.', result);
            }
            // at this point, 0 will be used as prefix
            if (num == 0) break;
        }
    }
}