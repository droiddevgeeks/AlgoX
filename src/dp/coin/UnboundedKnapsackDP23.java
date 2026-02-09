package dp.coin;

import java.util.Arrays;

/**
 * A thief wants to rob a store. He is carrying a bag of capacity W.
 * The store has ‘n’ items of infinite supply. Its weight is given by the ‘wt’ array and its value by the ‘val’ array.
 * He can either include an item in its knapsack or exclude it but can’t partially have it as a fraction.
 * We need to find the maximum value of items that the thief can steal.
 * He can take a single item any number of times he wants and put it in his knapsack .
 * Input: n = 3, W = 8, wt = [2, 4, 6], val = [5, 11, 13], Output: 22
 * Explanation:We can take item with weight 2 (value 5) four times to fill capacity 8,total value = 5 × 4 = 20.
 * But a better choice: take item with weight 2 (value 5) twice and item with weight 4 (value 11) once → total weight = 2 + 2 + 4 = 8, total value = 5 + 5 + 11 = 21.
 * Even better: take two items with weight 4 (value 11 each), total value = 22, which is maximum.
 * Input: n = 2, W = 3, wt = [2, 1], val = [4, 2], Output: 6
 * Explanation:We can take item with weight 1 (value 2) three times , total value = 6.
 * Taking weight 2 (value 4) plus weight 1 (value 2) also gives 6. No combination yields more than 6.
 */

public class UnboundedKnapsackDP23 {
    public static void main(String[] args) {
        int[] value = new int[]{5, 11, 13};
        int[] wt = new int[]{2, 4, 6};
        int capacity = 8;

        int maxValue = findUnboundedKnapsackRec(capacity, wt, wt.length - 1, value);
        System.out.println("UnboundedKnapsackDP23---->" + maxValue);

        int[][] memo = new int[wt.length][capacity + 1];
        for (int[] temp : memo) Arrays.fill(temp, -1);
        maxValue = findUnboundedKnapsackMemo(capacity, wt, wt.length - 1, value, memo);
        System.out.println("UnboundedKnapsackDP23 Memo---->" + maxValue);

        maxValue = findUnboundedKnapsackTabular(capacity, wt, value);
        System.out.println("UnboundedKnapsackDP23 Tabular---->" + maxValue);
    }

    private static int findUnboundedKnapsackRec(int capacity, int[] wt, int index, int[] value) {
        if (index == 0) {
            return (capacity / wt[index]) * value[index];
        }
        int notPick = findUnboundedKnapsackRec(capacity, wt, index - 1, value);
        int pick = Integer.MIN_VALUE;
        if (wt[index] <= capacity)
            pick = value[index] + findUnboundedKnapsackRec(capacity - wt[index], wt, index, value);

        return Math.max(pick, notPick);
    }

    private static int findUnboundedKnapsackMemo(int capacity, int[] wt, int index, int[] value, int[][] memo) {
        if (index == 0) {
            return (capacity / wt[index]) * value[index];
        }

        if (memo[index][capacity] != -1) return memo[index][capacity];
        int notPick = findUnboundedKnapsackRec(capacity, wt, index - 1, value);
        int pick = Integer.MIN_VALUE;
        if (wt[index] <= capacity)
            pick = value[index] + findUnboundedKnapsackRec(capacity - wt[index], wt, index, value);

        memo[index][capacity] = Math.max(pick, notPick);
        return memo[index][capacity];
    }

    //dp[i][c] = max value using items [0..i] with capacity c
    private static int findUnboundedKnapsackTabular(int capacity, int[] wt, int[] value) {
        int n = wt.length;
        int[][] dp = new int[n][capacity + 1];

        for (int c = 1; c <= capacity; c++) {
            dp[0][c] = (c / wt[0]) * value[0];
        }

        for (int i = 1; i < n; i++) {
            for (int c = 0; c <= capacity; c++) {
                int notPick = dp[i - 1][c];
                int pick = Integer.MIN_VALUE;
                if (wt[i] <= c) {
                    pick = value[i] + dp[i][c - wt[i]];
                }

                dp[i][c] = Math.max(notPick, pick);
            }
        }

        for (int [] t : dp) System.out.println(Arrays.toString(t));
        return dp[n-1][capacity];
    }
}
