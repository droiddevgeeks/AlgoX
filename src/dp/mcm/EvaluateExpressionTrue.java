package dp.mcm;

import java.util.Arrays;

/**
 * Given an expression, A, with operands and operators (OR, AND, XOR), in how many ways can you evaluate the expression to be true,
 * by grouping it in different ways?.
 * Operands are only true and false.
 * Return the number of ways to evaluate the expression modulo 103 + 3.
 * Input: expression = “T|T&F”
 * Output: 1, Explanation: The only way to get the result as true is:(T) | (T&F) = T|F = T
 * Input: expression = “F|T^F”
 * Output: 2, Explanation: There are 2 possible ways to get the result as true:
 * i. (F|T) ^ F = T ^ F = T
 * ii. F | (T^F) = F | T = T
 */
public class EvaluateExpressionTrue {
    public static void main(String[] args) {
        //String epx = "T|T&F";
        String epx = "F|T^F";

        int countWaysToTrue = countEvaluateExpressionTrueRec(epx, 0, epx.length() - 1, 1);
        System.out.println("EvaluateExpressionTrue Rec--->" + countWaysToTrue);

        int n = epx.length();
        int[][][] memo = new int[n + 1][n + 1][2];
        for (int[][] row : memo) {
            for (int[] col : row) {
                Arrays.fill(col, -1);
            }
        }
        countWaysToTrue = countEvaluateExpressionTrueMemo(epx, 0, epx.length() - 1, 1, memo);
        System.out.println("EvaluateExpressionTrue Memo--->" + countWaysToTrue);

        countWaysToTrue = countEvaluateExpressionTrueTabular(epx);
        System.out.println("EvaluateExpressionTrue Tabular--->" + countWaysToTrue);
    }

    private static int countEvaluateExpressionTrueRec(String exp, int i, int j, int isTrue) {
        if (i > j) return 0;
        if (i == j) {
            if (isTrue == 1) return exp.charAt(i) == 'T' ? 1 : 0;
            else return exp.charAt(i) == 'F' ? 1 : 0;
        }

        int ways = 0;
        for (int k = i + 1; k < j; k += 2) {
            int LT = countEvaluateExpressionTrueRec(exp, i, k - 1, 1);
            int LF = countEvaluateExpressionTrueRec(exp, i, k - 1, 0);
            int RT = countEvaluateExpressionTrueRec(exp, k + 1, j, 1);
            int RF = countEvaluateExpressionTrueRec(exp, k + 1, j, 0);

            char op = exp.charAt(k);

            if (op == '&') {
                if (isTrue == 1) ways += LT * RT;
                else ways += LT * RF + LF * RT + LF * RF;
            } else if (op == '|') {
                if (isTrue == 1) ways += LT * RF + RT * LF + RT * LT;
                else ways += LF * RF;
            } else if (op == '^') {
                if (isTrue == 1) ways += LT * RF + RT * LF;
                else ways += LF * RF + LT * RT;
            }
        }
        return ways;
    }

    private static int countEvaluateExpressionTrueMemo(String exp, int i, int j, int isTrue, int[][][] memo) {
        if (i > j) return 0;
        if (i == j) {
            if (isTrue == 1) return exp.charAt(i) == 'T' ? 1 : 0;
            else return exp.charAt(i) == 'F' ? 1 : 0;
        }

        if (memo[i][j][isTrue] != -1) return memo[i][j][isTrue];
        int ways = 0;
        for (int k = i + 1; k < j; k += 2) {
            int LT = countEvaluateExpressionTrueMemo(exp, i, k - 1, 1, memo);
            int LF = countEvaluateExpressionTrueMemo(exp, i, k - 1, 0, memo);
            int RT = countEvaluateExpressionTrueMemo(exp, k + 1, j, 1, memo);
            int RF = countEvaluateExpressionTrueMemo(exp, k + 1, j, 0, memo);

            char op = exp.charAt(k);

            if (op == '&') {
                if (isTrue == 1) ways += LT * RT;
                else ways += LT * RF + LF * RT + LF * RF;
            } else if (op == '|') {
                if (isTrue == 1) ways += LT * RF + RT * LF + RT * LT;
                else ways += LF * RF;
            } else if (op == '^') {
                if (isTrue == 1) ways += LT * RF + RT * LF;
                else ways += LF * RF + LT * RT;
            }
        }
        return memo[i][j][isTrue] = ways;
    }

    private static int countEvaluateExpressionTrueTabular(String exp) {
        int n = exp.length();
        int[][][] dp = new int[n + 1][n + 1][2];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    dp[i][j][1] = (exp.charAt(i) == 'T') ? 1 : 0;
                    dp[i][j][0] = (exp.charAt(i) == 'F') ? 1 : 0;
                } else {
                    for (int k = i + 1; k < j; k++) {
                        int LT = dp[i][k - 1][1];
                        int LF = dp[i][k - 1][0];
                        int RT = dp[k + 1][j][1];
                        int RF = dp[k + 1][j][0];

                        char op = exp.charAt(k);

                        if (op == '&') {
                            dp[i][j][1] += LT * RT;
                            dp[i][j][0] += LT * RF + LF * RT + LF * RF;
                        } else if (op == '|') {
                            dp[i][j][1] += LT * RF + RT * LF + RT * LT;
                            dp[i][j][0] += LF * RF;
                        } else if (op == '^') {
                            dp[i][j][1] += LT * RF + RT * LF;
                            dp[i][j][0] += LF * RF + LT * RT;
                        }
                    }
                }
            }
        }
        return dp[0][n-1][1];
    }
}
