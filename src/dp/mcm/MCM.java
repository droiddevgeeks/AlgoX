package dp.mcm;

import java.util.Arrays;

/**
 * Problem Statement: Given a chain of matrices A1,..., An denoted by an array of size n+1,
 * find out the minimum number of operations to multiply these n matrices .
 * Input :  [40, 20, 30, 10, 30]
 * Output :  26000
 * Input :  [10, 20, 30, 40, 30]
 * Output :  30000
 */
public class MCM {
    public static void main(String[] args) {
        int[] mat = new int[]{40, 20, 30, 10, 30};
        //int[] mat = new int[]{10, 20, 30, 40, 30};

        int minCostToMultiply = findMCMCostRec(mat, 1, mat.length - 1);
        System.out.println("MCM Rec-->" + minCostToMultiply);

        int[][] memo = new int[mat.length][mat.length];
        for (int[] temp : memo) Arrays.fill(temp, -1);
        minCostToMultiply = findMCMCostMemo(mat, 1, mat.length - 1, memo);
        System.out.println("MCM Memo-->" + minCostToMultiply);

        minCostToMultiply = findMCMCostTabular(mat);
        System.out.println("MCM Tabular-->" + minCostToMultiply);

        printMCMCostTabular(mat);
    }

    private static int findMCMCostRec(int[] mat, int i, int j) {
        if (i == j) return 0;
        int minCost = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int cost = findMCMCostRec(mat, i, k) + findMCMCostRec(mat, k + 1, j) + mat[i - 1] * mat[k] * mat[j];
            minCost = Math.min(cost, minCost);
        }
        return minCost;
    }

    private static int findMCMCostMemo(int[] mat, int i, int j, int[][] memo) {
        if (i == j) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        int minCost = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int cost = findMCMCostMemo(mat, i, k, memo) + findMCMCostMemo(mat, k + 1, j, memo) + mat[i - 1] * mat[k] * mat[j];
            minCost = Math.min(cost, minCost);
        }
        return memo[i][j] = minCost;
    }

    private static int findMCMCostTabular(int[] mat) {
        int n = mat.length;
        int[][] dp = new int[n][n];

        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j < n; j++) {
                int minCost = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + mat[i - 1] * mat[k] * mat[j];
                    minCost = Math.min(cost, minCost);
                }
                dp[i][j] = minCost;
            }
        }
        return dp[1][n - 1];
    }

    private static void printMCMCostTabular(int[] mat) {
        int n = mat.length;
        int[][] dp = new int[n][n];
        int[][] split = new int[n][n];

        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j < n; j++) {
                int minCost = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + mat[i - 1] * mat[k] * mat[j];
                    if (cost < minCost) {
                        minCost = cost;
                        split[i][j] = k;
                    }
                }
                dp[i][j] = minCost;
            }
        }
        System.out.print("Optimal Parenthesization: ");
        printParenthesis(1, n - 1, split);
    }

    private static void printParenthesis( int i, int j, int[][]split){
        if( i == j){
            System.out.print("A"+i);
            return;
        }
        System.out.print("(");
        int k = split[i][j];
        printParenthesis(i, k, split);
        printParenthesis(k+1, j, split);
        System.out.print(")");
    }
}
