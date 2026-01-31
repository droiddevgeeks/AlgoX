package dp;

import java.util.Arrays;

/**
 * Problem Statement: Given an array of N positive integers, we need to return the maximum sum of the subsequence
 * such that no two elements of the subsequence are adjacent elements in the array.
 * Note: A subsequence of an array is a list with elements of the array where some elements are deleted (or not deleted at all)
 * and the elements should be in the same order in the subsequence as in the array.
 * Input: nums = [1, 2, 4]
 * Output: 5 Explanation: Subsequence {1,4} gives maximum sum.
 * Input:  [2, 1, 4, 9]
 * Output: 11 Explanation:  Subsequence {2,9} gives maximum sum
 */
public class MaxSumNonAdjacentElementsDP5 {
    public static void main(String[] args) {
        //int[] nums = new int[]{1, 2, 4};
        int[] nums = new int[]{2, 1, 4, 9};
        int maxSum = findMaxSumNonAdjacentElementsRecursive(nums, nums.length-1);
        System.out.println("MaxSumNonAdjacentElementsDP5===>" + maxSum);

        maxSum = findMaxSumNonAdjacentElementsTabular(nums, nums.length);
        System.out.println("MaxSumNonAdjacentElementsDP5 Tabular===>" + maxSum);

        maxSum = findMaxSumNonAdjacentElementsMemo(nums, nums.length);
        System.out.println("MaxSumNonAdjacentElementsDP5 Memo===>" + maxSum);
    }

    private static int findMaxSumNonAdjacentElementsRecursive(int[] nums, int n) {
        if (n == 0) return nums[0];
        if (n < 0) return 0;

        int pickCurrElement = nums[n] + findMaxSumNonAdjacentElementsRecursive(nums, n - 2);
        int notPickCurrElement = findMaxSumNonAdjacentElementsRecursive(nums, n - 1);
        return Math.max(pickCurrElement, notPickCurrElement);
    }

    private static int findMaxSumNonAdjacentElementsTabular(int[] nums, int n) {
        if (n == 1) return nums[0];
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], nums[0]);

        for (int i = 2; i < n; i++) {
            int pick = nums[i] + dp[i - 2];
            int notPick = dp[i - 1];
            dp[i] = Math.max(pick, notPick);
        }
        return dp[n - 1];
    }

    private static int findMaxSumNonAdjacentElementsMemo(int[] nums, int n) {
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return helperMemo(nums, memo, n-1);
    }

    private static int helperMemo(int[] nums, int[] memo, int n) {
        if (n == 0) return nums[0];
        if (n < 0) return n;

        if (memo[n] != -1) return memo[n];
        int pick = nums[n] + helperMemo(nums, memo, n-2);
        int notPick = helperMemo(nums, memo, n-1);
        memo[n] = Math.max(pick, notPick);
        return memo[n];
    }

}
