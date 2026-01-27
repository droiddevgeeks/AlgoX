package recursion;

import java.util.ArrayList;
import java.util.List;

public class RatMaze {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 1, 1, 1}
        };

//        int[][] grid = new int[][]{
//                {1, 0},
//                {1, 0}
//        };
        List<String> paths = new ArrayList<>();
        findRateMazePaths(grid, 0, 0, new StringBuilder(), paths);
        System.out.println("RatMaze");
        for (String path : paths) {
            System.out.println(path);
        }
    }

    private static void findRateMazePaths(int[][] grid, int row, int col, StringBuilder currDirection, List<String> paths) {
        if (row < 0 || row >= grid.length || col < 0 || col > grid[0].length || grid[row][col] == 0) {
            return;
        }

        if (row == grid.length - 1 && col == grid[0].length - 1) {
            paths.add(currDirection.toString());
            return;
        }

        grid[row][col] = 0;

        currDirection.append("D");
        findRateMazePaths(grid, row + 1, col, currDirection, paths);
        currDirection.deleteCharAt(currDirection.length() - 1);

        currDirection.append("R");
        findRateMazePaths(grid, row, col + 1, currDirection, paths);
        currDirection.deleteCharAt(currDirection.length() - 1);

        currDirection.append("U");
        findRateMazePaths(grid, row - 1, col, currDirection, paths);
        currDirection.deleteCharAt(currDirection.length() - 1);

        currDirection.append("L");
        findRateMazePaths(grid, row, col - 1, currDirection, paths);
        currDirection.deleteCharAt(currDirection.length() - 1);

        grid[row][col] = 1;
    }
}
