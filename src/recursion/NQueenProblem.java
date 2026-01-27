package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Return all Distinct Solutions to the N-Queens Puzzle
 * Input: N = 4
 * Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 */
public class NQueenProblem {
    public static void main(String[] args) {
        int n = 4;
        char[][] board = new char[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }


        List<List<String>> result = new ArrayList<>();
        placeNQueenPureRecursion(0, board, result);
        System.out.println("Queen placed");
        for (List<String> temp : result) {
            System.out.println(temp);
        }
        result.clear();


        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n]; // row - col + n
        boolean[] diag2 = new boolean[2 * n]; // row + col
        // queenColumn[row] = column where queen is placed in that row
        int[] queenColumn = new int[n];
        System.out.println("Queen placed Optimized");
        placeNQueenOptimized(n, 0, queenColumn, cols, diag1, diag2, result);
        for (List<String> temp : result) {
            System.out.println(temp);
        }
    }

    private static void placeNQueenOptimized(int n, int row, int[] queenColumn, boolean[] cols, boolean[] diag1, boolean[] diag2, List<List<String>> result) {
        if (row == n) {
            result.add(buildBoard(queenColumn, n));
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n; // left diagonal
            int d2 = row + col;     // right diagonal

            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue; // not safe
            }
            // Place queen
            queenColumn[row] = col;
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            placeNQueenOptimized(n, row + 1, queenColumn, cols, diag1, diag2, result);

            // Backtrack
            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }

    // Build board strings from queenColumn[]
    private static List<String> buildBoard(int[] queenColumn, int n) {
        List<String> board = new ArrayList<>();

        for (int row = 0; row < n; row++) {
            StringBuilder sb = new StringBuilder();
            for (int col = 0; col < n; col++) {
                if (queenColumn[row] == col) sb.append('Q');
                else sb.append('.');
            }
            board.add(sb.toString());
        }

        return board;
    }

    private static void placeNQueenPureRecursion(int row, char[][] board, List<List<String>> res) {
        int n = board.length;
        if (row == n) {
            res.add(buildBoard(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                placeNQueenPureRecursion(row + 1, board, res);
                board[row][col] = '.';
            }
        }
    }


    private static List<String> buildBoard(char[][] board) {
        List<String> res = new ArrayList<>();
        for (char[] row : board) {
            res.add(new String(row));
        }
        return res;
    }

    private static boolean isSafe(char[][] board, int row, int col) {
        int n = board.length;

        // Check column
        for (char[] chars : board) {
            if (chars[col] == 'Q') return false;
        }

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }


}
