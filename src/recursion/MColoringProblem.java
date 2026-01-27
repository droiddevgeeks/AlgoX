package recursion;

import java.util.Arrays;

public class MColoringProblem {
    public static void main(String[] args) {
        //int N = 4, M = 3, E = 5;
        int N = 3, M = 2, E = 3;
        //int[][] Edges = {{0, 1},{1, 2},{2, 3},{3, 0}, {0, 2}};
        int[][] Edges = {{0, 1}, {1, 2}, {0, 2}};

        int[][] graph = new int[N][N];
        int[] color = new int[N];
        Arrays.fill(color, 0);

        for (int[] edge : Edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u][v] = 1;
            graph[v][u] = 1;
        }

        System.out.println("Graph---");
        for (int[] temp : graph) {
            System.out.println(Arrays.toString(temp));
        }
        boolean isColoringPossible = solveColoring(0, graph, color, M);
        System.out.println(isColoringPossible);
    }

    private static boolean solveColoring(int vertex, int[][] graph, int[] color, int colorLimit) {
        if (vertex == graph.length) return true;

        for (int i = 1; i <= colorLimit; i++) {
            if (isSafeToColor(graph, vertex, i, color)) {
                color[vertex] = i;
                if (solveColoring(vertex + 1, graph, color, colorLimit)) return true;
                color[vertex] = 0;
            }
        }
        return false;
    }

    private static boolean isSafeToColor(int[][] graph, int vertex, int colorValue, int[] color) {
        for (int i = 0; i < graph.length; i++) {
            // vertex is connected with ith node & ith node color is same as vertex color
            if (graph[vertex][i] == 1 && color[i] == colorValue) return false;
        }
        return true;
    }

}
