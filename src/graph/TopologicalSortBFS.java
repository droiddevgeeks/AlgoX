package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSortBFS {
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

        List<Integer> topologicalSort = topologicalSortBFS(V, adj);
        System.out.println("TopologicalSortBFS--->" + topologicalSort);
    }

    /**
     * Time Complexity: O(V+E), we traverse all the edges and visit all the vertices once.
     * Space Complexity: O(V+E), additonal space is used to store adjacency list, in-degree array and vertices in queue.
     */
    private static List<Integer> topologicalSortBFS(int V, List<List<Integer>> adj) {
        int inDegree[] = new int[V];
        // Step 1: compute inDegree
        for (int i = 0; i < V; i++) {
            for (int node : adj.get(i)) {
                inDegree[node]++;
            }
        }
        // Step 2: queue for nodes with inDegree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }

        List<Integer> topo = new ArrayList<>();

        // Step 3: BFS
        while (!queue.isEmpty()) {
            int node = queue.poll();
            topo.add(node);

            for (int nbr : adj.get(node)) {
                inDegree[nbr]--;
                if (inDegree[nbr] == 0) queue.add(nbr);
            }
        }
        return topo;

    }
}

/**
 * 1. Course Schedule I and II exactly same as this. Just building is different.
 * // Build graph
 *     for(int[] p : prerequisites) {
 *         int course = p[0];
 *         int prereq = p[1];
 *
 *         graph.get(prereq).add(course);
 *         indegree[course]++;
 *     }
 */
