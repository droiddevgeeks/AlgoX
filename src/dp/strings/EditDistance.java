package dp.strings;

import java.util.Arrays;

/**
 * We are given two strings ‘S1’ and ‘S2’. We need to convert S1 to S2. The following three operations are allowed:
 * Deletion of a character, Replacement of a character with another one ,Insertion of a character.
 * We have to return the minimum number of operations required to convert S1 to S2 as our answer.
 * Input: start = "planet", target = "plan"
 * Output: 2
 * Input: start = "abcdefg", target = "azced"
 * Output: 4
 */
public class EditDistance {
    public static void main(String[] args) {
        String s = "abcdefg";
        String t = "azced";

        int minOperation = findMinOperationRec(s, s.length() - 1, t, t.length() - 1);
        System.out.println("EditDistance Rec--->" + minOperation);

        int[][] memo = new int[s.length()][t.length()];
        for (int[] temp : memo) Arrays.fill(temp, -1);
        minOperation = findMinOperationMemo(s, s.length() - 1, t, t.length() - 1, memo);
        System.out.println("EditDistance Memo--->" + minOperation);

        minOperation = findMinOperationTabular(s, t);
        System.out.println("EditDistance Tabular--->" + minOperation);
    }


    private static int findMinOperationRec(String s, int i, String t, int j) {
        if (i < 0) return j + 1; //s finish,  insert remaining chars from target string
        if (j < 0) return i + 1; //t finish, delete remaining chars from s string

        if (s.charAt(i) == t.charAt(j)) return findMinOperationRec(s, i - 1, t, j - 1);

        int insert = 1 + findMinOperationRec(s, i, t, j - 1);   //current operation cost + remaining cost
        int del = 1 + findMinOperationRec(s, i - 1, t, j);      //current operation cost + remaining cost
        int replace = 1 + findMinOperationRec(s, i - 1, t, j - 1); //current operation cost + remaining cost
        return Math.min(insert, Math.min(del, replace));
    }

    private static int findMinOperationMemo(String s, int i, String t, int j, int[][] memo) {
        if (i < 0) return j + 1; //s finish,  insert remaining chars from target string
        if (j < 0) return i + 1; //t finish, delete remaining chars from s string
        if (memo[i][j] != -1) return memo[i][j];
        if (s.charAt(i) == t.charAt(j)) return memo[i][j] = findMinOperationMemo(s, i - 1, t, j - 1, memo);

        int insert = 1 + findMinOperationMemo(s, i, t, j - 1, memo);
        int del = 1 + findMinOperationMemo(s, i - 1, t, j, memo);
        int replace = 1 + findMinOperationMemo(s, i - 1, t, j - 1, memo);
        memo[i][j] = Math.min(insert, Math.min(del, replace));
        return memo[i][j];
    }

    private static int findMinOperationTabular(String s, String t) {
        int m = s.length();
        int n = t.length();

        int[][] dp = new int[m + 1][n + 1];

        // if t is empty then delete all from s
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        // if s is empty then insert all from t
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else {
                    int insert = 1 + dp[i][j - 1];
                    int del = 1 + dp[i - 1][j];
                    int replace = 1 + dp[i - 1][j - 1];
                    dp[i][j] = Math.min(insert, Math.min(del, replace));
                }
            }
        }
        return dp[m][n];
    }
}
