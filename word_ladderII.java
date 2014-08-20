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
        List<List<String>> result = new ArrayList<List<String>>();
        List<String> path = new ArrayList<String>();
        if (start.equals(end)) {
            path.add(start);
            path.add(end);
            result.add(path);
            return result;
        }

        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        map.put(start, new ArrayList<String>());
        map.put(end, new ArrayList<String>());
        for (String str : dict) {
            if (!map.containsKey(str))
                map.put(str, new ArrayList<String>());
        }

        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        // list to store nodes in certain level
        List<String> cur_level = new ArrayList<String>();

        while (!queue.isEmpty()) {
            int level_width = queue.size();
            cur_level.clear();

            for (int i = 0; i < level_width; i++) {
                String str = queue.poll();c
                if (dict.contains(str))
                    dict.remove(str);
                cur_level.add(str);
            }

            for (String str : cur_level) {
                for (int i = 0; i < str.length(); i++) {
                    char[] origin = str.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (origin[i] != c) {
                            origin[i] = c;
                            String new_str = new String(origin);
                            if (new_str.equals(end)) {
                                queue.add(end);
                                map.get(end).add(str);
                            } else {
                                if (dict.contains(new_str)) {
                                    // Error: if remove new str from dict
                                    // it will exclude other valid transform
                                    // relationship
                                    //dict.remove(new_str);
                                    if (!queue.contains(new_str))
                                        queue.add(new_str);
                                    map.get(new_str).add(str);
                                }
                            }
                        }
                    }
                }
            }

            if (queue.contains(end))
                break;
        }

        path.add(end);
        form_path(start, end, map, path, result);
        return result;
    }

    // DFS to search path
    private void form_path(String start, String end, HashMap<String, List<String>> map, 
        List<String> path, List<List<String>> result)
    {
        if (start.equals(end)) {
            List<String> tmp = new ArrayList<String>(path);
            Collections.reverse(tmp);
            result.add(tmp);
            return;
        }

        for (String str : map.get(end)) {
            path.add(str);
            form_path(start, str, map, path, result);
            path.remove(path.size() - 1);
        }
    }
}