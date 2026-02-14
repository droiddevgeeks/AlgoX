package dp.strings;

import java.util.Arrays;

/**
 * Problem Statement: We are given two strings ‘S1’ and ‘S2’. String S1 can have the following two special characters.
 * ‘?’ can be matched to a single character of S2.
 * ‘*’ can be matched to any sequence of characters of S2. (sequence can be of length zero or more).
 * We need to check whether strings S1 and S2 match or not.
 * Input: S1 = "ab*cd", S2 = "abdefcd"
 * Output: true
 * Input: S1 = "*a*b", S2 = "aaab"
 * Output: true
 */
public class WildcardMatching {
    public static void main(String[] args) {
        String s = "abdefcd";
        String p = "ab*cd";

        boolean isMatching = isWildcardMatchingRec(s, s.length() - 1, p, p.length() - 1);
        System.out.println("WildcardMatching Rec-->" + isMatching);

        Boolean[][] memo = new Boolean[s.length()][p.length()];
        for (Boolean[] temp : memo) Arrays.fill(temp, null);
        isMatching = isWildcardMatchingMemo(s, s.length() - 1, p, p.length() - 1, memo);
        System.out.println("WildcardMatching Memo-->" + isMatching);

        isMatching = isWildcardMatchingTabular(s, p);
        System.out.println("WildcardMatching Tabular-->" + isMatching);
    }

    private static boolean isWildcardMatchingRec(String s, int i, String p, int j) {
        if (i < 0 && j < 0) return true; // both left
        if (i >= 0 && j < 0) return false; // pattern finish , string left
        if (i < 0 && j >= 0) { // string finish ,pattern left
            for (int k = 0; k <= j; k++) {
                if (p.charAt(k) != '*') return false;
            }
            return true;
        }

        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') return isWildcardMatchingRec(s, i - 1, p, j - 1);

        if (p.charAt(j) == '*') {
            // match one char or // match empty
            return isWildcardMatchingRec(s, i - 1, p, j) || isWildcardMatchingRec(s, i, p, j - 1);
        }
        return false;
    }

    private static boolean isWildcardMatchingMemo(String s, int i, String p, int j, Boolean[][] memo) {
        if (i < 0 && j < 0) return true; // both left
        if (i >= 0 && j < 0) return false; // pattern finish , string left
        if (i < 0 && j >= 0) { // string finish ,pattern left
            for (int k = 0; k <= j; k++) {
                if (p.charAt(k) != '*') return false;
            }
            return true;
        }

        if (memo[i][j] != null) return memo[i][j];

        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
            return memo[i][j] = isWildcardMatchingMemo(s, i - 1, p, j - 1, memo);

        if (p.charAt(j) == '*') {
            // match one char or // match empty
            return memo[i][j] = isWildcardMatchingMemo(s, i - 1, p, j, memo) || isWildcardMatchingMemo(s, i, p, j - 1, memo);
        }
        return memo[i][j] = false;
    }

    private static boolean isWildcardMatchingTabular(String s, String p) {
        int m = s.length();
        int n = p.length();

        Boolean[][] dp = new Boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            boolean allStar = true;
            for (int k = 1; k <= j; k++) {
                if (p.charAt(k) != '*') {
                    allStar = false;
                    break;
                }
            }
            //Does empty string "" match pattern prefix p[0..j-1] ?
            dp[0][j] = allStar;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?')
                    dp[i][j] = dp[i - 1][j - 1];
                else if (p.charAt(j - 1) == '*') {
                    // match one char or // match empty
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else dp[i][j] = false;
            }
        }
        return dp[m][n];
    }
}