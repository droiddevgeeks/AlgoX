package dp.strings;

import java.util.Arrays;

/**
 * Problem Statement : Given two strings s and t, return the number of distinct subsequences of s that equal t.
 * A subsequence of a string is a new string generated from the original string with some characters (can be none)
 * deleted without changing the relative order of the remaining characters.
 * For example, "ace" is a subsequence of "abcde" while "aec" is not.
 * The task is to count how many different ways we can form t from s by deleting some (or no) characters from s.
 * Input: s = "axbxax", t = "axa"
 * Output: 2
 * Input: s = "babgbag", t = "bag"
 * Output: 5
 */
public class DistinctSubsequences {
    public static void main(String[] args) {
        String s = "babgbag";
        String t = "bag";

        int countDistinctSubsequences = countDistinctSubsequencesRec(s, s.length() - 1, t, t.length() - 1);
        System.out.println("DistinctSubsequences REc-->" + countDistinctSubsequences);

        int[][] memo = new int[s.length()][t.length()];
        for (int[] temp : memo) Arrays.fill(temp, -1);
        countDistinctSubsequences = countDistinctSubsequencesMemo(s, s.length() - 1, t, t.length() - 1, memo);
        System.out.println("DistinctSubsequences Memo-->" + countDistinctSubsequences);

        countDistinctSubsequences = countDistinctSubsequencesTabular(s, t);
        System.out.println("DistinctSubsequences Tabular-->" + countDistinctSubsequences);
    }


    private static int countDistinctSubsequencesRec(String s, int i, String t, int j) {
        if (j < 0) return 1; // t string len finish, means we found 1 subsequence
        if (i < 0) return 0; //s string len finish
        if (s.charAt(i) == t.charAt(j))
            // select from s & move both or skip from s and move only s
            return countDistinctSubsequencesRec(s, i - 1, t, j - 1) + countDistinctSubsequencesRec(s, i - 1, t, j);
            //not match, so move s only
        else return countDistinctSubsequencesRec(s, i - 1, t, j);
    }

    private static int countDistinctSubsequencesMemo(String s, int i, String t, int j, int[][] memo) {
        if (j < 0) return 1; // t string len finish, means we found 1 subsequence
        if (i < 0) return 0; //s string len finish

        if (memo[i][j] != -1) return memo[i][j];
        if (s.charAt(i) == t.charAt(j))
            // select from s & move both or skip from s and move only s
            memo[i][j] = countDistinctSubsequencesMemo(s, i - 1, t, j - 1, memo) + countDistinctSubsequencesMemo(s, i - 1, t, j, memo);
            //not match, so move s only
        else memo[i][j] = countDistinctSubsequencesMemo(s, i - 1, t, j, memo);
        return memo[i][j];
    }


    private static int countDistinctSubsequencesTabular(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                else dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[m][n];
    }
}

