package dp.mcm;

/**
 * Problem Description: Given an integer array arr with length n, and an integer k,
 * you may partition arr into one or more contiguous sub-arrays, where each sub-array has length in the range [1, k] (both inclusive).
 * After you pick a partition, replace every element in each sub-array with the maximum value found in that sub-array.
 * The array is modified in-place for the purpose of computing the total.
 * Return the largest possible sum of the entire array after performing exactly one such partition-and-replace operation.
 * Input: arr = [1,15,7,9,2,5,10], k = 3
 * Output: 84
 * Explanation: The partition will be the following to get the largest sum: [1, 15, 7 | 9 | 2, 5, 10].
 * After replacing the elements of each subarray with its maximum, the array will look like this: [15,15,15,9,10,10,10] and the sum will be 84.
 */
public class PartitionArrayMaximumSum {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 15, 7, 9, 2, 5, 10};
        int k = 3;

        //f(i) = maximum sum we can get starting from index i
        int maxSumAfterPartition = partitionArrayMaximumSumRec(arr, 0, k);
        System.out.println("PartitionArrayMaximumSum Rec-->" + maxSumAfterPartition);

        Integer[] memo = new Integer[arr.length];
        maxSumAfterPartition = partitionArrayMaximumSumMemo(arr, 0, k, memo);
        System.out.println("PartitionArrayMaximumSum Memo-->" + maxSumAfterPartition);

        maxSumAfterPartition = partitionArrayMaximumSumTabular(arr, k);
        System.out.println("PartitionArrayMaximumSum Tabular-->" + maxSumAfterPartition);
    }

    //f(i) = max over all valid partition sizes: (partition_length × max_in_that_partition) + f(next_index)
    private static int partitionArrayMaximumSumRec(int[] arr, int i, int k) {
        if (i == arr.length) return 0;
        int maxElement = 0;
        int maxSum = 0;
        for (int len = 1; len <= k && i + len - 1 < arr.length; len++) {
            maxElement = Math.max(maxElement, arr[i + len - 1]);
            int sum = maxElement * len + partitionArrayMaximumSumRec(arr, i + len, k);
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }

    //O(n * k)
    private static int partitionArrayMaximumSumMemo(int[] arr, int i, int k, Integer[] memo) {
        if (i == arr.length) return 0;
        if (memo[i] != null) return memo[i];
        int maxElement = 0;
        int maxSum = 0;
        for (int len = 1; len <= k && i + len - 1 < arr.length; len++) {
            maxElement = Math.max(maxElement, arr[i + len - 1]);
            int sum = maxElement * len + partitionArrayMaximumSumMemo(arr, i + len, k, memo);
            maxSum = Math.max(sum, maxSum);
        }
        return memo[i] = maxSum;
    }

    //dp[i] = maximum sum starting from index i
    private static int partitionArrayMaximumSumTabular(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1]; // dp[n] = 0 by default

        for (int i = n - 1; i >= 0; i--) {
            int max = 0;
            int maxSum = 0;
            for (int len = 1; len <= k && i + len - 1 < n; len++) {
                max = Math.max(max, arr[i + len - 1]);
                int sum = max * len + dp[i+len];
                maxSum = Math.max(sum, maxSum);
            }
            dp[i]= maxSum;
        }
        return dp[0];
    }
}

/**
 * ⚡ Pattern Recognition
 * Whenever you see:
 * "Partition into subarrays"
 * "Each partition contributes something"
 * "At most k length"
 * <p>
 * Think:
 * State → index based
 * Loop → try partition sizes
 */
