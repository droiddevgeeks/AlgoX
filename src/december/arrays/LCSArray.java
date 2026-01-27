package december.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array nums of n integers.
 * <p>
 * Return the length of the longest sequence of consecutive integers.
 * The integers in this sequence can appear in any order.
 */
public class LCSArray {
    public static void main(String[] args) {

        int[] a = {100, 4, 200, 1, 3, 2};
        int lcs = findLCSWithSort(a);
        System.out.println("LCS with Sort===" + lcs);

        int[] b = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int lcs1 = findLCSWithSpace(b);
        System.out.println("LCS with Space===" + lcs1);
    }

    private static int findLCSWithSpace(int[] a) {
        int len = a.length;
        if (len == 0 || len == 1) return len;
        Set<Integer> st = new HashSet<>();
        for (Integer temp : a) {
            st.add(temp);
        }
        int count = 1;
        int max = Integer.MIN_VALUE;
        for (int temp : st) {
            if (st.contains(temp + 1)) {
                count++;
                max = Math.max(max, count);
            } else {
                count = 1;
            }
        }
        return max;
    }

    private static int findLCSWithSort(int[] a) {
        int len = a.length;
        if (len == 0 || len == 1) return len;
        Arrays.sort(a);
        System.out.println("Sorted" + Arrays.toString(a));

        int i = 0;
        int max = 1;
        int count = 1;
        while (i < len) {
            if (Arrays.binarySearch(a, a[i] + 1) > 0) {
                count++;
                max = Math.max(max, count);
            } else {
                count = 1;
            }
            i++;
        }
        return max;
    }
}
