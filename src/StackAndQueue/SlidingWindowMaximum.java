package StackAndQueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Sliding Window Maximum
 * <p>
 * Problem Statement: Given an array of integers arr, there is a sliding window of size k which is moving from
 * the very left of the array to the very right. You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position. Return the max sliding window..
 * <p>
 * Examples
 * Input: arr = [4,0,-1,3,5,3,6,8], k = 3 , Output: [4,3,5,5,6,8]
 * Explanation:
 * Window position                   Max
 * ------------------------         -----
 * [4  0  -1] 3  5  3  6  8           4
 * 4 [0  -1  3] 5  3  6  8           3
 * 4  0 [-1  3  5] 3  6  8           5
 * 4  0  -1 [3  5  3] 6  8           5
 * 4  0  -1  3 [5  3  6] 8           6
 * 4  0  -1  3  5 [3  6  8]          8
 * <p>
 * For each window of size k=3, we find the maximum element in the window and add it to our output array.
 * <p>
 * Input: arr= [20,25], k = 2
 * Output: [25]
 * Explanation: There’s just one window is size 2 that is possible and the maximum of the two elements is our answer
 */
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 0, -1, 3, 5, 3, 6, 8};
        int k = 3;
        List<Integer> slidingWindowMax = findSlidingWindowMaximumBruteForce(arr, k);
        System.out.println(slidingWindowMax);

        slidingWindowMax.clear();
        slidingWindowMax = findSlidingWindowMaximumDequeBased(arr, k);
        System.out.println(slidingWindowMax);
    }

    private static List<Integer> findSlidingWindowMaximumBruteForce(int[] arr, int window) {
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i <= arr.length - window; i++) {
            int max = arr[i];
            for (int j = i; j < i + window; j++) {
                max = Math.max(max, arr[j]);
            }
            res.add(max);
        }
        return res;
    }

    private static List<Integer> findSlidingWindowMaximumDequeBased(int[] arr, int window) {
        List<Integer> res = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < arr.length; i++) {
            if (!deque.isEmpty() && deque.peekFirst() <= i - window) deque.pollFirst();
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]) deque.pollLast();

            deque.offer(i);
            if (i >= window - 1 && !deque.isEmpty()) {
                res.add(arr[deque.peekFirst()]);
            }
        }
        return res;
    }

}
