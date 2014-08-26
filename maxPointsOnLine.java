/*
Given n points on a 2D plane, find the maximum number of points that 
lie on the same straight line.

Solution: use HashMap to store slope and points pair,
points on the same slope are one the same line
*/

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    public int maxPoints(Point[] points) {
        int n = points.length;
        if (n < 3) return n;
        int result = 1;

        HashMap<Double, Integer> slopeMap = new HashMap<Double, Integer>();

        for (int i = 0; i < points.length - 1; i++) {
            Double slope = 0.0;
            int maxNum = 1;
            slopeMap.clear();
            int samePts = 0;
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].x == points[j].x) {
                    slope = Double.POSITIVE_INFINITY;
                    if (points[i].y == points[j].y) {
                        samePts++;
                        continue;
                    }
                } else {
                    slope = 1.0 * (points[i].y - points[j].y) / (points[i].x - points[j].x);
                    if (points[i].y == points[j].y)
                        slope = 0.0;

                }
                    
                if (slopeMap.containsKey(slope)) {
                    slopeMap.put(slope, slopeMap.get(slope) + 1);
                } else {
                    // should be 2
                    slopeMap.put(slope, 2);
                }

                // update max points
                maxNum = Math.max(maxNum, slopeMap.get(slope));
            }
            // update max points
            result = Math.max(maxNum + samePts, result);
        }

        return result;
    }
}