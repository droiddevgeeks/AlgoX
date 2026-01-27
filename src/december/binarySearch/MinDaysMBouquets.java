package december.binarySearch;

import java.util.Arrays;

/**
 * You are given 'N’ roses and you are also given an array 'arr'
 * where 'arr[i]' denotes that the 'ith' rose will bloom on the 'arr[i]th' day.
 * You can only pick already bloomed roses that are adjacent to make a bouquet.
 * You are also told that you require exactly 'k' adjacent bloomed roses to make a single bouquet.
 * Find the minimum number of days required to make at least ‘m' bouquets each containing 'k' roses. Return -1 if it is not possible.
 * Example 1:
 * Input Format: N = 8, arr[] = {7, 7, 7, 7, 13, 11, 12, 7}, m = 2, k = 3
 * Result: 12
 * Explanation: On the 12th the first 4 flowers and the last 3 flowers would have already bloomed.
 * So, we can easily make 2 bouquets, one with the first 3 and another with the last 3 flowers.
 * <p>
 * Example 2:
 * Input Format: N = 5, arr[] = {1, 10, 3, 10, 2}, m = 3, k = 2
 * Result: -1
 * Explanation: If we want to make 3 bouquets of 2 flowers each, we need at least 6 flowers.
 * But we are given only 5 flowers, so, we cannot make the bouquets.
 */
public class MinDaysMBouquets {
    public static void main(String[] args) {
        //int[] roses = new int[]{7, 7, 7, 7, 13, 11, 12, 7};
        int[] roses = new int[]{1, 10, 3, 10, 2};
        int m = 2;
        int k = 3;

        System.out.println("Roses---" + Arrays.toString(roses));
        int res = findMinDaysToFormMBouquetsBruteForce(roses, m, k);
        System.out.println("MinDaysMBouquets BruteForce===>" + res);

        int resOptimal = findMinDaysToFormMBouquetsOptimal(roses, m, k);
        System.out.println("MinDaysMBouquets Optimal===>" + resOptimal);
    }

    private static int findMinDaysToFormMBouquetsOptimal(int[] roses, int m, int k) {
        if (roses.length < m * k) return -1;
        int minDays = Integer.MAX_VALUE;
        int maxDays = Integer.MIN_VALUE;
        int candidateAns = 0;

        for (int temp : roses) {
            minDays = Math.min(minDays, temp);
            maxDays = Math.max(maxDays, temp);
        }
        int low = minDays, high = maxDays, mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (isPossibleToForm(roses, mid, m, k)) {
                candidateAns = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return candidateAns;
    }

    private static int findMinDaysToFormMBouquetsBruteForce(int[] roses, int m, int k) {
        if (roses.length < m * k) return -1;
        int[] temp = Arrays.copyOf(roses, roses.length);
        Arrays.sort(temp);
        int minDays = temp[0];
        int maxDays = temp[temp.length - 1];

        for (int currDay = minDays; currDay < maxDays; currDay++) {
            if (isPossibleToForm(roses, currDay, m, k)) {
                return currDay;
            }
        }
        return -1;
    }

    private static boolean isPossibleToForm(int[] roses, int currDay, int m, int k) {
        int totalForm = 0;
        int currAdjacent = 0;
        for (int temp : roses) {
            if (temp <= currDay) {
                currAdjacent++;
                if (currAdjacent == k) {
                    totalForm++;
                    currAdjacent = 0;
                }
            } else {
                currAdjacent = 0;
            }
        }
        return totalForm >= m;
    }
}
