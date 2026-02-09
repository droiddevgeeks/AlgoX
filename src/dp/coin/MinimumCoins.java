package dp.coin;

import java.util.Arrays;

/**
 * Minimum Coins (DP - 20)
 * Problem Statement: Given an integer array of coins representing coins of different denominations and an integer
 * amount representing a total amount of money. Return the fewest number of coins that are needed to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1. There are infinite numbers of coins of each type
 * Examples
 * Input: coins = [1, 2, 5], amount = 11, Output: 3
 * Explanation: 11 = 5 + 5 + 1. We need 3 coins to make up the amount 11.
 * Input : coins = [2, 5], amount = 3,Output: -1
 * Explanation :  It's not possible to make amount 3 with coins 2 and 5. Since we can't combine the coin 2 and 5 to make the amount 3, the output is -1.
 */
public class MinimumCoins {
    public static void main(String[] args) {
        int[] coins = new int[]{1, 2, 5};
        int amount = 11;

        int minCoins = findMinimumCoinsRec(coins, coins.length - 1, amount);
        System.out.println("MinimumCoins rec--->" + minCoins);

        int[][] memo = new int[coins.length][amount + 1];
        for (int[] temp : memo) {
            Arrays.fill(temp, -1);
        }
        minCoins = findMinimumCoinsMemo(coins, memo, coins.length - 1, amount);
        System.out.println("MinimumCoins Memo--->" + minCoins);

        minCoins = findMinimumCoinsTabular(coins, amount);
        System.out.println("MinimumCoins Tabular--->" + minCoins);
    }

    private static int findMinimumCoinsRec(int[] coins, int index, int amount) {
        if (amount == 0) return 0;
        if (index == 0) { // 1 element only remaining
            if (amount % coins[index] == 0) return amount / coins[index];
            else return (int) 1e9;
        }
        int notPick = findMinimumCoinsRec(coins, index - 1, amount);
        int pick = (int) 1e9;
        if (coins[index] <= amount) {
            pick = 1 + findMinimumCoinsRec(coins, index, amount - coins[index]);
        }
        return Math.min(pick, notPick);
    }

    private static int findMinimumCoinsMemo(int[] coins, int[][] memo, int index, int amount) {
        if (amount == 0) return 0;
        if (index == 0) { // 1 element only remaining
            if (amount % coins[index] == 0) return amount / coins[index];
            else return (int) 1e9;
        }
        if (memo[index][amount] != -1) return memo[index][amount];
        int notPick = findMinimumCoinsRec(coins, index - 1, amount);
        int pick = (int) 1e9;
        if (coins[index] <= amount) {
            pick = 1 + findMinimumCoinsRec(coins, index, amount - coins[index]);
        }
        memo[index][amount] = Math.min(pick, notPick);
        return memo[index][amount];
    }

    private static int findMinimumCoinsTabular(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        // base row
        for (int t = 0; t <= amount; t++) {
            if (t % coins[0] == 0) dp[0][t] = t / coins[0];
            else dp[0][t] = (int) 1e9;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= amount; j++) {
                int notPick = dp[i - 1][j];
                int pick = (int) 1e9;
                if (coins[i] <= j) pick = 1 + dp[i][j - coins[i]];
                dp[i][j] = Math.min(notPick, pick);
            }
        }
        return dp[n - 1][amount] >= (int) 1e9 ? -1 : dp[n - 1][amount];
    }
}
