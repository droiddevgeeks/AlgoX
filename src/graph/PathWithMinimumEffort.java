package graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Problem Statement: You are a hiker preparing for an upcoming hike.
 * You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of the cell (row, col).
 * You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e.,0-indexed).
 * You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 */
public class PathWithMinimumEffort {
    public static void main(String[] args) {
        int[][] heights = {
                {1, 2, 2},
                {3, 8, 2},
                {5, 3, 5}
        };
        int minEffort = pathWithMinEffort(heights);
        System.out.println("PathWithMinimumEffort--->" + minEffort);
    }

    private static int pathWithMinEffort(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        int[][] efforts = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(efforts[i], (int) 1e9);
        }
        efforts[0][0] = 0;
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        priorityQueue.add(new int[]{0, 0, 0}); // currEffort , row, col

        while (!priorityQueue.isEmpty()) {
            int[] curr = priorityQueue.poll();
            int currEffort = curr[0];
            int r = curr[1];
            int c = curr[2];

            if (currEffort > efforts[r][c]) continue;
            if (r == m - 1 && c == n - 1) return currEffort;

            for (int[] move : dirs) {
                int newR = r + move[0];
                int newC = c + move[1];

                if (newR < 0 || newR >= m || newC < 0 || newC >= n) continue;
                int edgeWt = Math.abs(heights[r][c] - heights[newR][newC]);
                int newEffort = Math.max(currEffort, edgeWt);
                if (newEffort < efforts[newR][newC]) {
                    efforts[newR][newC] = newEffort;
                    priorityQueue.add(new int[]{newEffort, newR, newC});
                }
            }
        }
        return 0;
    }
}

