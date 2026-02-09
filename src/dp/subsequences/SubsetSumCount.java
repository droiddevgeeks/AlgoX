package dp.subsequences;

import java.util.Arrays;

/**
 * Target Sum (DP - 21) : THIS IS SAME AS SUBSET SUM COUNT
 * Problem Statement: We are given an array ‘ARR’ of size ‘N’ and a number ‘Target’.
 * Our task is to build an expression from the given array where we can place a ‘+’ or ‘-’ sign in front of an integer.
 * We want to place a sign in front of every integer of the array and get our required target.
 * We need to count the number of ways in which we can achieve our required target.
 * P - N = target
 * P + N = totalSum
 * P = (target + totalSum) / 2
 * <p>
 * Input : nums = [1,1,1,1,1], target = 3, Output : 5
 * -1 + 1 + 1 + 1 + 1 = 3 ,+1 - 1 + 1 + 1 + 1 = 3 , +1 + 1 - 1 + 1 + 1 = 3 , +1 + 1 + 1 - 1 + 1 = 3, +1 + 1 + 1 + 1 - 1 = 3
 */
public class SubsetSumCount {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 1, 1};
        int target = 3;
        int totalSum = 0;
        for (int i : nums) totalSum += i;

        int finalTarget = target + totalSum;
        int subsetSumCount = findSubsetSumCountRec(nums, nums.length - 1, finalTarget / 2);
        System.out.println("SubsetSumCount---->" + subsetSumCount);

        int[][] memo = new int[nums.length][finalTarget + 1];
        for (int[] temp : memo) {
            Arrays.fill(temp, -1);
        }
        subsetSumCount = findSubsetSumCountMemo(nums, memo, nums.length - 1, finalTarget / 2);
        System.out.println("SubsetSumCount Memo---->" + subsetSumCount);

        subsetSumCount = findSubsetSumCountTabular(nums, finalTarget / 2);
        System.out.println("SubsetSumCount Tabular---->" + subsetSumCount);
    }

    private static int findSubsetSumCountRec(int[] nums, int index, int target) {
        if (target == 0) return 1;
        if (index == 0) return (target == nums[index]) ? 1 : 0;

        int notPick = findSubsetSumCountRec(nums, index - 1, target);
        int pick = 0;
        if (nums[index] <= target)
            pick = findSubsetSumCountRec(nums, index - 1, target - nums[index]);

        return pick + notPick;
    }

    private static int findSubsetSumCountMemo(int[] nums, int[][] memo, int index, int target) {
        if (target == 0) return 1;
        if (index == 0) return (target == nums[index]) ? 1 : 0;

        if (memo[index][target] != -1) return memo[index][target];
        int notPick = findSubsetSumCountRec(nums, index - 1, target);
        int pick = 0;
        if (nums[index] <= target)
            pick = findSubsetSumCountRec(nums, index - 1, target - nums[index]);

        memo[index][target] = pick + notPick;
        return memo[index][target];
    }

    private static int findSubsetSumCountTabular(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n][target + 1];
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1; //  // Base case: if (target == 0) return 1;
        }
        // Base case: index = 0
        if (nums[0] <= target) {
            dp[0][nums[0]] = 1;
        }

        // Fill the table
        for (int i = 1; i < n; i++) {
            for (int t = 0; t <= target; t++) {
                int notPick = dp[i - 1][t];
                int pick = 0;
                if (nums[i] <= t) {
                    pick = dp[i - 1][t - nums[i]];
                }
                dp[i][t] = pick + notPick;
            }
        }
        return dp[n - 1][target];
    }
}
