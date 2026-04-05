package graph;

import java.util.Arrays;

/**
 * Find the shortest distances between every pair of vertices in a given edge-weighted directed graph
 * Matrix[i][j] denotes the weight of the edge from i to j. If matrix[i][j]=-1, it means there is no edge from i to j.
 */

/**
 * If any node can reach itself with negative cost, then a negative cycle exists.
 */
public class FloydWarshall {
    public static void main(String[] args) {
        int[][] matrix = {
                {0, 2, -1, -1},
                {1, 0, 3, -1},
                {-1, -1, 0, -1},
                {3, 5, 4, 0}
        };

        System.out.println("Before");
        for (int[] temp : matrix) {
            System.out.println(Arrays.toString(temp));
        }

        shortestDistance(matrix);
        System.out.println("After");
        for (int[] temp : matrix) {
            System.out.println(Arrays.toString(temp));
        }
    }

    private static void shortestDistance(int[][] matrix) {
        int n = matrix.length;
        int INF = (int) 1e9;

        // Step 1: Convert -1 to INF
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = INF;
                }
            }
        }

        for (int via = 0; via < n; via++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][via] != INF && matrix[via][j] != INF) {
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][via] + matrix[via][j]);
                    }
                }
            }
        }

        // 🔴 Step 3: Detect negative cycle
        for (int i = 0; i < n; i++) {
            if (matrix[i][i] < 0) {
                System.out.println("Negative Cycle exist");
            }
        }

        // Step 3: Convert INF back to -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == INF) {
                    matrix[i][j] = -1;
                }
            }
        }
    }
}
