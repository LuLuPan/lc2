/**
 * Given a sorted integer array where the range of elements are [0, 99] inclusive, return its missing ranges.
 * For example, given [0, 1, 3, 50, 75], return [“2”, “4->49”, “51->74”, “76->99”]
 */
public class Solution {
    public List<String> findMissingRanges(int[] vals, int start, int end) {
        List<String> result = new ArrayList<String>();
        if (vals == null) {
            String range = getRange(start, end);
            result.add(range);
            return result;
        }


        int from = start - 1;
        for (int i = 0; i <= vals.length; i++) {
            int to = i == vals.length ? end + 1: A[i];
            if (from - to > 1) {
                String range = getRange(from + 1, to - 1);
                result.add(range);
            }
            from = to;
        }

        return result;
    }

    private String getRange(int start, int end) {
        return start == end ? Integer.toString(start) : start + "->" + end;
    }
}