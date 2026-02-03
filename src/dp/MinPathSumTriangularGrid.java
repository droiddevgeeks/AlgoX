package dp;

import java.util.Arrays;

/**
 * Problem Statement: Given a 2D integer array named triangle with n rows.
 * Its first row has 1 element and each succeeding row has one more element in it than the row above it.
 * Return the minimum falling path sum from the first row to the last.
 * Movement is allowed only to the bottom or bottom-right cell from the current cell.
 * Input: triangle = [[1], [1, 2], [1, 2, 4]]
 * Output: 3
 * Input : triangle = [[1], [4, 7], [4,10, 50], [-50, 5, 6, -100]]
 * Output: -42
 */
public class MinPathSumTriangularGrid {
    public static void main(String[] args) {
        //int[][] grid = new int[][]{{1}, {1, 2}, {1, 2, 4}};
        int[][] grid = new int[][]{
                {1},
                {4, 7},
                {4, 10, 50},
                {-50, 5, 6, -100}
        };

        int minPathSum = findMinPathSumTriangularGridRec(grid, 0, 0, grid.length);
        System.out.println("minPathSum Rec--->" + minPathSum);

        int[][] memo = new int[grid.length][grid.length];
        for (int[] temp : memo) {
            Arrays.fill(temp, -1);
        }

        minPathSum = findMinPathSumTriangularGridMemo(grid, memo, 0, 0, grid.length);
        System.out.println("minPathSum Memo--->" + minPathSum);

        minPathSum = findMinPathSumTriangularGridTabular(grid, grid.length);
        System.out.println("minPathSum Tabular--->" + minPathSum);
    }

    private static int findMinPathSumTriangularGridRec(int[][] grid, int r, int c, int n) {
        if (r == n - 1) {
            return grid[r][c];
        }
        int down = findMinPathSumTriangularGridRec(grid, r + 1, c, n);
        int rightDown = findMinPathSumTriangularGridRec(grid, r + 1, c + 1, n);
        return grid[r][c] + Math.min(down, rightDown);
    }

    private static int findMinPathSumTriangularGridMemo(int[][] grid, int[][] memo, int r, int c, int n) {
        if (r == n - 1) {
            return grid[r][c];
        }
        if (memo[r][c] != -1) return memo[r][c];
        int down = findMinPathSumTriangularGridRec(grid, r + 1, c, n);
        int rightDown = findMinPathSumTriangularGridRec(grid, r + 1, c + 1, n);
        memo[r][c] = grid[r][c] + Math.min(down, rightDown);
        return memo[r][c];
    }

    private static int findMinPathSumTriangularGridTabular(int[][] grid, int n) {
        int[][] dp = new int[n][n];

        for (int col = 0; col < n; col++) {
            dp[n - 1][col] = grid[n - 1][col];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }
        return dp[0][0];
    }
}
