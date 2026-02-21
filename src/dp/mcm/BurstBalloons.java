package dp.mcm;

import java.util.Arrays;

/**
 * You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array.
 * You are asked to burst all the balloons.
 * If you burst the ith balloon, you will get arr[i - 1] * arr[i] * arr[i + 1] coins. If i - 1 or i + 1 goes out of the array's bounds,
 * then treat it as if there is a balloon with a 1 painted on it.
 * Return the maximum coins you can collect by bursting the balloons wisely.
 * Example 1:
 * Input: N = 4, array[] = {3, 1, 5, 8}
 * Output: 167
 * Explanation: First, we will burst the second balloon with the value 1. Coins = 3*1*5 = 15. Second, we will burst the balloon with the value 5. Coins = 3*5*8 = 120. Third, we will burst the balloon with the value 3. Coins = 1*3*8 = 24. Fourth, we will burst the balloon with the value 8. Coins = 1*8*1 = 8. So, the total number of coins we can collect is 167. This is the maximum number of coins we can collect.
 */
public class BurstBalloons {
    public static void main(String[] args) {
        int[] bals = new int[]{3, 1, 5, 8};
        int n = bals.length;

        int[] newBals = new int[n + 2];
        newBals[0] = 1;
        newBals[n + 1] = 1;
        for (int i = 0; i < n; i++) {
            newBals[i + 1] = bals[i];
        }
        int maxBurstValue = calculateBurstBalloonsRec(newBals, 1, n);
        System.out.println("BurstBalloons Rec--->" + maxBurstValue);

        int[][] memo = new int[n + 2][n + 2];
        int[][] splitIndex = new int[n + 2][n + 2];
        for (int[] temp : memo) {
            Arrays.fill(temp, -1);
        }
        maxBurstValue = calculateBurstBalloonsMemo(newBals, 1, n, memo, splitIndex);
        System.out.println("BurstBalloons Memo--->" + maxBurstValue);
        System.out.println("Print Split using memo");
        printSplit(splitIndex, 1, n);

        System.out.println("\nPrint Split using Tabular");
        maxBurstValue = calculateBurstBalloonsTabular(newBals);
        System.out.println("\nBurstBalloons Tabular--->" + maxBurstValue);
    }

    private static int calculateBurstBalloonsRec(int[] bals, int i, int j) {
        if (i > j) return 0;
        int maxValue = Integer.MIN_VALUE;
        for (int k = i; k <= j; k++) {
            int leftPart = calculateBurstBalloonsRec(bals, i, k - 1);
            int value = bals[i - 1] * bals[k] * bals[j + 1];
            int rightPart = calculateBurstBalloonsRec(bals, k + 1, j);
            int totalValue = leftPart + value + rightPart;

            maxValue = Math.max(totalValue, maxValue);
        }
        return maxValue;
    }

    private static int calculateBurstBalloonsMemo(int[] bals, int i, int j, int[][] memo, int[][] splitIndex) {
        if (i > j) return 0;

        if (memo[i][j] != -1) return memo[i][j];
        int maxValue = Integer.MIN_VALUE;
        for (int k = i; k <= j; k++) {
            int leftPart = calculateBurstBalloonsMemo(bals, i, k - 1, memo, splitIndex);
            int value = bals[i - 1] * bals[k] * bals[j + 1];
            int rightPart = calculateBurstBalloonsMemo(bals, k + 1, j, memo, splitIndex);
            int totalValue = leftPart + value + rightPart;
            if (totalValue > maxValue) {
                maxValue = totalValue;
                splitIndex[i][j] = k;
            }
        }
        return memo[i][j] = maxValue;
    }

    private static void printSplit(int[][] split, int i, int j) {
        if (i > j) return;
        int k = split[i][j];
        printSplit(split, i, k - 1);
        printSplit(split, k + 1, j);
        System.out.print(k + " ");
    }

    private static int calculateBurstBalloonsTabular(int[] bals) {
        int n = bals.length;
        int[][] dp = new int[n][n];
        int[][] splitIndex = new int[n][n];
        for (int i = n - 2; i >= 1; i--) {
            for (int j = i; j <= n - 2; j++) { // j =i bcoz of diagonal element
                int max = Integer.MIN_VALUE;
                for (int k = i; k <= j; k++) {
                    int value = dp[i][k - 1] + bals[i - 1] * bals[k] * bals[j + 1] + dp[k + 1][j];
                    if (value > max) {
                        max = value;
                        splitIndex[i][j] = k;
                    }
                }
                dp[i][j] = max;
            }
        }
        printSplit(splitIndex, 1, n - 2);
        return dp[1][n - 2];
    }
}
