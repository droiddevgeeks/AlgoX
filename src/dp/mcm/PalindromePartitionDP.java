package dp.mcm;

import java.util.Arrays;

import static recursion.PalindromePartitioning.isPalindrome;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * Input: s = “bababcbadcede”
 * Output: 4
 * Input: s = "aab"
 * Output: 1
 */
public class PalindromePartitionDP {
    public static void main(String[] args) {
        String s = "bababcbadcede";

        //partitions = cuts + 1.

        int partitions = calculatePalindromePartitionRec(s, 0, s.length() - 1);
        System.out.println("PalindromePartitionDP Rec--->" + (partitions - 1));

        int[][] memo = new int[s.length()][s.length()];
        for (int[] temp : memo) Arrays.fill(temp, -1);
        partitions = calculatePalindromePartitionMemo(s, 0, s.length() - 1, memo);
        System.out.println("PalindromePartitionDP Memo--->" + (partitions - 1));

        partitions = calculatePalindromePartitionTabular(s);
        System.out.println("PalindromePartitionDP Tabular--->" + (partitions - 1));
    }

    //f(i) = minimum partitions needed from index i to end:: partitions = cuts + 1.
    private static int calculatePalindromePartitionRec(String s, int i, int j) {
        if (i == j) return 0;
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            if (isPalindrome(s, i, k)) {
                int cost = 1 + calculatePalindromePartitionRec(s, k + 1, j);
                min = Math.min(cost, min);
            }
        }
        // min will have partition count. To calculate cuts, we have to subtract 1 to partition.
        return min;
    }

    //f(i) = minimum partitions needed from index i to end:: partitions = cuts + 1.
    private static int calculatePalindromePartitionMemo(String s, int i, int j, int[][] memo) {
        if (i == j) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            if (isPalindrome(s, i, k)) {
                int cost = 1 + calculatePalindromePartitionMemo(s, k + 1, j, memo);
                min = Math.min(cost, min);
            }
        }
        // min will have partition count. To calculate cuts, we have to subtract 1 to partition.
        return memo[i][j] = min;
    }

    //precompute palindrome..
    private static int calculatePalindromePartitionTabular(String s) {
        int n = s.length();
        boolean[][] pal = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || pal[i + 1][j - 1])) {
                    pal[i][j] = true;
                }
            }
        }

        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            int min = Integer.MAX_VALUE;
            for (int j = i; j < n; j++) {
                if (pal[i][j]) {
                    min = Math.min(min, 1 + dp[j + 1]);
                }
            }
            dp[i] = min;
        }
        System.out.println(Arrays.toString(dp));
        return dp[0];
    }
}
