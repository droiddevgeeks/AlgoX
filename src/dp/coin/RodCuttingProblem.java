package dp.coin;

import java.util.Arrays;

/**
 * Problem Statement: Given a rod of length N inches and an array price[] where price[i]
 * denotes the value of a piece of rod of length i inches (1-based indexing).
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
 * Make any number of cuts, or none at all, and sell the resulting pieces.
 * Input : price = [1, 6, 8, 9, 10, 19, 7, 20], N = 8
 * Output :25
 * Explanation :Cut the rod into lengths of 2 and 6 for a total price of 6 + 19= 25.
 * <p>
 * Input :price = [1, 5, 8, 9], N = 4
 * Output :10
 * Explanation :Cut the rod into lengths of 2 and 2 for a total price of 5 + 5 = 10.
 */

public class RodCuttingProblem {
    public static void main(String[] args) {
        int[] price = new int[]{1, 6, 8, 9, 10, 19, 7, 20};
        int len = 8;
        int maxValue = findMaxValueFromCutRec(price, len - 1, len);
        System.out.println("RodCuttingProblem Rec--->" + maxValue);

        int[][] memo = new int[len][len + 1];
        for (int[] temp : memo) Arrays.fill(temp, -1);
        maxValue = findMaxValueFromCutMemo(price, memo, len - 1, len);
        System.out.println("RodCuttingProblem Memo--->" + maxValue);

        maxValue = findMaxValueFromCutTabular(price, len);
        System.out.println("RodCuttingProblem Tabular--->" + maxValue);
    }

    private static int findMaxValueFromCutRec(int[] price, int index, int len) {
        if (index == 0) {
            return len * price[0];
        }

        int notPick = findMaxValueFromCutRec(price, index - 1, len);
        int pick = Integer.MIN_VALUE;
        if ((index + 1) <= len) pick = price[index] + findMaxValueFromCutRec(price, index, len - (index + 1));

        return Math.max(pick, notPick);
    }

    private static int findMaxValueFromCutMemo(int[] price, int[][] memo, int index, int len) {
        if (index == 0) {
            return len * price[0];
        }

        if (memo[index][len] != -1) return memo[index][len];
        int notPick = findMaxValueFromCutRec(price, index - 1, len);
        int pick = Integer.MIN_VALUE;
        if ((index + 1) <= len) pick = price[index] + findMaxValueFromCutRec(price, index, len - (index + 1));
        return memo[index][len] = Math.max(pick, notPick);
    }

    //dp[i][l] = max value using pieces [0..i] to fill length l
    private static int findMaxValueFromCutTabular(int[] price, int len) {
        int[][] dp = new int[len][len+1];

        for (int l = 0; l <= len; l++) {
            dp[0][l] = l * price[0];
        }

        for (int i = 1; i < len; i++) {
            for (int l = 0; l <= len; l++) {
                int notPick = dp[i - 1][l];
                int pick = Integer.MIN_VALUE;
                if ((i + 1) <= l) {
                    pick = price[i]+ dp[i][l - (i + 1)];
                }
                dp[i][l] = Math.max(notPick, pick);
            }
        }
        for (int [] t : dp) System.out.println(Arrays.toString(t));
        return dp[len - 1][len];
    }
}
