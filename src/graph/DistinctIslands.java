package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Input: grid = [[1, 1, 0, 0, 0], [1, 1, 0, 0, 0], [0, 0, 0, 1, 1],[0, 0, 0, 1, 1]]
 * Output: 1
 * Input: grid = [[1, 1, 0, 1, 1], [1, 0, 0, 0, 0], [0, 0, 0, 0, 1],[1, 1, 0, 1, 1]]
 * Output: 3
 */
public class DistinctIslands {
    public static int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        /*int[][] grid = new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}
        };*/

        int[][] grid = new int[][]{
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}
        };

        int uniqueIslands = findUniqueIslands(grid);
        System.out.println("DistinctIslands--->" + uniqueIslands);
    }

    /**
     * Time: O(m * n)
     * Space: O(m * n) (visited + recursion + set)
     */
    private static int findUniqueIslands(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Set<String> set = new HashSet<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    List<String> shape = new ArrayList<>();
                    dfs(i, j, i, j, shape, grid, visited);
                    set.add(String.join(",", shape));
                }
            }
        }
        return set.size();
    }

    private static void dfs(int r, int c, int baseR, int baseC, List<String> shape, int[][] grid, boolean[][] visited) {
        visited[r][c] = true;
        shape.add((r - baseR) + "_" + (c - baseC));
        for (int[] move : dirs) {
            int nr = r + move[0];
            int nc = c + move[1];
            if (nr >= 0 && nc >= 0
                    && nr < grid.length && nc < grid[0].length
                    && grid[nr][nc] == 1
                    && !visited[nr][nc]) {
                dfs(nr, nc, baseR, baseC, shape, grid, visited);
            }
        }
    }
}
