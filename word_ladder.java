/*
Given two words (start and end), and a dictionary, find the length of shortest 
transformation sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
For example,

Given:
start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.

Solution: BFS, for each word on path, change all character to try fall into dict

To record level length, two methods:
1) enqueue current level no. when new element in this level be enqueued,
   to make node and its level sync
2) process a whole level each time: move all nodes in certain level out of queue
   move them into a current list, process nodes in list enequeue next level node
   This makes loop counts and level no. sync

Complexity: O(n) + O(n) ?

Corner case: a, c {a, b, c}
             a, a 
*/
public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
        if (dict.size() == 0) return 0;

        int ladder_len = 1;
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);

        List<String> cur_level = new ArrayList<String>();
        while (!queue.isEmpty()) {
            // move all node in current level from queue to level list
            int level_width = queue.size();
            cur_level.clear();
            for (int i = 0; i < level_width; i++) {
                String str = queue.poll();
                if (dict.contains(str))
                    dict.remove(str);
                cur_level.add(str);
            }

            for (String str : cur_level) {
                for (int i = 0; i < str.length(); i++) {
                    char[] origin = str.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c != origin[i]) {
                            origin[i] = c;
                            String new_str = new String(origin);
                            if (new_str.equals(end)) {
                                queue.offer(end);
                            } else if (dict.contains(new_str)) {
                                queue.offer(new_str);
                                // Error: still need here
                                // same new str may generated in same level
                                dict.remove(new_str);
                            }
                        }
                    }
                }
            }

            ladder_len++;
            if (queue.contains(end))
                return ladder_len;
        }

        return 0;
    }
}