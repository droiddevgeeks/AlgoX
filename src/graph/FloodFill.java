package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * An image is represented by a 2-D array of integers, each integer representing the pixel value of the image.
 * Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor,
 * "flood fill" the image.
 * Input: image = [ [1, 1, 1], [1, 1, 0], [1, 0, 1] ], sr = 1, sc = 1, newColor = 2
 * Output: [ [2, 2, 2], [2, 2, 0], [2, 0, 1] ]
 */
public class FloodFill {
    final static int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        int[][] image = new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int sr = 1, sc = 1;
        int newColor = 2;

        floodFill(image, sc, sc, newColor);
        for (int[] row : image) {
            System.out.println(Arrays.toString(row));
        }

        /**
         * Input: image = [ [0, 1, 0], [1, 1, 0], [0, 0, 1] ], sr = 2, sc = 2, newColor = 3
         * Output: [ [0, 1, 0], [1, 1, 0], [0, 0, 3] ]
         */
        int[][] image2 = new int[][]{{0, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        bfs(image2, 2, 2, 3);
        for (int[] row : image2) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int prevColor = image[sr][sc];
        if (prevColor == newColor) return image;
        dfs(image, sr, sc, prevColor, newColor);
        return image;
    }

    private static void dfs(int[][] image, int r, int c, int oldColor, int newColor) {
        int m = image.length;
        int n = image[0].length;

        if (r < 0 || c < 0 || r >= m || c >= n) return;
        if (image[r][c] != oldColor) return;

        image[r][c] = newColor;

        for (int[] dir : dirs) {
            dfs(image, r + dir[0], c + dir[1], oldColor, newColor);
        }
    }


    private static int[][] bfs(int[][] image, int sr, int sc, int newColor) {
        int m = image.length;
        int n = image[0].length;
        int prevColor = image[sr][sc];
        if (prevColor == newColor) return image;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sr, sc});
        image[sr][sc] = newColor;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr >= 0 && nc >= 0 && nr < m && nc < n && image[nr][nc] == prevColor) {
                    image[nr][nc] = newColor;
                    queue.add(new int[]{nr, nc});
                }
            }
        }
        return image;
    }
}
