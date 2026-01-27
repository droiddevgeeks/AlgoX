package arrays;

/**
 * Example 1:
 * <p>
 * Input: prices = {1, 1, 0, 1, 1, 1}
 * <p>
 * Output: 3
 * <p>
 * Explanation: There are two consecutive 1’s and three consecutive 1’s in the array out of which maximum is 3.
 * <p>
 * Input: prices = {1, 0, 1, 1, 0, 1}
 * <p>
 * Output: 2
 */
public class MaximumConsecutiveOne {

    public static void main(String[] args) {
        //int[] nums = new int[]{1, 1, 0, 1, 1, 1};
        int[] nums = new int[]{1, 0, 1, 1, 0, 1};

        int count = countMaximumConsecutiveOne(nums);
        System.out.println("MaximumConsecutiveOne ==>" + count);
    }

    private static int countMaximumConsecutiveOne(int[] a) {
        int maxOne = 0;
        int result = Integer.MIN_VALUE;
        for (int j : a) {
            if (j == 1) {
                maxOne++;
            } else {
                result = Math.max(maxOne, result);
                maxOne = 0;
            }
        }
        result = Math.max(maxOne , result);
        return result;
    }
}
