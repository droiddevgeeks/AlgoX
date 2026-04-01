package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistanceMaze {
    public static void main(String[] args) {
        // Driver Code
        int[] source = {0, 1};
        int[] destination = {2, 2};

        // Define the grid
        int[][] grid = {
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 0},
                {1, 0, 0, 1}
        };

        int shortestPath = shortestPathMaze(grid, source, destination);
        System.out.println("ShortestDistanceMaze--->" + shortestPath);
    }

    private static int shortestPathMaze(int[][] grid, int[] source, int[] destination) {
        if (source[0] == destination[0] && source[1] == destination[1]) return 0;

        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        int m = grid.length;
        int n = grid[0].length;

        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], (int) 1e9);
        }

        dist[source[0]][source[1]] = 0;

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(source[0], source[1], 0));

        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int dis = pair.dist;
            int r = pair.row;
            int c = pair.col;
            for (int[] move : dirs) {
                int newR = r + move[0];
                int newC = c + move[1];

                if (newR >= 0 && newR < m && newC >= 0 && newC < n && grid[newR][newC] == 1 && dis + 1 < dist[newR][newC]) {
                    dist[newR][newC] = dis + 1;
                    if (newR == destination[0] && newC == destination[1]) return dis + 1;
                    queue.add(new Pair(newR, newC, dis + 1));
                }
            }
        }
        return -1;
    }

    private static class Pair {
        int row, col, dist;

        Pair(int r, int c, int d) {
            row = r;
            col = c;
            dist = d;
        }
    }
}
