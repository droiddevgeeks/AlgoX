package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem Statement: Determine all possible set of k numbers that can be added together to equal n
 * while meeting the following requirements:
 * 1. There is only use of numerals 1 through 9.
 * 2. A single use is made of each number.
 * Return list of every feasible combination that is allowed.
 * The combinations can be returned in any order, but the list cannot have the same combination twice.
 * <p>
 * Example 1:
 * Input: k = 3, n = 7
 * Output: [[1, 2, 4]]
 * Explanation:
 * 1 + 2 + 4 = 7
 * There are no other valid combinations.
 * Example 2:
 * Input: k = 3, n = 9
 * Output: [[1, 2, 6],[1, 3, 5],[2, 3, 4]]
 * Explanation:
 * 1 + 2 + 6 = 9
 * 1 + 3 + 5 = 9
 * 2 + 3 + 4 = 9
 * There are no other valid combinations.
 */
public class CombinationSum3 {
    public static void main(String[] args) {
        int combinationLengthAllowed = 3;
        int targetSum = 7;
        List<List<Integer>> res = new ArrayList<>();
        System.out.println("Loop Choice Recursion Based");
        findCombinationSum1LoopBased(new ArrayList<>(), 1, combinationLengthAllowed, targetSum, res);
        for (List<Integer> temp : res) {
            System.out.println(temp);
        }
    }

    private static void findCombinationSum1LoopBased(List<Integer> currCombination,
                                                     int startIndex,
                                                     int combinationLengthAllowed,
                                                     int targetSum,
                                                     List<List<Integer>> res) {
        if (targetSum == 0 && combinationLengthAllowed == 0) {
            res.add(new ArrayList<>(currCombination));
            return;
        }
        if (targetSum < 0 || combinationLengthAllowed < 0) {
            return;
        }

        for (int i = startIndex; i < 9; i++) {
            currCombination.add(i);
            findCombinationSum1LoopBased(
                    currCombination,
                    i + 1, // don't reuse same element. At-most 1
                    combinationLengthAllowed -1,
                    targetSum -i,
                    res);
            currCombination.removeLast();
        }
    }
}
