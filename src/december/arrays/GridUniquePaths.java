package december.arrays;

import java.util.Arrays;

/**
 * Grid Unique Paths | Count paths from left-top to the right bottom of a matrix
 * Problem Statement: Given two integers m and n, representing the number of rows and columns of a 2d array named matrix.
 * Return the number of unique ways to go from the top-left cell (matrix[0][0]) to the bottom-right cell (matrix[m-1][n-1]).
 * Movement is allowed only in two directions from a cell: right and bottom.
 */
public class GridUniquePaths {

    public static void main(String[] args) {
        int m = 3, n = 2;
        //int m = 2, n = 4;
        int res = getUniquePaths(0, 0, m, n);
        System.out.println("GridUniquePaths==>" + res);

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        int resDP = getUniquePathsDP(0, 0, m, n, dp);
        System.out.println("GridUniquePathsDP==>" + resDP);
    }

    private static int getUniquePaths(int i, int j, int m, int n) {
        if (i == m - 1 && j == n - 1) return 1;
        if (i > m - 1 || j > n - 1) return 0;
        int right = getUniquePaths(i, j + 1, m, n);
        int down = getUniquePaths(i + 1, j, m, n);
        return right + down;
    }

    private static int getUniquePathsDP(int i, int j, int m, int n, int[][] dp) {
        if (i == m - 1 && j == n - 1) return 1;
        if (i > m - 1 || j > n - 1) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        dp[i][j] = getUniquePathsDP(i, j + 1, m, n, dp) + getUniquePathsDP(i + 1, j, m, n, dp);
        return dp[i][j];
    }

}
