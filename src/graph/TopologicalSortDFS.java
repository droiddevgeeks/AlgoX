package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a Directed Acyclic Graph (DAG) with V vertices labeled from 0 to V-1.
 * The graph is represented using an adjacency list where adj[i] lists all nodes connected to node.
 * Find any Topological Sorting of that Graph.
 * In topological sorting, node u will always appear before node v if there is a directed edge from node u towards node v(u -> v).
 * 1. Use Stack to store nodes.
 */
public class TopologicalSortDFS {
    public static void main(String[] args) {
        int V = 6;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(5).add(0);
        adj.get(5).add(2);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(2).add(3);
        adj.get(3).add(1);

        List<Integer> topologicalSort = topologicalSort(V, adj);
        System.out.println("TopologicalSortDFS--->" + topologicalSort);
    }

    private static List<Integer> topologicalSort(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, visited, stack, adj);
            }
        }
        List<Integer> sortedData = new ArrayList<>();
        while (!stack.isEmpty()) {
            sortedData.add(stack.pop());
        }
        return sortedData;
    }

    private static void dfs(int node, boolean[] visited, Stack<Integer> stack, List<List<Integer>> adj) {
        visited[node] = true;
        for (int nbr : adj.get(node)) {
            if (!visited[nbr]) {
                dfs(nbr, visited, stack, adj);
            }
        }
        stack.add(node);
    }
}
