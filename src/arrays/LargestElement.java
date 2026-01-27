package arrays;

/**
 * nput: nums = [3, 3, 6, 1]
 * <p>
 * Output: 6
 * <p>
 * Explanation: The largest element in array is 6
 * <p>
 * Input: nums = [3, 3, 0, 99, -40]
 * <p>
 * Output: 99
 * <p>
 * Explanation: The largest element in array is 99
 */
public class LargestElement {
    public static void main(String[] args) {
        //int[] nums = new int[]{3, 3, 0, 99, -40};
        int[] nums =  new int[]{3, 3, 6, 1};

        int max = largestElement(nums);
        System.out.println("Largest Element==>" + max);

    }


    private static int largestElement(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(num, max);
        }
        return max;
    }
}
