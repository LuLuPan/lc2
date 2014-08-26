/*
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].

Corner case:
Since unsorted,
[[2,3],[4,5],[6,7],[8,9],[1,10]]
=>
[[1, 10]]

Solution:
1. sort, compare and merge in one pass
2. Reuse insert intervals O(n1 + n2 + n3...)
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result = new ArrayList<Interval>();
        if (intervals.size() == 0)
            return result;

        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval a, Interval b) {
                return a.start > b.start ? 1 : (a.start == b.start ? 0 : -1);        
            }
        });

        Interval curItv = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start <= curItv.end) {
                // merge overlap
                curItv.start = Math.min(curItv.start, intervals.get(i).start);
                curItv.end = Math.max(curItv.end, intervals.get(i).end);
            } else {
                result.add(curItv);
                curItv = intervals.get(i);
            }
        }

        result.add(curItv);
        return result;
    }
}