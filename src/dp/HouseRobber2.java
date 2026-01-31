package dp;

import java.util.Arrays;

/**
 * Problem Statement: A thief needs to rob money in a street.
 * The houses in the street are arranged in a circular manner.
 * Therefore the first and the last house are adjacent to each other.
 * The security system in the street is such that if adjacent houses are robbed, the police will get notified.
 * <p>
 * Given an array of integers “Arr'' which represents money at each house,
 * we need to return the maximum amount of money that the thief can rob without alerting the police.
 * Input: money = [2, 1, 4, 9]
 * Output: 10
 * Input: money = [1, 5, 2, 1, 6]
 * Output: 11
 */
public class HouseRobber2 {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 4, 9};
        //int []arr = new int[]{1, 5, 2, 1, 6};

        int maxRob = findHouseRobber2Recursion(arr, arr.length);
        System.out.println("HouseRobber2 Recursion--->" + maxRob);

        maxRob = findHouseRobber2Tabular(arr, arr.length);
        System.out.println("HouseRobber2 Tabular--->" + maxRob);

        maxRob = findHouseRobber2Memo(arr, arr.length);
        System.out.println("HouseRobber2 Memo--->" + maxRob);

    }

    private static int findHouseRobber2Recursion(int[] arr, int n) {
        if (n == 1) return arr[0];
        if (n == 2) return Math.max(arr[0], arr[1]);
        int case1 = findHouseRobberRecursionHelper(arr, 0, n - 2); // skip last house
        int case2 = findHouseRobberRecursionHelper(arr, 1, n - 1); // skip first house
        return Math.max(case1, case2);
    }

    private static int findHouseRobberRecursionHelper(int[] arr, int start, int n) {
        if (n == start) return arr[n];
        if (n < start) return 0;

        int pick = arr[n] + findHouseRobberRecursionHelper(arr, start, n - 2);
        int notPick = findHouseRobberRecursionHelper(arr, start, n - 1);
        return Math.max(pick, notPick);
    }

    private static int findHouseRobber2Tabular(int[] arr, int n) {
        if (n == 1) return arr[0];
        if (n == 2) return Math.max(arr[0], arr[1]);
        int case1 = findHouseRobberTabularHelper(arr, 0, n - 2);
        int case2 = findHouseRobberTabularHelper(arr, 1, n - 1);
        return Math.max(case1, case2);
    }

    private static int findHouseRobberTabularHelper(int[] arr, int start, int n) {
        int len = arr.length;
        int[] dp = new int[len];
        dp[start] = arr[start];
        dp[start + 1] = Math.max(arr[start], arr[start + 1]);

        for (int i = start + 2; i <= n; i++) {
            int pick = arr[i] + dp[i - 2];
            int notPick = dp[i - 1];
            dp[i] = Math.max(pick, notPick);
        }
        return dp[n];
    }

    private static int findHouseRobber2Memo(int[] arr, int n) {
        if (n == 1) return arr[0];
        if (n == 2) return Math.max(arr[0], arr[1]);

        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        int case1 = findHouseRobberMemoHelper(arr, memo, 0, n - 2);
        int case2 = findHouseRobberMemoHelper(arr, memo, 1, n - 1);
        return Math.max(case1, case2);
    }

    private static int findHouseRobberMemoHelper(int[] arr, int[] memo, int start, int n) {
        if (n == start) return arr[start];
        if (n < start) return 0;
        if (memo[n] != -1) return memo[n];

        int pick = arr[n] + findHouseRobberMemoHelper(arr, memo, start, n - 2);
        int notPick = findHouseRobberMemoHelper(arr, memo, start, n - 1);
        memo[n] = Math.max(pick, notPick);
        return memo[n];
    }


}
