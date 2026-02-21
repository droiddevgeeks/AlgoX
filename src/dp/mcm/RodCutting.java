package dp.mcm;

import java.util.Arrays;

/**
 * Input: n = 7, cuts = [1, 3, 4, 5]
 * Output: 16
 * Input: n = 7, cuts = [1, 3, 6]
 * Output: 14
 */
public class RodCutting {
    public static void main(String[] args) {
        int n = 7;
        int[] originalCuts = new int[]{1, 3, 4, 5};
        //int [] cuts = new int[]{1, 3, 6};

        int m = originalCuts.length;
        int[] cuts = new int[m + 2];
        cuts[0] = 0;
        cuts[m + 1] = n;
        for (int i = 0; i < m; i++)
            cuts[i + 1] = originalCuts[i];
        Arrays.sort(cuts);

        int minCutCost = findMinCutCostRec(cuts, 0, cuts.length - 1);
        System.out.println("RodCutting Rec--->" + minCutCost);


        int[][] memo = new int[cuts.length][cuts.length];
        for (int[] temp : memo) Arrays.fill(temp, -1);
        minCutCost = findMinCutCostMemo(cuts, 0, cuts.length - 1, memo);
        System.out.println("RodCutting Memo--->" + minCutCost);

        minCutCost = findMinCutCostTabular(cuts);
        System.out.println("RodCutting Tabular--->" + minCutCost);
    }

    private static int findMinCutCostRec(int[] cuts, int i, int j) {
        if (j - i <= 1) return 0;
        int minCost = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            int cost = findMinCutCostRec(cuts, i, k) + findMinCutCostRec(cuts, k, j) + cuts[j] - cuts[i];
            minCost = Math.min(minCost, cost);
        }
        return minCost;
    }

    private static int findMinCutCostMemo(int[] cuts, int i, int j, int[][] memo) {
        if (j - i <= 1) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        int minCost = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            int cost = findMinCutCostMemo(cuts, i, k, memo) + findMinCutCostMemo(cuts, k, j, memo) + cuts[j] - cuts[i];
            minCost = Math.min(minCost, cost);
        }
        return memo[i][j] = minCost;
    }

    private static int findMinCutCostTabular(int[] cuts) {
        int m = cuts.length;
        int[][] dp = new int[m][m];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = i + 2; j < m; j++) { //we just need at-least 1 cut between i & j so j = i+2. That’s why rod cutting skips diagonal.
                int minCost = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    int cost = dp[i][k] + dp[k][j] + cuts[j] - cuts[i];
                    minCost = Math.min(minCost, cost);
                }
                dp[i][j]= minCost;
            }
        }
        return dp[0][m - 1];
    }
}
