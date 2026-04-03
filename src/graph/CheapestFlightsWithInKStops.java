package graph;

import java.util.*;

public class CheapestFlightsWithInKStops {
    public static void main(String[] args) {
        // Driver Code
        int n = 4, src = 0, dst = 3, K = 1;

        // Flight routes and their costs
        int[][] flights = {
                {0, 1, 100},
                {1, 2, 100},
                {2, 0, 100},
                {1, 3, 600},
                {2, 3, 200}
        };

        int cheapestFlight = cheapestFLight(flights, src, dst, K);
        System.out.println("CheapestFlightsWithInKStops-->"+ cheapestFlight);
    }

    private static int cheapestFLight(int[][] flights, int src, int dest, int totalStops) {
        List<List<Pair>> adj = new ArrayList<>();
        int m = flights.length;
        int n = flights[0].length;
        for (int i = 0; i < m; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] flight : flights) {
            adj.get(flight[0]).add(new Pair(flight[1], flight[2]));
        }

        Queue<Tuple> queue = new LinkedList<>();

        int[] cost = new int[m];
        Arrays.fill(cost, (int) 1e9);
        cost[src] = 0;

        queue.add(new Tuple(0, src, 0));

        while (!queue.isEmpty()) {
            Tuple curr = queue.poll();
            int currStops = curr.stops;
            int currNode = curr.node;
            int currCost = curr.cost;

            // If the number of stops exceeds K, continue to the next iteration
            if (currStops > totalStops) continue;

            for (Pair nbr : adj.get(currNode)) {
                int nbrNode = nbr.node;
                int nbrCost = nbr.cost;
                if (currCost + nbrCost < cost[nbrNode] && currStops <= totalStops) {
                    cost[nbrNode] = currCost + nbrCost;
                    queue.add(new Tuple(currStops + 1, nbrNode, cost[nbrNode]));
                }
            }
        }

        if (cost[dest] == (int) 1e9) return -1;
        return cost[dest];
    }

    private static class Pair {
        int node;
        int cost;

        public Pair(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

    private static class Tuple {
        int stops;
        int node;
        int cost;


        public Tuple(int stops, int node, int cost) {
            this.stops = stops;
            this.node = node;
            this.cost = cost;
        }
    }
}
