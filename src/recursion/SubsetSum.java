package recursion;

import java.util.*;

/**
 * Subset Sum : Sum of all Subsets
 * Problem Statement: Given an array print all the sum of the subset generated from it, in the increasing order.
 * <p>
 * Input: N = 3, arr[] = {5,2,1}
 * Output: 0,1,2,3,5,6,7,8
 * Explanation: We have to find all the subset’s sum and print them.
 * in this case the generated subsets are [ [], [1], [2], [2,1], [5], [5,1], [5,2]. [5,2,1],
 * so the sums we get will be  0,1,2,3,5,6,7,8
 * <p>
 * Input: N=3,arr[]= {3,1,2}
 * Output: 0,1,2,3,3,4,5,6
 * Explanation: We have to find all the subset’s sum and print them.
 * in this case the generated subsets are [ [], [1], [2], [2,1], [3], [3,1], [3,2]. [3,2,1],
 * so the sums we get will be  0,1,2,3,3,4,5,6
 */
public class SubsetSum {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2};
        List<Integer> res = new ArrayList<>();
        System.out.println("SubsetSum All subsets");
        generateAllSubSetsSum(nums, 0, 0, res);
        Collections.sort(res);
        System.out.println(res);

        System.out.println("All Unique subsets");
        List<List<Integer>> subsets = new ArrayList<>();
        Arrays.sort(nums);
        generateAllUniqueSubSets(nums, 0, new ArrayList<>(), subsets);
        for (List<Integer> temp : subsets) {
            System.out.println(temp);
        }
    }

    private static void generateAllSubSetsSum(int[] nums, int currIndex, int currSum, List<Integer> res) {
        if (currIndex == nums.length) {
            res.add(currSum);
            return;
        }
        generateAllSubSetsSum(nums, currIndex + 1, currSum, res);
        generateAllSubSetsSum(nums, currIndex + 1, currSum + nums[currIndex], res);
    }

    private static void generateAllUniqueSubSets(int[] nums, int currIndex, List<Integer> currList, List<List<Integer>> res) {
        res.add(new ArrayList<>(currList));
        for (int i = currIndex; i < nums.length; i++) {
            if (i > currIndex && nums[i] == nums[i - 1]) continue;
            currList.add(nums[i]);
            generateAllUniqueSubSets(nums, i + 1, currList, res);
            currList.removeLast();
        }

    }
}
