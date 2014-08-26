/*
Given a set of non-overlapping intervals, insert a new interval into the 
intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their 
start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge
 [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].

Solution: It is better to scan, compare and merge interval in one pass
Corner case: input interval and intervals could be empty
Complexity: O(n) + O(1)
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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<Interval>();
        if (intervals == null || intervals.size() == 0) {
            result.add(newInterval);
            return result;
        }
        if (newInterval == null)
            return intervals;

        ListIterator<Interval> li = intervals.listIterator();
        Interval start = null;
        Interval end = null;
        while (li.hasNext()) {
            Interval itv = li.next();
            if (itv.end < newInterval.start)
                result.add(itv);
            else if (itv.start > newInterval.end) {
                result.add(newInterval);
                newInterval = itv;
            } else {
                // begin overlaps
                newInterval.start = Math.min(newInterval.start, itv.start);
                newInterval.end = Math.max(newInterval.end, itv.end);
            }
        }

        result.add(newInterval);

        return result;
    }
}