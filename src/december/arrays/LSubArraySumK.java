package december.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Given an array nums of size n and an integer k,
 * find the length of the longest sub-array that sums to k. If no such sub-array exists, return 0.
 * Input:
 * nums = [10, 5, 2, 7, 1, 9], k = 15
 * Output:
 * 4
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
public class LSubArraySumK {
    public static void main(String[] args) {
        HashMap<Integer, int[]> inputs = new HashMap<>();
        inputs.put(15, new int[]{10, 5, 2, 7, 1, 9});
        //inputs.put(6, new int[]{-3, 2, 1});
        inputs.put(1, new int[]{-1, 1, 1});
        inputs.put(5, new int[]{2, 3, 5});
        inputs.put(6, new int[]{3, 1, 2, 4});

        for (Map.Entry<Integer, int[]> entry : inputs.entrySet()) {
            int maxSubArray = findLSubArraySumK(entry.getValue(), entry.getKey());
            System.out.println("LSubArraySumK==>" + Arrays.toString(entry.getValue()) + "---" + maxSubArray);
        }
    }

    private static int findLSubArraySumK(int[] nums, int k) {
        int maxLen = 0;
        int i = 0;
        int currSum = nums[i];
        for (int j = i + 1; j < nums.length; ) {
            if (currSum >= k) {
                if (currSum == k) {
                    maxLen = Math.max(maxLen, j - i);
                }
                currSum = currSum - nums[i];
                i++;
            } else {
                currSum += nums[j];
                j++;
            }
        }
        if (currSum == k) return nums.length;
        return maxLen;
    }
}
