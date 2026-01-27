package december.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k, return the total number of subarrays whose sum equals k.
 * A subarray is a contiguous non-empty sequence of elements within an array.
 * Input : N = 4, array[] = {3, 1, 2, 4}, k = 6
 * Output: 2
 * Explanation: The subarrays that sum up to 6 are [3, 1, 2] and [2, 4].
 * <p>
 * Input: N = 3, array[] = {1,2,3}, k = 3
 * Output: 2
 * Explanation: The subarrays that sum up to 3 are [1, 2], and [3].
 */
public class CountSubArraySumEqualsK {
    public static void main(String[] args) {

        HashMap<Integer, int[]> inputs = new HashMap<>();
        inputs.put(6, new int[]{3, 1, 2, 4});
        inputs.put(3, new int[]{2, -1, 2, 1});


        for (Map.Entry<Integer, int[]> entry : inputs.entrySet()) {
            System.out.println("Array is ==>" + Arrays.toString(entry.getValue()));
            int count = findLTotalSubArraySumKBruteForce(entry.getValue(), entry.getKey());
            System.out.println("TotalSubArraySumKBruteForce==>" + count);

            int countOptimal = findLTotalSubArraySumKOptimal(entry.getValue(), entry.getKey());
            System.out.println("CountSubarraySumEqualsK==>" + countOptimal);
        }
    }

    private static int findLTotalSubArraySumKOptimal(int[] value, Integer key) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(0, 1); // base condition when sum found, then count 1.
        int count = 0;
        int sum = 0;
        for (int temp : value) {
            sum += temp;
            count += hm.getOrDefault(sum - key, 0);
            hm.put(sum, hm.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    private static int findLTotalSubArraySumKBruteForce(int[] value, Integer key) {
        int count = 0;
        for (int i = 0; i < value.length; i++) {
            int sum = 0;
            for (int j = i; j < value.length; j++) {
                sum += value[j];
                if (sum == key) count++;
            }
        }
        return count;
    }
}
