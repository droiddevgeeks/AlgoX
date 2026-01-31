package dp;

import java.util.Arrays;

/**
 * Dynamic Programming : Climbing Stairs
 * Problem Statement: Given a number of stairs. Starting from the 0th stair we need to climb to the “Nth” stair.
 * At a time we can climb either one or two steps. We need to return the total number of distinct ways to reach from 0th to Nth stair.
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        int n = 4;
        int count = findTotalNumberOfWayMemorisation(n);
        System.out.println("ClimbingStairs===>" + count);

        count = findTotalNumberOfWayTabular(n);
        System.out.println("ClimbingStairs Tabular===>" + count);
    }

    private static int findTotalNumberOfWayTabular(int n) {
        if (n == 1 || n == 2) return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    private static int findTotalNumberOfWayMemorisation(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, 0);
        return helper(n, memo);
    }

    private static int helper(int n, int[] memo) {
        if (n == 1 || n == 2) return n; // base condition
        if (memo[n] != 0) return memo[n]; // if already calculate, return
        memo[n] = helper(n - 1, memo) + helper(n - 2, memo); //calculate & store
        return memo[n]; //return calculated value
    }
}
