package graph;

/**
 * Given a weighted, directed and connected graph
 * Find the shortest distance of all the vertices from the source vertex S.
 * If the Graph contains a negative cycle then return an array consisting of only -1.
 * V = 6, E = [[3, 2, 6], [5, 3, 1], [0, 1, 5], [1, 5, -3], [1, 2, -2], [3, 4, -2], [2, 4, 3]], S = 0
 * Result: 0 5 3 3 1 2
 */

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Why Bellman-Ford? It works with negative edges and can detect negative cycles.
 * For undirected graphs, treat each edge as two directed edges.
 */
public class BellmanFord {
    public static void main(String[] args) {
        int V = 6;
        int S = 0;
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>() {
            {
                add(new ArrayList<>(Arrays.asList(3, 2, 6)));
                add(new ArrayList<>(Arrays.asList(5, 3, 1)));
                add(new ArrayList<>(Arrays.asList(0, 1, 5)));
                add(new ArrayList<>(Arrays.asList(1, 5, -3)));
                add(new ArrayList<>(Arrays.asList(1, 2, -2)));
                add(new ArrayList<>(Arrays.asList(3, 4, -2)));
                add(new ArrayList<>(Arrays.asList(2, 4, 3)));
            }
        };

        int[] dist = getShortestDistanceFromSource(edges, V, S);
        System.out.println("BellmanFord==>" + Arrays.toString(dist));
    }

    private static int[] getShortestDistanceFromSource(ArrayList<ArrayList<Integer>> edges, int nodes, int src) {
        int[] dist = new int[nodes];
        Arrays.fill(dist, (int) 1e8);
        dist[src] = 0;

        for (int i = 0; i < nodes - 1; i++) {
            for (ArrayList<Integer> edge : edges) {
                int u = edge.get(0);
                int v = edge.get(1);
                int wt = edge.get(2);
                if (dist[u] != 1e8 && dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                }
            }
        }

        // Nth relaxation to check negative cycle
        for (ArrayList<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);
            int wt = edge.get(2);
            if (dist[u] != 1e8 && dist[u] + wt < dist[v]) {
                int temp[] = new int[1];
                temp[0] = -1;
                return temp;
            }
        }
        return dist;
    }
}
