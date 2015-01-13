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
Complexity: O(n) + O(n)
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
            
        Iterator<Interval> it = intervals.iterator();
        while (it.hasNext()) {
            Interval cur = it.next();
            if (newInterval.start > cur.end)
                result.add(cur);
            else if (newInterval.end < cur.start) {
                result.add(newInterval);
                newInterval = cur;
            } else {
                newInterval.start = Math.min(newInterval.start, cur.start);
                newInterval.end = Math.max(newInterval.end, cur.end);
            }
        }
        
        result.add(newInterval);
        return result;
    }
}


// Improve 1: O(n) + O(1) in place
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals == null || intervals.size() == 0) {
            intervals.add(newInterval);
            return intervals;
        }

        if (newInterval == null)
            return intervals;
            
        ListIterator<Interval> it = intervals.listIterator();
        while (it.hasNext()) {
            Interval cur = it.next();
            if (newInterval.start > cur.end)
                continue;
            else if (newInterval.end < cur.start) {
                // Notice 1
                it.previous();
                it.add(newInterval);
                return intervals;
            } else {
                newInterval.start = Math.min(newInterval.start, cur.start);
                newInterval.end = Math.max(newInterval.end, cur.end);
                // Notice 2
                it.remove();
            }
        }
        
        it.add(newInterval);
        return intervals;
    }
}


// Improve 2: O(logn) + O(1) with binary search
// Since original intervals are non-overlapping
// But input list supposed to be array list with random access
public class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals == null || intervals.size() == 0) {
            intervals.add(newInterval);
            return intervals;
        }

        if (newInterval == null)
            return intervals;

        int startPos = searchPos(intervals, newInterval.start);
        int endPos = searchPos(intervals, newInterval.end);
        int newStart = 0, newEnd = 0;

        if (startPos >= 0 && intervals.get(startPos).end >= newInterval.start)
            newStart = intervals.get(startPos).start;
        else {
            startPos++;
            newStart = newInterval.start;
        }

        if (endPos >= 0)
            newEnd = Math.max(newInterval.end, intervals.get(endPos).end);
        else
            newEnd = newInterval.end;
        
        for (int i = startPos; i <= endPos; i++)
            intervals.remove(startPos);
        intervals.add(startPos, new Interval(newStart, newEnd));
        return intervals;
    }

    // return lower index if target doesn't exist
    // will return -1 if it is the first element
    private int searchPos(List<Interval> intervals, int target) {
        int start = 0, end = intervals.size() - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (intervals.get(mid).start == target)
                    return mid;
            else if (intervals.get(mid) < target)
                start = mid + 1;
            else
                end = mid - 1;
        }

        return end;
    }
}