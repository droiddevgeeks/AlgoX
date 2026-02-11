package dp.strings;

import java.util.Arrays;

/**
 * Problem Statement: Given a string, Find the longest palindromic subsequence length in given string.
 * A palindrome is a sequence that reads the same backwards as forward.
 * Input:s = "eeeme", Output:4
 * Input:s = "annb" , Output:2
 */
public class LPS {
    public static void main(String[] args) {
        String a = "eeeme";
        int maxLPSLen = findLPSRec(a, 0, a.length() - 1);
        System.out.println("LPS REc--->" + maxLPSLen);

        int n = a.length();
        int[][] memo = new int[n][n];
        for (int[] temp : memo) {
            Arrays.fill(temp, -1);
        }
        maxLPSLen = findLPSMemo(a, 0, a.length() - 1, memo);
        System.out.println("LPS Memo--->" + maxLPSLen);

        maxLPSLen = findLPSTabular(a);
        System.out.println("LPS Tabular--->" + maxLPSLen);
    }

    private static int findLPSRec(String a, int s, int e) {
        if (s > e) return 0;
        if (s == e) return 1;

        if (a.charAt(s) == a.charAt(e)) return 2 + findLPSRec(a, s + 1, e - 1);

        int left = findLPSRec(a, s + 1, e);
        int right = findLPSRec(a, s, e - 1);
        return Math.max(left, right);
    }

    private static int findLPSMemo(String a, int s, int e, int[][] memo) {
        if (s > e) return 0;
        if (s == e) return 1;

        if (memo[s][e] != -1) return memo[s][e];

        if (a.charAt(s) == a.charAt(e)) return 2 + findLPSRec(a, s + 1, e - 1);

        int left = findLPSRec(a, s + 1, e);
        int right = findLPSRec(a, s, e - 1);
        return memo[s][e] = Math.max(left, right);
    }

    private static int findLPSTabular(String a) {
        int n = a.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (a.charAt(i) == a.charAt(j)) dp[i][j] = 2 + dp[i + 1][j - 1];
                else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        for (int[] temp : dp) System.out.println(Arrays.toString(temp));
        return dp[0][n - 1];
    }
}
