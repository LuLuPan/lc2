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


Corener Cases: /xxx/yyy
*/
public class Solution {
    public String simplifyPath(String path) {
        if (path.length() == 0) return "";
        StringBuilder result = new StringBuilder();
        Stack<String> stack = new Stack<String>();

        for (int i = 0; i < path.length();) {
        	int index = i;
        	StringBuilder sb = new StringBuilder();
        	while (i < path.length() && path.charAt(i) != '/') {
        		sb.append(path.charAt(i));
        		i++;
        	}
        	// find meaningful string
        	if (index != i) {
        		String folder = sb.toString();
        		if (folder.equals("..")) {
        			if (!stack.isEmpty())
        				stack.pop();
        		} else if (!folder.equals("."))
                    stack.push(folder);
        	}
        	i++;
        }

        if (!stack.isEmpty()) {
        	// stack bottom become array head?
        	String[] strs = stack.toArray(new String[stack.size()]);
        	for (int i = 0; i < strs.length; i++)
        		result.append("/" + strs[i]);
        }

        if (result.length() == 0)
        	result.append("/");

        return result.toString();
    }
}