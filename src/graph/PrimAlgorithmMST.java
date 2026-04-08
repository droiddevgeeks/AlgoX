package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Problem Statement: Given a weighted, undirected, and connected graph of V vertices and E edges.
 * The task is to find the sum of weights of the edges of the Minimum Spanning Tree.
 */
public class PrimAlgorithmMST {
    public static void main(String[] args) {
        int V = 5;
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
//        int[][] edges = {
//                {0, 1, 2},
//                {0, 2, 1},
//                {1, 2, 1},
//                {2, 3, 2},
//                {3, 4, 1},
//                {4, 2, 2}
//        };
        int[][] edges = {
                {0, 1, 2},
                {0, 3, 6},
                {1, 2, 3},
                {1, 3, 8},
                {1, 4, 5},
                {4, 2, 7}
        };

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            adj.get(u).add(new Pair(w, v, -1));
            adj.get(v).add(new Pair(w, u, -1));
        }

        int sumOfMST = findMSTViaPrimAlgorithm(V, adj);
        System.out.println("PrimAlgorithmMST--->" + sumOfMST);
    }

    /**
     * Time Complexity: O(E*logE) + O(E*logE) = O(E*logE), where E = no. of given edges.
     * The maximum size of the priority queue can be E so after at most E iterations the priority queue will be empty and the loop will end.
     * Inside the loop, there is a pop operation that will take logE time.
     * This will result in the first O(E*logE) time complexity.
     * Now, inside that loop, for every node, we need to traverse all its adjacent nodes where the number of nodes can be at most E.
     * If we find any node unvisited, we will perform a push operation and for that, we need a logE time complexity.
     * So this will result in the second O(E*logE).
     */
    private static int findMSTViaPrimAlgorithm(int v, ArrayList<ArrayList<Pair>> adj) {
        PriorityQueue<Pair> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x.wt));

        int[] visited = new int[v];
        Arrays.fill(visited, 0);

        queue.add(new Pair(0, 0, -1));
        int sum = 0;
        while (!queue.isEmpty()) {

            Pair curr = queue.poll();
            int currNode = curr.node;
            int currWt = curr.wt;
            int parent = curr.parent;

            if (visited[currNode] == 1) continue;
            visited[currNode] = 1;
            sum += currWt;

            // ✅ Print edge when node is actually picked
            if (parent != -1) {
                System.out.println(parent + " - " + currNode);
            }


            for (Pair adjPair : adj.get(currNode)) {
                int adjNode = adjPair.node;
                int adjWt = adjPair.wt;
                if (visited[adjNode] == 0) {
                    queue.add(new Pair(adjWt, adjNode, currNode));
                }
            }
        }

        return sum;
    }

    private static class Pair {
        int wt;
        int node;
        int parent;

        public Pair(int wt, int node, int parent) {
            this.wt = wt;
            this.node = node;
            this.parent = parent;
        }
    }
}
