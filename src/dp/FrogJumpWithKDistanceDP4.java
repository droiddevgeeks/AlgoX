package dp;

import java.util.Arrays;

/**
 * Dynamic Programming: Frog Jump with k Distances (DP 4)
 * A frog wants to climb a staircase with n steps.
 * Given an integer array heights, where heights[i] contains the height of the ith step, and an integer k.
 * To jump from the ith step to the jth step, the frog requires abs(heights[i] - heights[j]) energy,
 * where abs() denotes the absolute difference. The frog can jump from the ith step to any step in the range [i + 1, i + k],
 * provided it exists. Return the minimum amount of energy required by the frog to go from the 0th step to the (n-1)th step.
 */
public class FrogJumpWithKDistanceDP4 {
    public static void main(String[] args) {
        //int[] ht = new int[]{10, 5, 20, 0, 15};
        int[] ht = new int[]{15, 4, 1, 14, 15};
        int k = 3;
        int n = ht.length - 1;
        int minEnergy = findFrogJumpWithKDistanceRecursion(ht, n, k);
        System.out.println("minEnergy---" + minEnergy);

        minEnergy = findFrogJumpWithKDistanceTabular(ht, ht.length, k);
        System.out.println("minEnergy Tabular---" + minEnergy);

        minEnergy = findFrogJumpWithKDistanceMemo(ht, k);
        System.out.println("minEnergy Memo---" + minEnergy);

    }

    private static int findFrogJumpWithKDistanceRecursion(int[] ht, int n, int k) {
        if (n == 0) return 0;

        int minEnergy = Integer.MAX_VALUE;
        for (int i = 1; i <= k; i++) {
            if (n - i >= 0) {
                int jumpCost = findFrogJumpWithKDistanceRecursion(ht, n - i, k) + Math.abs(ht[n] - ht[n - i]);
                minEnergy = Math.min(minEnergy, jumpCost);
            }
        }
        return minEnergy;
    }


    private static int findFrogJumpWithKDistanceTabular(int[] ht, int n, int k) {
        int[] dp = new int[n];
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            int minEnergy = Integer.MAX_VALUE;
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    int jumpCost = dp[i - j] + Math.abs(ht[i] - ht[i - j]);
                    minEnergy = Math.min(minEnergy, jumpCost);
                }
            }
            dp[i] = minEnergy;
        }
        return dp[n - 1];
    }

    private static int findFrogJumpWithKDistanceMemo(int[] ht, int k) {
        int n = ht.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return helper(memo, ht, n - 1, k);
    }

    private static int helper(int[] memo, int[] ht, int i, int k) {
        if (i == 0) return i; // base condition
        if (memo[i] != -1) return memo[i]; // if already calculate, return

        int minEnergy = Integer.MAX_VALUE;
        for (int j = 1; j <= k; j++) {
            if (i - j >= 0) {
                int jump = helper(memo, ht, i - j, k) + Math.abs(ht[i] - ht[i - j]);
                minEnergy = Math.min(minEnergy, jump);
            }
        }
        memo[i] = minEnergy;
        return memo[i];
    }
}
