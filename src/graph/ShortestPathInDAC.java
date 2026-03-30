package graph;

import java.util.*;

/**
 * Input: N = 4, M = 2 edge = [[0,1,2],[0,2,1]]
 * Output: 0 2 1 -1
 * Input: N = 6, M = 7 edge = [[0,1,2],[0,4,1],[4,5,4],[4,2,2],[1,2,3],[2,3,6],[5,3,1]]
 * Output: 0 2 3 6 1 5
 */
public class ShortestPathInDAC {
    public static void main(String[] args) {
        // Number of nodes and edges
        int N = 6;

        // Edge list with weights
        int[][] edges = {
                {0, 1, 2}, {0, 4, 1}, {4, 5, 4},
                {4, 2, 2}, {1, 2, 3}, {2, 3, 6}, {5, 3, 1}
        };

        List<List<Pair>> adj = createAdjList(edges);
        int[] paths = getShortestPathDAC(adj, N);
        System.out.println("ShortestPathInUG--->" + Arrays.toString(paths));
    }

    private static List<List<Pair>> createAdjList(int[][] edges) {
        int N = 6;
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        // Fill adjacency list from edge list
        for (int[] edge : edges) {
            adj.get(edge[0]).add(new Pair(edge[1], edge[2]));
        }
        return adj;
    }

    private static int[] getShortestPathDAC(List<List<Pair>> adj, int N) {
        boolean[] visited = new boolean[N];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            if (!visited[i]) topoSort(adj, N, visited, stack);
        }

        int[] dist = new int[N];
        Arrays.fill(dist, (int) 1e9);
        dist[0] = 0;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            // If the node is reachable
            if (dist[node] != (int) 1e9) {
                for (Pair nbrPair : adj.get(node)) {
                    int nbr = nbrPair.nbr;
                    int wt = nbrPair.wt;
                    if (dist[node] + wt < dist[nbr]) {
                        dist[nbr] = dist[node] + wt;
                    }
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

    private static void topoSort(List<List<Pair>> adj, int node, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (Pair currPair : adj.get(node)) {
            int nbrNode = currPair.nbr;
            if (!visited[nbrNode]) {
                topoSort(adj, nbrNode, visited, stack);
            }
        }
        stack.push(node);
    }

    private static class Pair {
        int nbr;
        int wt;

        public Pair(int nbr, int wt) {
            this.nbr = nbr;
            this.wt = wt;
        }
    }
}
