package december.arrays;

import java.util.*;

public class MergeOverlappingSubIntervals {
    public static void main(String[] args) {
        //int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] intervals = {{1,4},{4,5}};
        List<List<Integer>> res = mergeBruteForce(intervals);
        System.out.println("Using Brute Force");
        for (List<Integer> temp : res) {
            System.out.println(Arrays.toString(temp.toArray()));
        }
        System.out.println("\n");

        Stack<int[]> mergedList = mergeUsingStack(intervals);
        System.out.println("Using Stack");
        for (int[] temp: mergedList) {
            System.out.println(Arrays.toString(temp));
        }
    }

    private static Stack<int[]> mergeUsingStack(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        Stack<int[]> st = new Stack<>();
        st.push(intervals[0]);
        int i = 1;
        while (i < intervals.length) {
            int[] top = st.peek();
            int[] curr = intervals[i];
            if (top[1] < curr[0]) {
                st.push(curr);
            } else {
                int[] temp = st.pop();
                temp[1] = Math.max(temp[1], curr[1]);
                st.push(temp);
            }
            i++;
        }
        return st;
    }

    private static List<List<Integer>> mergeBruteForce(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int row = intervals.length;
        List<List<Integer>> result = new ArrayList<>();
        int i = 0;
        while (i < row) {
            int s = intervals[i][0];
            int e = intervals[i][1];
            int j = i + 1;
            while (j < row && intervals[j][0] <= e) {
                e = Math.max(e, intervals[j][1]);
                j++;
            }
            result.add(Arrays.asList(s, e));
            i = j;
        }
        return result;
    }
}
