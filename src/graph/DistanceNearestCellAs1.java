package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem Statement: Given a binary grid of N*M. Find the distance of the nearest 1 in the grid for each cell.
 * Input: grid = [ [0, 1, 1, 0], [1, 1, 0, 0], [0, 0, 1, 1] ]
 * Output: [ [1, 0, 0, 1], [0, 0, 1, 1], [1, 1, 0, 0] ]
 * <p>
 * Input: grid = [ [1, 0, 1], [1, 1, 0], [1, 0, 0] ]
 * Output: [ [0, 1, 0], [0, 0, 1], [0, 1, 2] ]
 */
public class DistanceNearestCellAs1 {
    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 1, 0},
                {1, 1, 0, 0},
                {0, 0, 1, 1}
        };

        int[][] grid2 = {
                {1, 0, 1},
                {1, 1, 0},
                {1, 0, 0}
        };

        //int[][] distance = findDistanceOfNearestOne(grid);
        int[][] distance = findDistanceOfNearestOne(grid2);
        for (int[] row : distance) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * O(N × M) time
     * O(N × M) Space
     */
    private static int[][] findDistanceOfNearestOne(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<Triplet> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        int[][] distance = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.add(new Triplet(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            Triplet triplet = queue.poll();
            int r = triplet.r;
            int c = triplet.c;
            int dist = triplet.dist;

            distance[r][c] = dist;

            for (int[] move : dirs) {
                int nr = r + move[0];
                int nc = c + move[1];
                if (nr >= 0 && nc >= 0 && nr < m && nc < n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.add(new Triplet(nr, nc, 1 + dist));
                }
            }
        }
        return distance;
    }


    private static class Triplet {
        int r, c, dist;

        public Triplet(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }
}
