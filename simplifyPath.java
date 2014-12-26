/*
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
click to show corner cases.

Corner Cases:
Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, 
such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".


Corener Cases: /xxx/yyy, /xxx/..
               /..
*/
public class Solution {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) return "";
        int n = path.length();
        Stack<String> stack = new Stack<String>();
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (path.charAt(i) == '/')
                j = i;
            else if (i == n - 1 || path.charAt(i + 1) == '/') {
                String sub = path.substring(j + 1, i + 1);
                if (sub.equals("..")) {
                    if (!stack.isEmpty())
                        stack.pop();
                } else if (!sub.equals("."))
                    stack.push(sub);
            }
        }
        
        StringBuilder result = new StringBuilder();
        String[] strs = stack.toArray(new String[stack.size()]);
        for (String str : strs)
            result.append("/" + str);
        if (result.length() == 0)
            result.append("/");
        
        return result.toString();
    }
}