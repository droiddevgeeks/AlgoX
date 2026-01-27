package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of distinct integers and a target, you have to return the list of all unique combinations
 * where the chosen numbers sum to target. You may return the combinations in any order.
 * <p>
 * The same number may be chosen from the given array an unlimited number of times.
 * Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 * <p>
 * It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 * <p>
 * Example 1:
 * Input: array = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation: 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 * Example 2:
 * Input: array = [2], target = 1
 * Output: []
 * Explanation: No combination is possible.
 */
public class CombinationSum1 {
    public static void main(String[] args) {
        int[] input = new int[]{2, 3, 6, 7};
        //int[] input = new int[]{2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> res = new ArrayList<>();
        findCombinationSum1UsingBinaryRecursion(input, new ArrayList<>(), 0, target, res);
        System.out.println("Binary Recursion Based");
        for (List<Integer> temp : res) {
            System.out.println(temp);
        }
        res.clear();
        System.out.println("Loop Choice Recursion Based");
        findCombinationSum1LoopBased(input, new ArrayList<>(), 0, target, res);
        for (List<Integer> temp : res) {
            System.out.println(temp);
        }
    }

    // This is binary recursion based. Either select or not select.
    private static void findCombinationSum1UsingBinaryRecursion(int[] input, List<Integer> currCombination, int currIndex, int remainingTarget, List<List<Integer>> res) {
        if (remainingTarget == 0) {
            res.add(new ArrayList<>(currCombination));
            return;
        }
        if (remainingTarget < 0 || currIndex == input.length) {
            return;
        }
        currCombination.add(input[currIndex]);
        findCombinationSum1UsingBinaryRecursion(input, currCombination, currIndex, remainingTarget - input[currIndex], res);
        currCombination.removeLast();
        findCombinationSum1UsingBinaryRecursion(input, currCombination, currIndex + 1, remainingTarget, res);
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
            currCombination.add(input[i]);
            findCombinationSum1LoopBased(input, currCombination, i, remainingTarget - input[i], res);
            currCombination.removeLast();
        }
    }
}
