package arrays;

import java.util.*;

/**
 * Example 1:
 * <p>
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and
 * sell on day 5 (price = 6), profit = 6-1 = 5.
 * <p>
 * Note: That buying on day 2 and selling on day 1
 * is not allowed because you must buy before
 * you sell.
 * <p>
 * Example 2:
 * <p>
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are
 * done and the max profit = 0.
 */
public class StockBuyAndSell {
    public static void main(String[] args) {
        //int[] nums = new int[]{7, 1, 5, 3, 6, 4};
        int[] nums = new int[]{7, 6, 4, 3, 1};

        int maxProfit = maxProfitBruteForce(nums);
        int maxProfitOptimal = maxProfitOptimal(nums);
        System.out.println("StockBuyAndSell ==>" + maxProfit);
        System.out.println("StockBuyAndSell Optimal==>" + maxProfitOptimal);
    }

    private static int maxProfitBruteForce(int[] a) {
        int len = a.length;
        int max = 0;

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                max = Math.max(max, a[j] - a[i]);
            }
        }
        return max;
    }

    private static int maxProfitOptimal(int[] a) {
        int maxProfit = 0;
        int min = a[0];

        for (int i = 1; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
            }
            maxProfit = Math.max(maxProfit, a[i] - min);
        }

        return maxProfit;
    }
}
