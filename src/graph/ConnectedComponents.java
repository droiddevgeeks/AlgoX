package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Problem Statement: Given an undirected Graph consisting of V vertices numbered from 0 to V-1 and E edges.
 * The ith edge is represented by [ai,bi], denoting a edge between vertex ai and bi.
 * We say two vertices u and v belong to a same component if there is a path from u to v or v to u.
 * Find the number of connected components in the graph.
 * <p>
 * A connected component is a subgraph of a graph in which there exists a path between any two vertices,
 * and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.
 * Input: V=4, edges=[[0,1],[1,2]]
 * Output: 2
 * Explanation: Vertices {0,1,2} forms the first component and vertex 3 forms the second component.
 * Input:V = 7, edges = [[0, 1], [1, 2], [2, 3], [4, 5]]
 * Output: 3
 */
public class ConnectedComponents {
    public static void main(String[] args) {
        // Number of vertices
        //int v = 5;
        //int[][] edges = {{0, 1}, {1, 2}, {3, 4}};

        int v = 7;
        int[][] edges = {{0, 1}, {1, 2}, {2, 3}, {4, 5}};

        int countConnectComponent = findConnectComponents(v, edges);
        System.out.print("ConnectedComponents--" + countConnectComponent);
    }

    // this is BFS
    private static int findConnectComponents(int v, int[][] edges) {

        // Create Adjacency List
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }


        boolean[] visited = new boolean[v];
        int comp = 0;
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                comp++;
                bsfTraversal(i, visited, adj);
            }
        }
        return comp;
    }

    private static void bsfTraversal(int v, boolean[] visited, List<List<Integer>> adj) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int nbr : adj.get(node)) {
                if (!visited[nbr]) {
                    visited[nbr] = true;
                    queue.add(nbr);
                }
            }
        }
    }
}
