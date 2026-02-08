package dp.subsequences;

import java.util.Arrays;

/**
 * Problem Description: Given an array of n integers,
 * partition the array into two subsets such that the absolute difference between their sums is minimized.
 * Input: nums = [1, 2, 3, 4]
 * Output: 0
 * Explanation: Two subsets can be [1,4] and [2,3].
 * Input: nums = [8, 6, 5]
 * Output: 3
 * Explanation: Two subsets can be [8] and [6, 5].
 */
public class SubsetsWithMinAbsoluteSum {
    public static void main(String[] args) {
        //int[] nums = new int[]{1, 2, 3, 4};
        int[] nums = new int[]{8, 6, 5};

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int closestDiff = findSubsetsWithMinAbsoluteSumRec(nums, 0, 0, sum);
        System.out.println("SubsetsWithMinAbsoluteSum rec--->" + closestDiff);

        int[][] memo = new int[nums.length][sum + 1];
        for (int[] temp : memo) {
            Arrays.fill(temp, -1);
        }
        closestDiff = findSubsetsWithMinAbsoluteSumMemo(nums, memo, 0, 0, sum);
        System.out.println("SubsetsWithMinAbsoluteSum Memo--->" + closestDiff);

        closestDiff = findSubsetsWithMinAbsoluteSumTabular(nums, sum);
        System.out.println("SubsetsWithMinAbsoluteSum Tabular--->" + closestDiff);
    }

    private static int findSubsetsWithMinAbsoluteSumRec(int[] nums, int index, int currSum, int totalSum) {
        if (index == nums.length) {
            return Math.abs((totalSum - currSum) - currSum);
        }

        int pick = findSubsetsWithMinAbsoluteSumRec(nums, index + 1, currSum + nums[index], totalSum);
        int notPick = findSubsetsWithMinAbsoluteSumRec(nums, index + 1, currSum, totalSum);

        return Math.min(pick, notPick);
    }

    private static int findSubsetsWithMinAbsoluteSumMemo(int[] nums, int[][] memo, int index, int currSum, int totalSum) {
        if (index == nums.length) {
            return Math.abs((totalSum - currSum) - currSum);
        }

        if (memo[index][currSum] != -1) return memo[index][currSum];

        int pick = findSubsetsWithMinAbsoluteSumRec(nums, index + 1, currSum + nums[index], totalSum);
        int notPick = findSubsetsWithMinAbsoluteSumRec(nums, index + 1, currSum, totalSum);

        memo[index][currSum] = Math.min(pick, notPick);
        return memo[index][currSum];
    }

    private static int findSubsetsWithMinAbsoluteSumTabular(int[] nums, int sum) {
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];

        for (int i = 0; i <= nums.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= sum; j++) {
                boolean notPick = dp[i - 1][j];
                boolean pick = false;
                if (j >= nums[i - 1]) {
                    pick = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
                dp[i][j] = pick || notPick;
            }
        }
        int minDiff = Integer.MAX_VALUE;
        for (int j = 0; j <= sum / 2; j++) {
            if (dp[nums.length][j]) {
                minDiff = Math.min(minDiff, Math.abs(sum - 2 * j));
            }
        }
        return minDiff;
    }
}
