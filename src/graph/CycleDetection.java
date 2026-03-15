package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * 0 ----- 1
 * |       |
 * |       |
 * 3 ----- 2
 */

public class CycleDetection {
    public static void main(String[] args) {
        //visited neighbour AND neighbour != parent → cycle

        int V = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);

        adj.get(3).add(1);
        adj.get(1).add(3);

        boolean haveCycle = isCycleDfs(V, adj);
        System.out.print("CycleDetection DFS...===>" + haveCycle);

        haveCycle = isCycleBfs(V, adj);
        System.out.print("CycleDetection BFs...===>" + haveCycle);
    }

    private static boolean isCycleDfs(int v, List<List<Integer>> adj) {
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                if (dsfCycle(i, -1, visited, adj)) return true;
            }
        }
        return false;
    }

    private static boolean dsfCycle(int node, int parent, boolean[] visited, List<List<Integer>> adj) {
        visited[node] = true;
        for (int nbr : adj.get(node)) {
            if (!visited[nbr]) {
                if (dsfCycle(nbr, node, visited, adj)) return true;
            } else if (nbr != parent) {
                return true;
            }
        }
        return false;
    }


    private static boolean isCycleBfs(int v, List<List<Integer>> adj) {
        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                if (bfsCycleDetection(i, visited, adj)) return true;
            }
        }
        return false;
    }

    private static boolean bfsCycleDetection(int start, boolean[] visited, List<List<Integer>> adj) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start, -1});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int parent = curr[1];

            for (int nbr : adj.get(node)) {
                if (!visited[nbr]) {
                    visited[nbr] = true;
                    queue.add(new int[]{nbr, nbr});
                } else if (nbr != parent) {
                    return true;
                }
            }
        }
        return false;
    }
}
