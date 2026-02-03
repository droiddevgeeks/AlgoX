package dp;

import java.util.Arrays;

/**
 * Problem Statement: Given two integers m and n, representing the number of rows and columns of a 2d array named matrix.
 * Return the number of unique ways to go from the top-left cell (matrix[0][0]) to the bottom-right cell (matrix[m-1][n-1]).
 * Movement is allowed only in two directions from a cell: right and bottom.
 * Input:m = 3, n = 2
 * Output: 3
 * Input:m = 2, n = 4
 * Output:4
 */
public class GridUniquePaths {
    public static void main(String[] args) {
        int m = 3;
        int n = 2;
        int uniquePaths = findGridUniquePathsRec(m - 1, n - 1);
        System.out.println("GridUniquePaths Rec--->" + uniquePaths);

        int[][] dp = new int[m][n];
        for (int[] t : dp) {
            Arrays.fill(t, -1);
        }
        uniquePaths = findGridUniquePathsMemo(m - 1, n - 1, dp);
        System.out.println("GridUniquePaths Memo--->" + uniquePaths);

        for (int[] t : dp) {
            Arrays.fill(t, -1);
        }
        uniquePaths = findGridUniquePathsTabular(m, n, dp);
        System.out.println("GridUniquePaths Memo--->" + uniquePaths);
    }

    private static int findGridUniquePathsRec(int m, int n) {
        if (m == 0 && n == 0) return 1;
        if (m < 0 || n < 0) return 0;

        int up = findGridUniquePathsRec(m - 1, n);
        int left = findGridUniquePathsRec(m, n - 1);
        return up + left;
    }

    private static int findGridUniquePathsMemo(int m, int n, int[][] dp) {
        if (m == 0 && n == 0) return 1;
        if (m < 0 || n < 0) return 0;
        if (dp[m][n] != -1) return dp[m][n];
        int up = findGridUniquePathsMemo(m - 1, n, dp);
        int left = findGridUniquePathsMemo(m, n - 1, dp);
        dp[m][n] = up + left;
        return dp[m][n];
    }

    private static int findGridUniquePathsTabular(int m, int n, int[][] dp) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else {
                    int up = (i > 0) ? dp[i - 1][j] : 0;
                    int left = (j > 0) ? dp[i][j - 1] : 0;
                    dp[i][j] = up + left;
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
