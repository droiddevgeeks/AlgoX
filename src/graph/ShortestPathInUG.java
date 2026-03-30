package graph;

import java.util.*;

/**
 * Input: n = 9, m = 10 edges = [[0,1],[0,3],[3,4],[4 ,5],[5,6],[1,2],[2,6],[6,7],[7,8],[6,8]] src = 0
 * Output: 0 1 2 1 2 3 3 4 4
 * Input: n = 8, m = 10 edges = [[1,0],[2,1],[0,3],[3,7],[3,4],[7,4],[7,6],[4,5],[4,6],[6,5]] src = 0
 * Output: 0 1 2 1 2 3 3 2
 */
public class ShortestPathInUG {
    public static void main(String[] args) {
        int N = 9, M = 10;
        int[][] edges = {
                {0, 1}, {0, 3}, {3, 4}, {4, 5}, {5, 6},
                {1, 2}, {2, 6}, {6, 7}, {7, 8}, {6, 8}
        };
        int src = 0;
        List<List<Integer>> adj = createAdjList(edges);
        int[] paths = getShortestPath(adj, N, src);
        System.out.println("ShortestPathInUG--->" + Arrays.toString(paths));
    }

    private static List<List<Integer>> createAdjList(int[][] edges) {
        int N = 9, M = 10;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        // Fill adjacency list from edge list
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        return adj;
    }

    private static int[] getShortestPath(List<List<Integer>> adj, int N, int src) {
        int[] dist = new int[N];
        Arrays.fill(dist, (int) 1e9);

        dist[src] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int nbr : adj.get(node)) {
                if (dist[node] + 1 < dist[nbr]) {
                    dist[nbr] = dist[node] + 1;
                    queue.add(nbr);
                }
            }
        }
        // Prepare result, replacing infinity with -1
        for (int i = 0; i < N; i++) {
            if (dist[i] == (int) 1e9) {
                dist[i] = -1;
            }
        }
        return dist;
    }
}
