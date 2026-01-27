package december.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array nums of size n and an integer k,
 * find the length of the longest sub-array that sums to k. If no such sub-array exists, return 0.
 * Input:
 * nums = [-1, 1, 1], k = 1
 * Output:
 * 3
 * Explanation:
 * The longest sub-array with a sum equal to 15 is [5, 2, 7, 1], which has a length of 4. This sub-array starts at index 1 and ends at index 4, and the sum of its elements (5 + 2 + 7 + 1) equals 15. Therefore, the length of this sub-array is 4.
 * <p>
 * Example 2:
 * Input:
 * nums = [-3, 2, 1], k = 6
 * Output:
 * 0
 * Explanation:
 * There is no sub-array in the array that sums to 6. Therefore, the output is 0.
 */
public class LSubArraySumKNegativeValues {
    public static void main(String[] args) {
        HashMap<Integer, int[]> inputs = new HashMap<>();
        inputs.put(1, new int[]{-1, 1, 1});
        inputs.put(6, new int[]{-3, 2, 1});
        inputs.put(3, new int[]{2, -1, 2, 1});

        for (Map.Entry<Integer, int[]> entry : inputs.entrySet()) {
            System.out.print("Array is ==>" + Arrays.toString(entry.getValue()));
            int maxSubArray = findLSubArraySumK(entry.getValue(), entry.getKey());
            System.out.println("and LSubArraySumK==>" + maxSubArray);
        }
    }

    private static int findLSubArraySumK(int[] nums, int k) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        int sum = 0;
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (sum == k) {
                maxLen = i + 1;
            }

            if (hm.containsKey(sum - k)) {
                maxLen = Math.max(maxLen, i - hm.get(sum - k));
            }

            hm.putIfAbsent(sum, i);
        }
        return maxLen;
    }
}
