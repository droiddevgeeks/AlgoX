package StackAndQueue;

import java.util.Stack;

/**
 * Problem Statement: Given an array of integers arr of size n, calculate the sum of the minimum value in each (contiguous) subarray of arr.
 * Since the result may be large, return the answer modulo 10⁹ +7.
 * <p>
 * Example 1:
 * Input:
 * arr = [3, 1, 2, 5]
 * Output:
 * 18
 * Explanation:
 * The minimum of subarrays: [3], [1], [2], [5], [3, 1], [1, 2], [2, 5], [3, 1, 2], [1, 2, 5], [3, 1, 2, 5] are 3, 1, 2, 5, 1, 1, 2, 1, 1, 1 respectively and their sum is 18.
 * <p>
 * Example 2:
 * Input:
 * arr = [2, 3, 1]
 * Output:
 * 10
 * Explanation:
 * The minimum of subarrays: [2], [3], [1], [2,3], [3,1], [2,3,1] are 2, 3, 1, 2, 1, 1 respectively and their sum is 10.
 */
public class SumofSubarrayMinimums {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 2, 5};
        long sum = findSumofSubarrayMinimums(arr);
        System.out.println(sum);

        sum = findSumofSubarrayMinimumsUsingStack(arr);
        System.out.println(sum);
    }

    private static long findSumofSubarrayMinimums(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            for (int j = i; j < arr.length; j++) {
                min = Math.min(min, arr[j]);
                sum += min;
            }
        }
        return sum;
    }

    private static int findSumofSubarrayMinimumsUsingStack(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int MOD = 1_000_000_007;
        //PSE
        int[] pse = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) stack.pop();
            pse[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();

        //NSE
        int[] nse = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) stack.pop();
            nse[i] = stack.isEmpty() ? arr.length : stack.peek();
            stack.push(i);
        }

        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            long left = i - pse[i];
            long right = nse[i] - i;
            long contribution = (left * arr[i]) % MOD;
            contribution = (contribution * right) % MOD;

            sum = (sum + contribution) % MOD;
        }
        return (int) sum;
    }
}
