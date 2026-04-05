package graph;


import java.util.Arrays;

/**
 * You need to find out a city with the smallest number of cities that are reachable through some path and whose distance is at most Threshold Distance,
 * If there are multiple such cities, our answer will be the city with the greatest number.
 */
public class CitySmallestNumberNeighboursUnderThreshold {
    public static void main(String[] args) {
        int N = 4, M = 4;
        int[][] edges = new int[][]{{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
        int distanceThreshold = 4;

        int city = findCitySmallestNumberNeighboursUnderThreshold(edges, distanceThreshold, N);
        System.out.println("CitySmallestNumberNeighboursUnderThreshold-->" + city);
    }

    private static int findCitySmallestNumberNeighboursUnderThreshold(int[][] edges, int threshold, int n) {
        int[][] dist = new int[n][n];
        int INF = (int) 1e9;
        for (int[] temp : dist) {
            Arrays.fill(temp, INF);
        }
        for (int[] edge : edges) {
            dist[edge[0]][edge[1]] = edge[2];
            dist[edge[1]][edge[0]] = edge[2];
        }

        for (int i = 0; i < n; i++) {
            dist[i][i] = 0;
        }

        for (int via = 0; via < n; via++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][via] != INF && dist[via][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][via] + dist[via][j]);
                    }
                }
            }
        }

        int cityCount = n;
        int cityNo = -1;
        // Check each city and count the number of cities within the threshold distance
        for (int city = 0; city < n; city++) {
            int count = 0;
            for (int adjCity = 0; adjCity < n; adjCity++) {
                if (dist[city][adjCity] <= threshold) count++;
            }
            if (count <= cityCount) {
                cityCount = count;
                cityNo = city;
            }
        }
        return cityNo;
    }
}
