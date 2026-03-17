package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * Problem Statement: Given a matrix mat of size N x M where every element is either ‘O’ or ‘X’.
 * Replace all ‘O’ with ‘X’ that is surrounded by ‘X’. An ‘O’ (or a set of ‘O’) is considered to be surrounded by ‘X’ if there are ‘X’ at locations just below,
 * just above just left, and just right of it.
 * <p>
 * Input: mat = [ ["X", "X", "X", "X"], ["X", "O", "O", "X"], ["X", "X", "O", "X"], ["X", "O", "X", "X"] ]
 * Output: [ ["X", "X", "X", "X"], ["X", "X", "X", "X"], ["X", "X", "X", "X"], ["X", "O", "X", "X"] ]
 * <p>
 * Input: mat = [ ["X", "X", "X"], ["X", "O", "X"], ["X", "X", "X"] ]
 * Output: [ ["X", "X", "X"], ["X", "X", "X"], ["X", "X", "X"] ]
 */
public class SurroundedRegions {
    public static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        String[][] mat = new String[][]{
                {"X", "X", "X", "X"},
                {"X", "O", "O", "X"},
                {"X", "X", "O", "X"},
                {"X", "O", "X", "X"}
        };
        convertSurroundedRegion(mat);
        for (String[] row : mat){
            System.out.println(Arrays.toString(row));
        }
    }

    private static void convertSurroundedRegion(String[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            if (Objects.equals(mat[i][0], "O")) queue.add(new int[]{i, 0}); //first column
            if (Objects.equals(mat[i][n - 1], "O")) queue.add(new int[]{i, n - 1}); //last column
        }

        for (int i = 0; i < n; i++) {
            if (Objects.equals(mat[0][i], "O")) queue.add(new int[]{0, i}); //first row
            if (Objects.equals(mat[m - 1][i], "O")) queue.add(new int[]{m - 1, i}); //last row
        }

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0];
            int c = cell[1];

            if (mat[r][c] != "O") continue;
            mat[r][c] = "#";

            for (int[] move : dirs) {
                int nr = r + move[0];
                int nc = c + move[1];
                if (nr >= 0 && nc >= 0 && nr < m && nc < n && mat[nr][nc] == "O") {
                    queue.add(new int[]{nr, nc});
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j <n ; j++) {
                if(Objects.equals(mat[i][j], "O")) mat[i][j] = "X";
                if(Objects.equals(mat[i][j], "#")) mat[i][j] = "O";
            }
        }
    }
}

/**
 * This problem is same as Number of Enclaves
 */
