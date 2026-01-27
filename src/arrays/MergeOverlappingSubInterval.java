package arrays;

import java.util.Arrays;
import java.util.Stack;

/**
 * Example 1:
 * Example 1:
 * <p>
 * Input: intervals=[[1,3],[2,6],[8,10],[15,18]]
 * <p>
 * Output: [[1,6],[8,10],[15,18]]
 * <p>
 * Explanation: Since intervals [1,3] and [2,6] are overlapping we can merge them to form [1,6]
 * intervals.
 * <p>
 * Example 2:
 * <p>
 * Input: [[1,4],[4,5]]
 * <p>
 * Output: [[1,5]]
 * <p>
 * Explanation: Since intervals [1,4] and [4,5] are overlapping we can merge them to form [1,5].
 */
public class MergeOverlappingSubInterval {

    public static void main(String[] args) {
        int[][] nums = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        //int[][] nums = {{1, 4}, {4, 5}};
        Stack<int[]> merged = mergeIntervals(nums);

        for (int[] num : merged) {
            System.out.println("Interval" + Arrays.toString(num));
        }
    }


    private static Stack<int[]> mergeIntervals(int[][] a) {
        Stack<int[]> st = new Stack<>();
        st.push(a[0]);

        for (int i = 1; i < a.length; i++) {
            int[] curr = a[i];
            int[] top = st.peek();

            if (curr[0] > top[1]) st.add(curr);
            else {
                int[] temp = st.pop();
                temp[1] = Math.max(temp[1], curr[1]);
                st.push(temp);
            }
        }
        return st;
    }
}
