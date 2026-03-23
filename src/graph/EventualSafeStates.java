package graph;

import java.util.*;

/**
 * Problem Statement: A directed graph of V vertices and E edges is given in the form of an adjacency list adj.
 * A node is a terminal node if there are no outgoing edges.
 * A node is a safe node if every possible path starting from that node leads to a terminal node.
 * Safe nodes are: Terminal nodes (outdegree = 0) , Nodes that eventually lead to terminal nodes
 */

/**
 * Nodes that are NOT part of a cycle AND do not lead to a cycle
 * Trick: Reverse the Graph
 * Because BFS (Topological Sort) works on indegree, but the problem is about outgoing edges.
 * Reverse all edges
 * Track indegree in reversed graph = original outdegree
 */
public class EventualSafeStates {
    public static void main(String[] args) {
        // Adjacency list representation of the graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(3);
        adj.get(2).add(4);
        adj.get(3).add(4);
        adj.get(4).add(5);
        adj.get(5).add(6);
        adj.get(6).add(7);
        adj.get(8).add(1);
        adj.get(8).add(9);
        adj.get(9).add(10);
        adj.get(10).add(8);
        adj.get(11).add(9);

        int V = 12;

        List<Integer> safeNodes = findEventualSafeStates(V, adj);
        System.out.println("EventualSafeStates-->" + safeNodes);
    }

    private static List<Integer> findEventualSafeStates(int V, List<List<Integer>> adj) {
        List<List<Integer>> revAdj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            revAdj.add(new ArrayList<>());
        }

        int[] inDegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int nbr : adj.get(i)) {
                revAdj.get(nbr).add(i);
                inDegree[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if(inDegree[i] == 0) queue.add(i);
        }

        List<Integer> safeNodes = new ArrayList<>();
        while (!queue.isEmpty()){
            int node = queue.poll();
            safeNodes.add(node);
            for (int nbr : revAdj.get(node)) {
                inDegree[nbr]--;
                if (inDegree[nbr] == 0) queue.add(nbr);
            }
        }
        Collections.reverse(safeNodes);
        return  safeNodes;
    }
}
