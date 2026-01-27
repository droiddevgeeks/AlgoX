package arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Example 1:
 * <p>
 * Input: [100, 200, 1, 3, 2, 4]
 * <p>
 * Output: 4
 * <p>
 * Explanation: The longest consecutive subsequence is 1, 2, 3, and 4.
 * <p>
 * Input: [3, 8, 5, 7, 6]
 * <p>
 * Output: 4
 * <p>
 * Explanation: The longest consecutive subsequence is 5, 6, 7, and 8.
 */
public class LCSInArray {

    public static void main(String[] args) {
        int[] a = new int[]{100, 200, 1, 3, 2, 4};
        //int[] a = new int[]{3, 8, 5, 7, 6};

        int lcs = lcsSorting(a);
        System.out.println("LCS with sorting==>" + lcs);
        lcs = lcsOptimal(a);
        System.out.println("LCS with sorting===>" + lcs);
    }

    private static int lcsSorting(int[] a) {
        int lcs = 1;
        Arrays.sort(a);
        int c = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i - 1] + 1 == a[i]) {
                c++;
                lcs = Math.max(lcs, c);
            } else {
                c = 1;
            }
        }
        return lcs;
    }

    private static int lcsOptimal(int[] a) {
        Set<Integer> set = new HashSet<>();
        for (int i : a) {
            set.add(i);
        }
        int lcs = Integer.MIN_VALUE;
        for (int temp : set) {
            // this is first found
            if (!set.contains(temp - 1)) {
                int c = 1;
                int x = temp;
                while (set.contains(x+1)) {
                    x += 1;
                    c += 1;
                }
                if (lcs < c) {
                    lcs = c;
                }
            }
        }
        return lcs;
    }
}
