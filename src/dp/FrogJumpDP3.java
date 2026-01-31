package dp;

import java.util.Arrays;

/**
 * Problem Statement: Given a number of stairs and a frog, the frog wants to climb from the 0th stair to the (N-1)th stair.
 * At a time the frog can climb either one or two steps.
 * A height[N] array is also given. Whenever the frog jumps from a stair i to stair j,
 * the energy consumed in the jump is abs(height[i]- height[j]), where abs() means the absolute difference.
 * We need to return the minimum energy that can be used by the frog to jump from stair 0 to stair N-1..
 */
public class FrogJumpDP3 {
    public static void main(String[] args) {
        //int[] ht = new int[]{2, 1, 3, 5, 4};
        int[] ht = new int[]{7, 5, 1, 2, 6};
        int minEnergyReq = findMinEnergyRequiredRecursion(ht.length - 1, ht);
        System.out.println("minEnergyReq===" + minEnergyReq);

        minEnergyReq = findMinEnergyRequiredMemo(ht);
        System.out.println("minEnergyReq===" + minEnergyReq);

        minEnergyReq = findMinEnergyRequiredTabular(ht);
        System.out.println("minEnergyReq===" + minEnergyReq);

    }

    private static int findMinEnergyRequiredRecursion(int n, int[] ht) {
        if (n == 0) return 0;
        int step1 = findMinEnergyRequiredRecursion(n - 1, ht) + Math.abs(ht[n] - ht[n - 1]);
        int step2 = Integer.MAX_VALUE;
        if (n > 1) {
            step2 = findMinEnergyRequiredRecursion(n - 2, ht) + Math.abs(ht[n] - ht[n - 2]);
        }
        return Math.min(step1, step2);
    }

    private static int findMinEnergyRequiredTabular(int[] ht) {
        int[] dp = new int[ht.length + 1];
        int n = ht.length;
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int step1 = dp[i - 1] + Math.abs(ht[i] - ht[i - 1]);
            int step2 = Integer.MAX_VALUE;
            if (i > 1) {
                step2 = dp[i - 2] + Math.abs(ht[i] - ht[i - 2]);
            }
            dp[i] = Math.min(step1, step2);
        }
        return dp[n - 1];
    }

    private static int findMinEnergyRequiredMemo(int[] ht) {
        int[] memo = new int[ht.length + 1];
        Arrays.fill(memo, -1);
        return helper(ht.length - 1, memo, ht);
    }

    private static int helper(int n, int[] memo, int[] ht) {
        if (n == 0) return n; // base condition
        if (memo[n] != -1) return memo[n]; // if already calculate, return
        int step1 = helper(n - 1, memo, ht) + Math.abs(ht[n] - ht[n - 1]);
        int step2 = Integer.MAX_VALUE;
        if (n > 1) step2 = helper(n - 2, memo, ht) + Math.abs(ht[n] - ht[n - 2]);
        memo[n] = Math.min(step1, step2); //calculate & store
        return memo[n]; //return calculated value
    }

}
