package dp.greedy;

import java.util.Arrays;

/**
 * Problem Description: Given an integer array nums, find the number of Longest Increasing Subsequences (LIS) in the array.
 * Input: nums = [1, 3, 5, 4, 7]
 * Output: 2
 * Input: nums = [2, 2, 2, 2, 2]
 * Output: 5
 */
public class CountLIS {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 4, 7};
        int lisCount = countLIS(nums);
        System.out.println("CountLIS===>" + lisCount);
    }

    private static int countLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] count = new int[n];

        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if ((dp[j] + 1) > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }

                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        int total = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLen) total += count[i];
        }
        return total;
    }

}
