package recursion;

import java.util.Arrays;

/**
 * Count all subsequences with sum K
 * Problem Statement: Given an array nums and an integer k.
 * Return the number of non-empty subsequences of nums such that the sum of all elements in the subsequence is equal to k.
 * Example 1: Input : nums = [4, 9, 2, 5, 1] , k = 10
 * Output : 2
 * Explanation : The possible subsets with sum k are [9, 1] , [4, 5, 1].
 * Example 2:Input : nums = [4, 2, 10, 5, 1, 3] , k = 5
 * Output :3
 * Explanation : The possible subsets with sum k are [4, 1] , [2, 3] , [5].
 */
public class CountSubSequencesWithSumK {
    public static void main(String[] args) {
        int[] nums = new int[]{4, 9, 2, 5, 1};
        int k = 10;
        System.out.println("Nums---" + Arrays.toString(nums));
        int count = countSubSequencesWithSumK(nums, 0, 0, k);
        System.out.println("CountSubSequencesWithSumK---" + count);
    }

    private static int countSubSequencesWithSumK(int[] nums, int index, int currSum, int k) {
        if (index == nums.length) {
            if (currSum == k) return 1;
            else return 0;
        }
        int sumWithout = countSubSequencesWithSumK(nums, index + 1, currSum, k);
        int sumWith = countSubSequencesWithSumK(nums, index + 1, currSum + nums[index], k);
        return sumWithout + sumWith;
    }
}
