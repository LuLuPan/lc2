// no duplications
public class Solution {
    public int findMin(int[] num) {
        if (num == null || num.length == 0) return -1;
        int start = 0;
        int end = num.length - 1;
        while (start < end) {
            if (num[start] <= num[end]) break;
            int mid = start + (end - start) / 2;
            if (num[mid] < num[end])
                end = mid;
            else
                start = mid + 1;
        }
        
        return num[start];
    }
}

// duplications
public class Solution {
    public int findMin(int[] num) {
        if (num == null || num.length == 0) return -1;
        int start = 0;
        int end = num.length - 1;
        while (start < end) {
            // note1: cannot be <=, since duplications could be at start and end
            if (num[start] < num[end]) break;
            int mid = start + (end - start) / 2;
            if (num[mid] < num[end])
                end = mid;
            else if ((num[mid] > num[start]))
                start = mid + 1;
            // note 2: duplication
            else start++;
        }
        
        return num[start];
    }
}