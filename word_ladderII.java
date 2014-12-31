/*
Given two words (start and end), and a dictionary, find all shortest 
transformation sequence(s) from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
Return
  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
Note:
All words have the same length.
All words contain only lowercase alphabetic characters.

Solution: Use HashMap to record all word and its predescders during transform

*/
public class Solution {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> result = new  ArrayList<List<String>>();
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        map.put(start, new ArrayList<String>());
        map.put(end, new ArrayList<String>());
        
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<String> level = new ArrayList<String>();
            for (int i = 0; i < size; i++) {
                String str = queue.poll();
                if (dict.contains(str))
                    dict.remove(str);
                level.add(str);
            }
            
            for (String str : level) {
                for (int i = 0; i < str.length(); i++) {
                    char[] strArr = str.toCharArray();
                    char org = strArr[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (org != c) {
                            strArr[i] = c;
                            String newStr = new String(strArr);
                            if (newStr.equals(end)) {
                                map.get(end).add(str);
                                queue.offer(end);
                            } else if (dict.contains(newStr)) {
                                // Error: if remove new str from dict
                                // it will exclude other valid transform
                                // relationship since several path may share
                                // one intermediate words
                                //dict.remove(new_str);
                                if (!map.containsKey(newStr))
                                    map.put(newStr, new ArrayList<String>());
                                map.get(newStr).add(str);
                                // Note: Need to check new string already within
                                // queue or not, otherwise will bring redandunancy
                                // in next level
                                if (!queue.contains(newStr))
                                    queue.offer(newStr);
                            }
                        }
                    }
                }
            }
            
            if (queue.contains(end))
                break;
        }
        
        List<String> path = new ArrayList<String>();
        path.add(end);
        helper(start, end, map, path, result);
        return result;
    }
    
    private void helper(String start, String end, HashMap<String, List<String>> map, 
                        List<String> path, List<List<String>> result) {
        if (start.equals(end)) {
            List<String> tmp = new ArrayList<String>(path);
            Collections.reverse(tmp);
            result.add(tmp);
            return;
        }
        
        for (String str : map.get(end)) {
            path.add(str);
            helper(start, str, map, path, result);
            path.remove(path.size() - 1);
        }
    }
}                                   