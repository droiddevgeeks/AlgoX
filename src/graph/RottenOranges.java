package graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem Statement: Given an n x m grid, where each cell has the following values :
 * 2 - represents a rotten orange , 1 - represents a Fresh orange , 0 - represents an Empty Cell .
 * <p>
 * Every minute, if a fresh orange is adjacent to a rotten orange in 4-direction ( upward, downwards, right, and left ) it becomes rotten.
 * Return the minimum number of minutes required such that none of the cells has a Fresh Orange. If it's not possible, return -1..
 * Input :grid = [ [2,1,1] , [1,1,0] , [0,1,1] ]
 * Output :4
 */
public class RottenOranges {
    public static void main(String[] args) {
        int[][] grid = new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        // int[][] grid = new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        int minTime = calculateMinTimeToRodOranges(grid);
        System.out.print("RottenOranges-->" + minTime);
    }

    /**
     * Time : O(m × n) Every cell processed once.
     * space: O(m × n) : Queue may hold all cells in worst case.
     */
    private static int calculateMinTimeToRodOranges(int[][] grid) {
        int fresh = 0;
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
                if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        if (fresh == 0) return 0;
        int minute = 0;

        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean affected = false;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int r = cur[0];
                int c = cur[1];

                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr >= 0 && nc >= 0 && nr < m && nc < n && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        queue.add(new int[]{nr, nc});
                        fresh--;
                        affected = true;
                    }
                }
            }
            if (affected) minute++;
        }
        return fresh == 0 ? minute : -1;
    }
}
