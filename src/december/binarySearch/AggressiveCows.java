package december.binarySearch;

import java.util.Arrays;

/**
 * Problem Statement: You are given an array 'arr' of size 'n' which denotes the position of stalls.
 * You are also given an integer 'k' which denotes the number of aggressive cows.
 * You are given the task of assigning stalls to 'k' cows such that
 * the minimum distance between any two of them is the maximum possible.
 * Find the maximum possible minimum distance.
 * Example 1:
 * Input Format:
 * N = 6, k = 4, arr[] = {0,3,4,7,10,9}
 * Result:
 * 3
 * Explanation:
 * The maximum possible minimum distance between any two cows will be 3 when 4 cows are placed at positions {0, 3, 7, 10}. Here the distances between cows are 3, 4, and 3 respectively. We cannot make the minimum distance greater than 3 in any ways.
 * <p>
 * Example 2:
 * Input Format:
 * N = 5, k = 2, arr[] = {4,2,1,3,6}
 * Result:
 * 5
 * Explanation:
 * The maximum possible minimum distance between any two cows will be 5 when 2 cows are placed at positions {1, 6}.
 */
public class AggressiveCows {
    public static void main(String[] args) {
        //int[] stalls = new int[]{0, 3, 4, 7, 10, 9};
        int[] stalls = new int[]{4, 2, 1, 3, 6};
        int k = 2;
        System.out.println("Stalls are ---" + Arrays.toString(stalls));
        int maxPossibleMinDistance = findMaxPossibleMinDistance(stalls, k);
        System.out.println("maxPossibleMinDistance BruteForce---" + maxPossibleMinDistance);

        int maxPossibleMinDistanceOptimal = findMaxPossibleMinDistanceOptimal(stalls, k);
        System.out.println("maxPossibleMinDistance Optimal---" + maxPossibleMinDistanceOptimal);
    }

    private static int findMaxPossibleMinDistanceOptimal(int[] stalls, int k) {
        int ans = 0;
        Arrays.sort(stalls);
        int low = 1, high = stalls[stalls.length - 1] - stalls[0];
        while (low <= high) {
            int mid = (low + high) / 2;
            if (canSetCows(stalls, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private static int findMaxPossibleMinDistance(int[] stalls, int k) {
        int ans = 0;
        Arrays.sort(stalls);
        int maxDistance = stalls[stalls.length - 1] - stalls[0];
        for (int distance = 1; distance <= maxDistance; distance++) {
            if (canSetCows(stalls, k, distance)) {
                ans = distance;
            }
        }
        return ans;
    }

    private static boolean canSetCows(int[] stalls, int k, int distance) {
        int count = 1;
        int lastPos = stalls[0];

        for (int stall : stalls) {
            if (stall - lastPos >= distance) {
                count++;
                lastPos = stall;
            }
            if (count >= k) return true;
        }
        return false;
    }
}
