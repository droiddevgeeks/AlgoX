package graph;

import java.util.ArrayList;

public class CycleDirectedGraph {
    public static void main(String[] args) {
        int V = 11;
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(3).add(7);
        adj.get(4).add(5);
        adj.get(5).add(6);
        adj.get(7).add(5);
        adj.get(8).add(9);
        adj.get(9).add(10);
        adj.get(10).add(8);

        boolean isCycle = isCyclic(V, adj);
        System.out.println("CycleDirectedGraph--->" + isCycle);
    }

    private static boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean[] visited = new boolean[V];
        boolean[] pathVisited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                if (dfsCycle(i, visited, pathVisited, adj)) return true;
            }
        }
        return false;
    }

    private static boolean dfsCycle(int node, boolean[] visited, boolean[] pathVisited, ArrayList<ArrayList<Integer>> adj) {
        visited[node] = true;
        pathVisited[node] = true;

        for (int nbr : adj.get(node)) {
            // Case 1: Not visited → go deeper
            if (!visited[nbr]) {
                if (dfsCycle(nbr, visited, pathVisited, adj)) {
                    return true;
                }
            }
            // Case 2: Already in current path → cycle
            else if (pathVisited[nbr]) return true;
        }
        pathVisited[node] = false;
        return false;
    }
}
