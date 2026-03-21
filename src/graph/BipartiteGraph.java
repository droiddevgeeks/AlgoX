package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem Statement: Given an adjacency list of a graph adj of V no. of vertices having 0 based index.
 * Check whether the graph is bipartite or not.
 * If we are able to colour a graph with two colours such that no adjacent nodes have the same colour, it is called a bipartite graph.
 */
public class BipartiteGraph {
    public static void main(String[] args) {
        int V = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1);
        adj.get(1).add(0);
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);
        adj.get(3).add(0);
        adj.get(0).add(3);

        System.out.println(isBipartite(V, adj)); // true
    }

    private static boolean isBipartite(int V, List<List<Integer>> adj) {
        int[] color = new int[V];
        Arrays.fill(color, -1);

        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!dsf(i, 0, color, adj))
                    return false;
            }
        }
        return true;
    }

    private static boolean dsf(int node, int currColor, int[] color, List<List<Integer>> adj) {
        color[node] = currColor;
        for (int nbr : adj.get(node)) {
            if (color[nbr] == -1) {
                // If not colored → assign opposite color
                if (!dsf(nbr, 1 - currColor, color, adj)) return false;
            } else if (color[nbr] == currColor) { // If already colored and same → conflict
                return false;
            }
        }
        return true;
    }
}
