package dp.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 * nums = [10, 9, 2, 5, 3, 7, 101, 18]    // 4 [2, 3, 7, 101]
 * [0, 1, 0, 3, 2, 3]   // 4 [0, 1, 2, 3],
 */
public class LIS {
    public static void main(String[] args) {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};

        int lis = findLISRec(nums, 0, -1);
        System.out.println("LIC  Rec ---" + lis);

        Integer[][] memo = new Integer[nums.length][nums.length + 1];
        lis = findLISMemo(nums, 0, -1, memo);
        System.out.println("LIC  Memo ---" + lis);

        lis = findLISTabular(nums);
        System.out.println("LIC  Tabular ---" + lis);

        lis = findLISTabularBasedOn1D(nums);
        System.out.println("LIC  Basedon 1D DP array ---" + lis);

        lis = findLISTabularGreedy(nums);
        System.out.println("LIC Greedy ---" + lis);

        List<Integer> lisList = printLISTabularBasedOn1D(nums);
        System.out.println("LIC Greedy ---" + lisList);
    }

    private static int findLISRec(int[] nums, int index, int prevIndex) {
        if (index == nums.length) return 0;
        int skip = findLISRec(nums, index + 1, prevIndex);
        int take = 0;
        if (prevIndex == -1 || nums[index] > nums[prevIndex])
            take = 1 + findLISRec(nums, index + 1, index);

        return Math.max(skip, take);

    }

    private static int findLISMemo(int[] nums, int index, int prevIndex, Integer[][] memo) {
        if (index == nums.length) return 0;
        if (memo[index][prevIndex + 1] != null) return memo[index][prevIndex + 1];
        int skip = findLISMemo(nums, index + 1, prevIndex, memo);
        int take = 0;
        if (prevIndex == -1 || nums[index] > nums[prevIndex])
            take = 1 + findLISMemo(nums, index + 1, index, memo);
        return memo[index][prevIndex + 1] = Math.max(skip, take);
    }

    // dp[index][prevIndex] = Best LIS we can build starting at index ,given last chosen element was prevIndex
    private static int findLISTabular(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 1][n + 1];


        for (int index = n - 1; index >= 0; index--) {
            for (int prevIndex = index - 1; prevIndex >= -1; prevIndex--) {
                int skip = dp[index + 1][prevIndex + 1]; //since prevIndex is -1 based
                int take = 0;
                if (prevIndex == -1 || nums[index] > nums[prevIndex]) take = 1 + dp[index + 1][index + 1];
                dp[index][prevIndex + 1] = Math.max(skip, take);
            }
        }
        return dp[0][0];
    }

    //dp[i] = LIS ending at index i
    private static int findLISTabularBasedOn1D(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);  //Because every element alone is a subsequence.
        int maxLen = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }

    //tails[i] = smallest possible tail of an increasing subsequence of length (i+1)
    private static int findLISTabularGreedy(int[] nums) {
        List<Integer> tails = new ArrayList<>();

        for (int n : nums) {
            int left = 0;
            int right = tails.size();

            while (left < right) {
                int mid = left + (right - left) / 2;

                if (tails.get(mid) < n) left = mid + 1;
                else right = mid;
            }

            if (left == tails.size()) tails.add(n);
            else tails.add(left, n);
        }
        return tails.size();
    }

    //dp[i] = LIS ending at index i
    private static List<Integer> printLISTabularBasedOn1D(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);  //Because every element alone is a subsequence.
        int[] parent = new int[n + 1];
        Arrays.fill(parent, -1);

        int maxLen = 1;
        int lastIndex = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && (1 + dp[j]) > dp[i]) {
                    dp[i] = 1 + dp[j];
                    parent[i] = j;
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                lastIndex = i;
            }
        }
        // Reconstruct LIS
        List<Integer> lis = new ArrayList<>();
        while (lastIndex != -1) {
            lis.add(nums[lastIndex]);
            lastIndex = parent[lastIndex];
        }
        Collections.reverse(lis);
        return lis;
    }
}
