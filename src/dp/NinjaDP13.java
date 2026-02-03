package dp;

import java.util.Arrays;

/**
 * 3-d DP : Ninja and his friends (DP-13)
 * Problem Statement:  We are given an ‘N*M’ matrix. Every cell of the matrix has some chocolates on it,
 * mat[i][j] gives us the number of chocolates.
 * We have two friends ‘Alice’ and ‘Bob’. initially,
 * Alice is standing on the cell(0,0) and Bob is standing on the cell(0, M-1).
 * Both of them can move only to the cells below them in these three directions: to the bottom cell (↓), t
 * o the bottom-right cell(↘), or to the bottom-left cell(↙).
 * When Alica and Bob visit a cell, they take all the chocolates from that cell with them.
 * It can happen that they visit the same cell, in that case, the chocolates need to be considered only once.
 * They cannot go out of the boundary of the given matrix,
 * we need to return the maximum number of chocolates that Bob and Alice can together collect
 * Example 1:
 * Input: ‘R’ = 3, ‘C’ = 4
 * ‘GRID’ = [[2, 3, 1, 2], [3, 4, 2, 2], [5, 6, 3, 5]]
 * Output: 21
 * <p>
 * Example 2:
 * Input: ‘R’ = 2, ‘C’ = 3
 * ‘GRID’ = [[4, 1, 2], [7, 3, 5]]
 * Output: 22
 */
public class NinjaDP13 {
    public static void main(String[] args) {
        int r = 3, c = 4;
        int[][] grid = new int[][]{{2, 3, 1, 2}, {3, 4, 2, 2}, {5, 6, 3, 5}};

        int maxChocolate = calculateMaxChocolateRec(grid, 0, 0, c - 1, r, c);
        System.out.println("Max Chocolate Rec---->" + maxChocolate);


        int[][][] memo = new int[r][c][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        maxChocolate = calculateMaxChocolateMemo(grid, 0, 0, c - 1, r, c, memo);
        System.out.println("Max Chocolate Memo---->" + maxChocolate);

        maxChocolate = calculateMaxChocolateTabular(grid, r, c);
        System.out.println("Max Chocolate Tabular---->" + maxChocolate);
    }


    private static int calculateMaxChocolateRec(int[][] grid, int i, int j1, int j2, int r, int c) {
        if (j1 < 0 || j1 >= c || j2 < 0 || j2 >= c) return (int) -1e9;

        if (i == r - 1) {
            if (j1 == j2) return grid[i][j1];
            else return grid[i][j1] + grid[i][j2];
        }

        int max = (int) -1e9;

        for (int d1 = -1; d1 <= 1; d1++) {
            for (int d2 = -1; d2 <= 1; d2++) {
                int value;
                if (j1 == j2) value = grid[i][j1];
                else value = grid[i][j1] + grid[i][j2];
                value += calculateMaxChocolateRec(grid, i + 1, j1 + d1, j2 + d2, r, c);
                max = Math.max(max, value);
            }
        }
        return max;
    }


    private static int calculateMaxChocolateMemo(int[][] grid, int i, int j1, int j2, int r, int c, int[][][] memo) {
        if (j1 < 0 || j1 >= c || j2 < 0 || j2 >= c) return (int) -1e9;

        if (i == r - 1) {
            if (j1 == j2) return grid[i][j1];
            else return grid[i][j1] + grid[i][j2];
        }
        if (memo[i][j1][j2] != -1) return memo[i][j1][j2];

        int max = (int) -1e9;

        for (int d1 = -1; d1 <= 1; d1++) {
            for (int d2 = -1; d2 <= 1; d2++) {
                int value;
                if (j1 == j2) value = grid[i][j1];
                else value = grid[i][j1] + grid[i][j2];
                value += calculateMaxChocolateRec(grid, i + 1, j1 + d1, j2 + d2, r, c);
                max = Math.max(max, value);
            }
        }
        memo[i][j1][j2] = max;
        return memo[i][j1][j2];
    }

    private static int calculateMaxChocolateTabular(int[][] grid, int r, int c) {
        int[][][] dp = new int[r][c][c];
        for (int j1 = 0; j1 < c; j1++) {
            for (int j2 = 0; j2 < c; j2++) { // copy last row
                if (j1 == j2) dp[r - 1][j1][j2] = grid[r - 1][j1];
                else dp[r - 1][j1][j2] = grid[r - 1][j1] + grid[r - 1][j2];
            }
        }

        // build bottom to top
        for (int i = r - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < c; j1++) {
                for (int j2 = 0; j2 < c; j2++) {
                    int max = (int) -1e9;

                    for (int d1 = -1; d1 <= 1; d1++) {
                        for (int d2 = -1; d2 <= 1; d2++) {
                            int rj1 = j1 + d1;
                            int rj2 = j2 + d2;
                            if (rj1 < 0 || rj1 >= c || rj2 < 0 || rj2 >= c) continue;
                            int value;
                            if (j1 == j2) value = grid[i][j1];
                            else value = grid[i][j1] + grid[i][j2];
                            value += dp[i + 1][rj1][rj2];
                            max = Math.max(max, value);
                        }
                    }
                    dp[i][j1][j2] = max;
                }
            }
        }
        return dp[0][0][c - 1];
    }
}


