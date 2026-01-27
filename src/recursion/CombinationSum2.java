package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Combination Sum II - Find all unique combinations
 * <p>
 * Problem Statement: Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 * Each number in candidates may only be used once in the combination..
 * Example 1:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]]
 * Explanation: These are the unique combinations whose sum is equal to target.
 * <p>
 * Example 2:
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output: [[1,2,2],[5]]
 * Explanation: These are the unique combinations whose sum is equal to target.
 */
public class CombinationSum2 {
    public static void main(String[] args) {
        int[] input = new int[]{10, 1, 2, 7, 6, 1, 5};
        //int[] input = new int[]{2,5,2,1,2};
        int target = 8;
        List<List<Integer>> res = new ArrayList<>();
        System.out.println("Loop Choice Recursion Based");
        Arrays.sort(input); //group duplicates
        findCombinationSum1LoopBased(input, new ArrayList<>(), 0, target, res);
        for (List<Integer> temp : res) {
            System.out.println(temp);
        }
    }

    private static void findCombinationSum1LoopBased(int[] input, List<Integer> currCombination, int startIndex, int remainingTarget, List<List<Integer>> res) {
        if (remainingTarget == 0) {
            res.add(new ArrayList<>(currCombination));
            return;
        }
        if (remainingTarget < 0) {
            return;
        }

        for (int i = startIndex; i < input.length; i++) {
            if (i > startIndex && input[i] == input[i - 1]) continue;
            currCombination.add(input[i]);
            findCombinationSum1LoopBased(input, currCombination, i + 1, remainingTarget - input[i], res);
            currCombination.removeLast();
        }
    }
}
