package dp.strings;

import java.util.Arrays;

/**
 * Given two strings str1 and str2, find the length of their longest common substring.
 * A substring is a contiguous sequence of characters within a string.
 * str1 = "abcde", str2 = "abfce" ,Output: 2
 * str1 = "abcdxyz", str2 = "xyzabcd" , Output: 4
 */
public class LCSSubString {
    public static void main(String[] args) {
        String a = "abcdxyz";
        String b = "xyzabcd";
        findMaxLCSSubString(a, b);

        int maxLenTabular = findMaxLCSSubStringTabular(a, b);
        System.out.println("LCSSubString tabular-->" + maxLenTabular);
    }

    private static void findMaxLCSSubString(String a, String b) {
        int m = a.length();
        int n = b.length();
        int maxLenRec = 0;

        int maxLenMemo = 0;
        int[][] memo = new int[m][n];
        for (int[] temp : memo) Arrays.fill(temp, -1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxLenRec = Math.max(maxLenRec, findMaxLCSSubStringRec(a, i, b, j));
                maxLenMemo = Math.max(maxLenMemo, findMaxLCSSubStringMemo(a, i, b, j, memo));
            }
        }

        System.out.println("LCSSubString Rec-->" + maxLenRec);
        System.out.println("LCSSubString Memo-->" + maxLenMemo);
    }

    private static int findMaxLCSSubStringRec(String a, int m, String b, int n) {
        if (m < 0 || n < 0) return 0;
        if (a.charAt(m) == b.charAt(n)) return 1 + findMaxLCSSubStringRec(a, m - 1, b, n - 1);
        return 0;
    }

    private static int findMaxLCSSubStringMemo(String a, int m, String b, int n, int[][] memo) {
        if (m < 0 || n < 0) return 0;
        if (memo[m][n] != -1) return memo[m][n];
        if (a.charAt(m) == b.charAt(n)) {
            memo[m][n] = 1 + findMaxLCSSubStringMemo(a, m - 1, b, n - 1, memo);
        } else {
            memo[m][n] = 0;
        }
        return memo[m][n];
    }

    private static int findMaxLCSSubStringTabular(String a, String b) {
        int m = a.length();
        int n = b.length();
        int[][] dp = new int[m + 1][n + 1];
        int maxLen = Integer.MIN_VALUE;
        int endIndex = 0; // to print LCS substring

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        endIndex = i;
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        System.out.println("LCSSubString tabular-->" + a.substring(endIndex-maxLen, endIndex));
        return maxLen;
    }
}
