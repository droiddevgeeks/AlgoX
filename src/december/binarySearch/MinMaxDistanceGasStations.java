package december.binarySearch;

import java.util.Arrays;

/**
 * You are given a sorted array ‘arr’ of length ‘n’, which contains positive integer positions of ‘n’ gas stations on the X-axis.
 * You are also given an integer ‘k’. You have to place 'k' new gas stations on the X-axis.
 * You can place them anywhere on the non-negative side of the X-axis, even on non-integer positions.
 * Let 'dist' be the maximum value of the distance between adjacent gas stations after adding k new gas stations.
 * Find the minimum value of ‘dist’.
 * Example 1: Input Format: N = 5, arr[] = {1,2,3,4,5}, k = 4 Result: 0.5
 * Explanation: One of the possible ways to place 4 gas stations is {1,1.5,2,2.5,3,3.5,4,4.5,5}.
 * Thus the maximum difference between adjacent gas stations is 0.5. Hence, the value of ‘dist’ is 0.5. It can be shown that there is no possible way to add 4 gas stations in such a way that the value of ‘dist’ is lower than this.
 * Example 2: Input Format: N = 10, arr[] = {1,2,3,4,5,6,7,8,9,10}, k = 1
 * Result: 1 Explanation: One of the possible ways to place 1 gas station is {1,1.5,2,3,4,5,6,7,8,9,10}.
 * Thus the maximum difference between adjacent gas stations is still 1. Hence, the value of ‘dist’ is 1.
 * It can be shown that there is no possible way to add 1 gas station in such a way that the value of ‘dist’ is lower than this.
 */
public class MinMaxDistanceGasStations {
    public static void main(String[] args) {
        int[] stations = new int[]{1, 2, 3, 4, 5};
        //int[] stations = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k = 4;

        System.out.println("Stations---" + Arrays.toString(stations));
        double minimizeMaxDistanceGasStations = MinimizeMaxDistanceGasStations(stations, k);
        System.out.println("MinMaxDistanceGasStations--" + minimizeMaxDistanceGasStations);


        double minimizeMaxDistanceGasStationsOptimal = MinimizeMaxDistanceGasStationsOptimal(stations, k);
        System.out.println("MinMaxDistanceGasStationsOptimal--" + minimizeMaxDistanceGasStationsOptimal);
    }

    private static double MinimizeMaxDistanceGasStations(int[] stations, int k) {
        int maxGap = 0;
        for (int i = 0; i < stations.length - 1; i++) {
            maxGap = Math.max(maxGap, stations[i + 1] - stations[i]);
        }
        double ans = maxGap;
        for (double dist = 0.1; dist <= maxGap; dist = dist + 0.1) {
            if (canFitStation(stations, dist) <= k) {
                ans = dist;
                break;
            }
        }
        return ans;
    }

    private static double MinimizeMaxDistanceGasStationsOptimal(int[] stations, int k) {
        double maxGap = 0;
        for (int i = 0; i < stations.length - 1; i++) {
            maxGap = Math.max(maxGap, stations[i + 1] - stations[i]);
        }
        double ans = maxGap;
        double eps = 1e-6;
        double low = 0;

        while (maxGap - low > eps) {
            double mid = (low + maxGap) / 2;
            if (canFitStation(stations, mid) <= k) {
                ans = mid;
                maxGap = mid;
            } else {
                low = mid;
            }
        }

        return ans;
    }

    private static int canFitStation(int[] stations, double mid) {
        int stationReq = 0;
        for (int j = 0; j < stations.length - 1; j++) {
            int gap = stations[j + 1] - stations[j];
            stationReq += (int) (Math.ceil(gap / mid) - 1);
        }

        return stationReq;
    }
}
