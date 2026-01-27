package december.binarySearch;

import java.util.Arrays;

/**
 * A monkey Koko is given ‘n’ piles of bananas, whereas the 'ith' pile has ‘a[i]’ bananas.
 * An integer ‘h’ is also given, which denotes the time (in hours) for all the bananas to be eaten.
 * <p>
 * Each hour, the monkey chooses a non-empty pile of bananas and eats ‘k’ bananas.
 * If the pile contains less than ‘k’ bananas, then the monkey consumes all the bananas and won’t eat any more bananas in that hour.
 * <p>
 * Find the minimum number of bananas ‘k’ to eat per hour so that the monkey can eat all the bananas within ‘h’ hours.
 * <p>
 * Input: N = 4, a[] = {7, 15, 6, 3}, h = 8
 * Output: 5
 * Explanation:  If Koko eats 5 bananas/hr, he will take 2, 3, 2, and 1 hour to eat the piles accordingly. So, he will take 8 hours to complete all the piles.
 * Input: N = 5, a[] = {25, 12, 8, 14, 19}, h = 5
 * Output: 25
 * Explanation: If Koko eats 25 bananas/hr, he will take 1, 1, 1, 1, and 1 hour to eat the piles accordingly.
 */
public class KokoEatingBananas {
    public static void main(String[] args) {
        int[] piles = {7, 15, 6, 3};
        int hour = 8;
        System.out.println("KokoEatingBananas--" + Arrays.toString(piles));
        int minBananaPerHour = findMinBananaPerHourBruteForce(piles, hour);
        System.out.println("minBananaPerHour BruteForce--" + minBananaPerHour);

        int minBananaPerHourOptimal = findMinBananaPerHourOptimal(piles, hour);
        System.out.println("minBananaPerHour Optimal--" + minBananaPerHourOptimal);
    }

    private static int findMinBananaPerHourBruteForce(int[] piles, int hour) {
        Arrays.sort(piles);
        int maxRate = piles[piles.length - 1];
        int minRate = 1;
        for (int i = minRate; i < maxRate; i++) {
            int h = 0;
            for (int pile : piles) {
                h += (int) Math.ceil((double) pile / i);
            }
            if (h <= hour) return i;
        }
        return maxRate;
    }

    private static int findMinBananaPerHourOptimal(int[] piles, int hour) {
        Arrays.sort(piles);
        int maxRate = piles[piles.length - 1];
        int low = 1, high = maxRate;
        int result = maxRate;
        while (low <= high) {
            int mid = (low + high) / 2;
            int h = 0;
            for (int pile : piles) {
                h += (int) Math.ceil((double) pile / mid);
            }
            if (h <= hour) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }
}
