package dp;

import java.util.Arrays;

/**
 * Minimum/Maximum Falling Path Sum (DP-12)
 * Problem Description: Given a 2D array called matrix consisting of integer values
 * return the maximum path sum that can be obtained by starting at any cell in the first row and ending at any cell in the last row.
 * Movement is allowed only to the bottom, bottom-right, or bottom-left cell of the current cell.
 * Input:matrix = [[1, 2, 10, 4], [100, 3, 2, 1], [1, 1, 20, 2], [1, 2, 2, 1]]
 * Output: 105
 * Input: matrix = [[1, 4, 3, 1], [2, 3, -1, -1], [1, 1, -1, 8]]
 * Output: 11
 */
public class FallingPathSumDp12 {
    public static void main(String[] args) {
//        int[][] grid = new int[][]{
//                {1, 4, 3, 1},
//                {2, 3, -1, -1},
//                {1, 1, -1, 8}
//        };
        int[][] grid = new int[][]{
                {1, 2, 10, 4},
                {100, 3, 2, 1},
                {1, 1, 20, 2},
                {1, 2, 2, 1}
        };

        int n = grid.length;
        int m = grid[0].length;
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) { //loop column wise on first row
            ans = Math.min(ans, findFallingPathSumDp12Rec(grid, 0, j, n, m));
        }
        System.out.println("minPathSum Rec--->" + ans);

        int[][] memo = new int[n][m];
        for (int[] temp : memo) Arrays.fill(temp, -1);
        for (int j = 0; j < m; j++) { //loop column wise on first row
            ans = Math.min(ans, findFallingPathSumDp12Memo(grid, memo, 0, j, n, m));
        }
        System.out.println("minPathSum Memo--->" + ans);
        ans = findFallingPathSumDp12Tabular(grid, n, m);
        System.out.println("minPathSum tabular--->" + ans);
    }

    private static int findFallingPathSumDp12Rec(int[][] grid, int i, int j, int n, int m) {
        if (j < 0 || j >= m) return Integer.MAX_VALUE;
        if (i == n - 1) return grid[i][j];

        int downLeft = findFallingPathSumDp12Rec(grid, i + 1, j - 1, n, m);
        int down = findFallingPathSumDp12Rec(grid, i + 1, j, n, m);
        int downRight = findFallingPathSumDp12Rec(grid, i + 1, j + 1, n, m);
        return grid[i][j] + Math.min(down, Math.min(downLeft, downRight));
    }

    private static int findFallingPathSumDp12Memo(int[][] grid, int[][] memo, int i, int j, int n, int m) {
        if (j < 0 || j >= m) return Integer.MAX_VALUE;
        if (i == n - 1) return grid[i][j];

        if (memo[i][j] != -1) return memo[i][j];

        int downLeft = findFallingPathSumDp12Rec(grid, i + 1, j - 1, n, m);
        int down = findFallingPathSumDp12Rec(grid, i + 1, j, n, m);
        int downRight = findFallingPathSumDp12Rec(grid, i + 1, j + 1, n, m);
        memo[i][j] = grid[i][j] + Math.min(down, Math.min(downLeft, downRight));
        return memo[i][j];
    }

    private static int findFallingPathSumDp12Tabular(int[][] grid, int n, int m) {
        int[][] dp = new int[n][m];

        for (int col = 0; col < m; col++) {
            dp[n - 1][col] = grid[n - 1][col];
        }

        // build upwards
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                int down = dp[i + 1][j];
                int downLeft = (j > 0) ? dp[i + 1][j - 1] : Integer.MAX_VALUE;
                int downRight = (j < m - 1) ? dp[i + 1][j + 1] : Integer.MAX_VALUE;
                dp[i][j] = grid[i][j] + Math.min(down, Math.min(downLeft, downRight));
            }
        }

        // answer = min in first row
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) {
            ans = Math.min(ans, dp[0][j]);
        }
        return ans;
    }

}
