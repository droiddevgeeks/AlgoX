package december.arrays;

/**
 * Given an array that contains only 1 and 0 return the count of maximum consecutive ones in the array..
 * Example 1:
 * Input: prices = {1, 1, 0, 1, 1, 1}
 * Output: 3
 * Explanation: There are two consecutive 1’s and three consecutive 1’s in the array out of which maximum is 3.
 * <p>
 * Example 2:
 * Input: prices = {1, 0, 1, 1, 0, 1}
 * Output: 2
 * Explanation: There are two consecutive 1's in the array.
 */
public class MaximumConsecutiveOne {

    public static void main(String[] args) {
        // int[] a = {1, 1, 0, 1, 1, 1};
        int[] a = {1, 0, 1, 1, 0, 1};
        int res = findMaximumConsecutiveOne(a);
        System.out.println("MaximumConsecutiveOne==>" + res);
    }

    private static int findMaximumConsecutiveOne(int[] a) {
        int count = 0;
        if (a.length == 0) return count;
        int res = Integer.MIN_VALUE;
        for (int input : a) {
            if (input == 1) {
                count++;
            } else {
                res = Math.max(res, count);
                count = 0;
            }
        }
        res = Math.max(res, count);
        return res;
    }
}
