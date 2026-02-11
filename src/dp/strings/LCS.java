package dp.strings;

import java.util.Arrays;

/**
 * Given two strings str1 and str2, find the length of their longest common subsequence.
 * A subsequence is a sequence that appears in the same relative order but not necessarily contiguous and
 * a common subsequence of two strings is a subsequence that is common to both strings.
 * Example 1:
 * Input: str1 = "bdefg", str2 = "bfg"
 * Output:3
 * Input: str1 = "mnop", str2 = "mnq"
 * Output: 2
 */
public class LCS {
    public static void main(String[] args) {
        String str1 = "bdefg"; String str2 = "bfg";
       // String str1 = "mnop";String str2 = "mnq";
        int lcs = findLCSRec(str1, str1.length() - 1, str2, str2.length() - 1);
        System.out.println("LCS Rec --->" + lcs);

        int[][] memo = new int[str1.length()][str2.length()];
        for (int[] temp : memo) Arrays.fill(temp, -1);
        lcs = findLCSMemo(str1, str1.length() - 1, str2, str2.length() - 1, memo);
        System.out.println("LCS using Memo --->" + lcs);

        String[][] memoString = new String[str1.length()][str2.length()];
        for (String[] temp : memoString) Arrays.fill(temp, null);
        String lcsString = findLCSStringMemo(str1, str1.length() - 1, str2, str2.length() - 1, memoString);
        System.out.println("LCS String using Memo --->" + lcsString);


        lcs = findLCSTabular(str1, str2);
        System.out.println("LCS Tabular --->" + lcs);

        lcsString = findLCSTabularString(str1, str2);
        System.out.println("LCS String using Tabular --->" + lcsString);

    }

    //f(i, j) = LCS length using s1[0..i] and s2[0..j]
    private static int findLCSRec(String a, int m, String b, int n) {
        if (m < 0 || n < 0) return 0;
        if (a.charAt(m) == b.charAt(n)) return 1 + findLCSRec(a, m - 1, b, n - 1);

        int left = findLCSRec(a, m - 1, b, n);
        int right = findLCSRec(a, m, b, n - 1);
        return Math.max(left, right);
    }

    private static int findLCSMemo(String a, int m, String b, int n, int[][] memo) {
        if (m < 0 || n < 0) return 0;
        if (a.charAt(m) == b.charAt(n)) return 1 + findLCSMemo(a, m - 1, b, n - 1, memo);
        if (memo[m][n] != -1) return memo[m][n];

        int left = findLCSMemo(a, m - 1, b, n, memo);
        int right = findLCSMemo(a, m, b, n - 1, memo);
        return memo[m][n] = Math.max(left, right);
    }


    private static String findLCSStringMemo(String a, int m, String b, int n, String[][] memo) {
        if (m < 0 || n < 0) return "";
        if (a.charAt(m) == b.charAt(n)) return a.charAt(m) + findLCSStringMemo(a, m - 1, b, n - 1, memo);
        if (memo[m][n] != null) return memo[m][n];

        String left = findLCSStringMemo(a, m - 1, b, n, memo);
        String right = findLCSStringMemo(a, m, b, n - 1, memo);
        return memo[m][n] = (left.length() > right.length()) ? left : right;
    }


    //dp[i][j] = LCS of s1[0..i-1] and s2[0..j-1]
    private static int findLCSTabular(String a, String b) {
        int n = a.length();
        int m = b.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }

    //dp[i][j] = LCS of s1[0..i-1] and s2[0..j-1]
    private static String findLCSTabularString(String a, String b) {
        int n = a.length();
        int m = b.length();

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // backtrack
        StringBuilder lcs = new StringBuilder();
        int i = n, j = m;

        while (i > 0 && j > 0) {
            if (a.charAt(i - 1) == b.charAt(j - 1)) {
                lcs.append(a.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else j--;
        }
        return lcs.reverse().toString();
    }
}

