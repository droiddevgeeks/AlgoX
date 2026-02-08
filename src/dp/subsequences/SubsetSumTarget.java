package dp.subsequences;

import java.util.Arrays;

/**
 * Input :  N = 4, ARR = [4, 3, 5, 2], K = 6
 * Output : true
 * Input : N = 3, ARR = [1, 2, 5], K = 4
 * Output : false
 */
public class SubsetSumTarget {
    public static void main(String[] args) {
        int target = 6;
        int[] arr = new int[]{4, 3, 5, 2};
        //int[] arr = new int[]{1, 2, 5};
        int n = arr.length;
        boolean isTargetSumPresent = isSubsetSumTargetPresentRec(arr, n - 1, target);
        System.out.println("SubsetSumTarget Rec--->" + isTargetSumPresent);

        int[][] memo = new int[n][target + 1];
        for (int[] temp : memo) {
            Arrays.fill(temp, -1);
        }

        isTargetSumPresent = isSubsetSumTargetPresentMemo(arr, memo, n - 1, target);
        System.out.println("SubsetSumTarget Memo--->" + isTargetSumPresent);

        isTargetSumPresent = isSubsetSumTargetPresentTabular(arr, target);
        System.out.println("SubsetSumTarget Memo--->" + isTargetSumPresent);
    }

    // TC : 2^n
    private static boolean isSubsetSumTargetPresentRec(int[] arr, int index, int target) {
        if (target == 0) return true;
        if (index == 0) return target == arr[index];

        boolean notPick = isSubsetSumTargetPresentRec(arr, index - 1, target);
        boolean pick = false;
        if (target >= arr[index]) {
            pick = isSubsetSumTargetPresentRec(arr, index - 1, target - arr[index]);
        }
        return pick || notPick;
    }

    private static boolean isSubsetSumTargetPresentMemo(int[] arr, int[][] memo, int index, int target) {
        if (target == 0) return true;
        if (index == 0) return target == arr[index];

        if (memo[index][target] != -1) return memo[index][target] == 1;
        boolean notPick = isSubsetSumTargetPresentMemo(arr, memo, index - 1, target);
        boolean pick = false;
        if (target >= arr[index]) {
            pick = isSubsetSumTargetPresentMemo(arr, memo, index - 1, target - arr[index]);
        }
        memo[index][target] = (pick || notPick) ? 1 : 0;
        return pick || notPick;
    }

    private static boolean isSubsetSumTargetPresentTabular(int[] arr, int target) {
        boolean[][] dp = new boolean[arr.length][target + 1];

        for (int i = 0; i < arr.length; i++) dp[i][0] = true;
        if (arr[0] <= target) dp[0][arr[0]] = true;

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= target; j++) {
                boolean notPick = dp[i - 1][j];
                boolean pick = false;
                if (j >= arr[i]) {
                    pick = dp[i - 1][j - arr[i]];
                }

                dp[i][j] = pick || notPick;
            }
        }
        return dp[arr.length - 1][target];
    }
}
