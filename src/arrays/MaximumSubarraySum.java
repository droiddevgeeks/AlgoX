package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Example 1:
 * <p>
 * Input: arr = [-2,1,-3,4,-1,2,1,-5,4]
 * <p>
 * Output: 6
 * <p>
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * <p>
 * Examples 2:
 * <p>
 * Input: arr = [1]
 * <p>
 * Output: 1
 * <p>
 * Explanation: Array has only one element and which is giving positive sum of 1.
 */
public class MaximumSubarraySum {
    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        //int[] nums = new int[]{1};
        maxSubArraySum(nums);
        kadaneMaxSubArraySum(nums);
    }

    private static void maxSubArraySum(int[] a) {
        int maxSum = Integer.MIN_VALUE;
        if (a.length == 1) {
            System.out.println("MAX sum==>" + a[0]);
            return;
        }
        int sIndex = -1;
        int eIndex = -1;
        for (int i = 0; i < a.length; i++) {
            int sum = a[i];
            for (int j = i + 1; j < a.length; j++) {
                sum += a[j];
                if (sum > maxSum) {
                    maxSum = sum;
                    sIndex = i;
                    eIndex = j;
                }
            }
        }
        System.out.println("SubArray is::=>" + Arrays.toString(Arrays.copyOfRange(a, sIndex, eIndex + 1)));
        System.out.println("MAX sum==>" + maxSum);
    }

    private static void kadaneMaxSubArraySum(int[] a) {
        int maxSum = Integer.MIN_VALUE;
        if (a.length == 1) {
            System.out.println("MAX sum==>" + a[0]);
            return;
        }
        int sIndex = -1;
        int eIndex = -1;
        int start = 0;
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            if (sum == 0) start = i;
            sum += a[i];

            if (maxSum < sum) {
                maxSum = sum;
                sIndex = start;
                eIndex = i;
            }
            if (sum < 0) {
                sum = 0;
            }
        }

        System.out.println("SubArray is::=>" + Arrays.toString(Arrays.copyOfRange(a, sIndex, eIndex+1)));
        System.out.println("MAX sum==>" + maxSum);
    }


}
