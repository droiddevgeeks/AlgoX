package graph;

import java.util.*;

public class DijkstraAlgo {
    public static void main(String[] args) {
        // Number of vertices and edges
        int V = 3, S = 2;

        // Create adjacency list to represent the graph
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add edges to the graph
        adj.get(0).add(new Pair(1, 1));
        adj.get(0).add(new Pair(6, 2));
        adj.get(1).add(new Pair(3, 2));
        adj.get(1).add(new Pair(1, 0));
        adj.get(2).add(new Pair(3, 1));
        adj.get(2).add(new Pair(6, 0));

        int[] minDistance = dijkstraUsingPQ(V, S, adj);
        System.out.println("DijkstraAlgoPQ--->" + Arrays.toString(minDistance));

        minDistance = dijkstraUsingSet(V, S, adj);
        System.out.println("DijkstraAlgo Using Set--->" + Arrays.toString(minDistance));
    }

    /**
     * Time: O(N × M)
     * Space: O(N × M)
     */
    private static int[] dijkstraUsingPQ(int v, int src, List<List<Pair>> adj) {
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.dist));

        int[] dist = new int[v];
        Arrays.fill(dist, (int) 1e9);

        dist[src] = 0;
        priorityQueue.add(new Pair(0, src));

        while (!priorityQueue.isEmpty()) {
            Pair curr = priorityQueue.poll();
            int currDist = curr.dist;
            int currNode = curr.node;
            // Skip outdated entries
            if (currDist > dist[currNode]) continue;

            for (Pair nbr : adj.get(currNode)) {
                int nbrNode = nbr.node;
                int nbrDist = nbr.dist;
                if (currDist + nbrDist < dist[nbrNode]) {
                    dist[nbrNode] = currDist + nbrDist;
                    priorityQueue.add(new Pair(dist[nbrNode], nbrNode));
                }
            }
        }
        return dist;
    }

    private static int[] dijkstraUsingSet(int v, int src, List<List<Pair>> adj) {
        Set<Pair> set = new TreeSet<>((a, b) -> {
            if (a.dist == b.dist) return a.node - b.node;
            else return a.dist - b.dist;
        });

        int[] dist = new int[v];
        Arrays.fill(dist, (int) 1e9);

        dist[src] = 0;
        set.add(new Pair(0, src));

        while (!set.isEmpty()) {
            Pair curr = set.stream().findFirst().get();
            set.remove(curr);
            int currDist = curr.dist;
            int currNode = curr.node;

            for (Pair nbr : adj.get(currNode)) {
                int nbrNode = nbr.node;
                int nbrDist = nbr.dist;
                if (currDist + nbrDist < dist[nbrNode]) {

                    if (dist[nbrNode] != (int) 1e9) {
                        set.remove(new Pair(dist[nbrNode], nbrNode));
                    }
                    dist[nbrNode] = currDist + nbrDist;
                    set.add(new Pair(dist[nbrNode], nbrNode));
                }
            }
        }
        return dist;
    }

    private static class Pair {
        int dist;
        int node;

        public Pair(int dist, int node) {
            this.dist = dist;
            this.node = node;
        }
    }
}
