package december.arrays;

import java.util.Arrays;

/**
 * There’s an array ‘A’ of size ‘N’ with an equal number of positive and negative elements. Without altering the relative order of positive and negative elements, you must return an array of alternately positive and negative values.
 * <p>
 * Examples
 * Example 1:
 * Input:
 * arr[] = {1,2,-4,-5}, N = 4
 * Output:
 * 1 -4 2 -5
 * Explanation:
 * Positive elements = 1,2
 * Negative elements = -4,-5
 * To maintain relative ordering, 1 must occur before 2, and -4 must occur before -5.
 * Example 2:
 * Input:
 * arr[] = {1,2,-3,-1,-2,-3}, N = 6
 * Output:
 * 1 -3 2 -1 3 -2
 * Explanation:
 * Positive elements = 1,2,3
 * Negative elements = -3,-1,-2
 * To maintain relative ordering, 1 must occur before 2, and 2 must occur before 3.
 * Also, -3 should come before -1, and -1 should come before -2.
 */
public class RearrangeArrayElementsBySign {

    public static void main(String[] args) {
        //int[] a = {1, 2, -4, -5};
        int[] a = {1, 2, 3, -1, -2, -3};
        int[] res = rearrangeArrayElementsBySign(a);
        System.out.println(Arrays.toString(res));
    }

    private static int[] rearrangeArrayElementsBySign(int[] a) {
        int posIndex = 0;
        int negIndex = 1;
        int[] temp = Arrays.copyOf(a, a.length);

        for (int x : a) {
            if (x < 0) {
                temp[negIndex] = x;
                negIndex = negIndex + 2;
            }
            if (x > 0) {
                temp[posIndex] = x;
                posIndex = posIndex + 2;
            }
        }
        return temp;
    }
}
