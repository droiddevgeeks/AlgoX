package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * There’s an array ‘A’ of size ‘N’ with an equal number of positive and negative elements. Without altering the relative order of positive and negative elements, you must return an array of alternately positive and negative values.
 * <p>
 * Note: Start the array with positive elements.
 * <p>
 * Examples:
 * <p>
 * Example 1:
 * <p>
 * Input:
 * arr[] = {1,2,-4,-5}, N = 4
 * Output:
 * 1 -4 2 -5
 * <p>
 * Explanation:
 * <p>
 * Positive elements = 1,2
 * Negative elements = -4,-5
 * To maintain relative ordering, 1 must occur before 2, and -4 must occur before -5.
 * <p>
 * Example 2:
 * Input:
 * arr[] = {1,2,-3,-1,-2,-3}, N = 6
 * Output:
 * 1 -3 2 -1 3 -2
 * Explanation:
 * <p>
 * Positive elements = 1,2,3
 * Negative elements = -3,-1,-2
 * To maintain relative ordering, 1 must occur before 2, and 2 must occur before 3.
 * Also, -3 should come before -1, and -1 should come before -2.
 */
public class RearrangeArrayElementsBySign {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, -4, -5};
        alternatePositiveNegativeBruteForce(nums);
        System.out.println("alternatePositiveNegativeBruteForce ==>" + Arrays.toString(nums));

        int[] num = new int[]{1, 2, -3, -1, -2, 3};
        int[] res = alternatePositiveNegativeOptimal(num);
        System.out.println("alternatePositiveNegativeOptimal==>" + Arrays.toString(res));
    }

    private static void alternatePositiveNegativeBruteForce(int[] a) {
        List<Integer> pos = new ArrayList<>();
        List<Integer> neg = new ArrayList<>();

        for (int j : a) {
            if (j < 0) neg.add(j);
            else pos.add(j);
        }
        for (int i = 0; i < a.length; i++) {
            if (i % 2 == 0) a[i] = pos.removeFirst();
            if (i % 2 != 0) a[i] = neg.removeFirst();
        }
    }

    /**
     * It will use extra space of O(n)
     *
     * @param a
     */
    private static int[] alternatePositiveNegativeOptimal(int[] a) {
        int[] res = Arrays.copyOf(a, a.length);

        int posIndex = 0;
        int negIndex = 1;
        for (int j : a) {
            if (j < 0) {
                res[negIndex] = j;
                negIndex = negIndex + 2;
            }
            if (j > 0) {
                res[posIndex] = j;
                posIndex = posIndex + 2;
            }
        }
        return res;
    }
}
