package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * Example 1:
 * Input:
 * n = 3
 * Output:
 * ["((()))", "(()())", "(())()", "()(())", "()()()"]
 * <p>
 * Example 2:
 * Input:
 * n = 1
 * Output:
 * ["()"]
 */
public class GenerateParenthesis {
    public static void main(String[] args) {
        int n = 3;
        List<String> result = new ArrayList<>();
        //generateParenthesisString("", n, result);
        generateParenthesisStringOptimal("", n, 0, 0, result);
        System.out.println(result);
    }

    private static void generateParenthesisStringOptimal(String s, int n, int open, int close, List<String> result) {
        if (n * 2 == s.length()) {
            result.add(s);
            return;
        }

        if (open < n) generateParenthesisStringOptimal(s + '(', n, open + 1, close, result);
        if (close < open) generateParenthesisStringOptimal(s + ')', n, open, close + 1, result);
    }

    private static void generateParenthesisString(String s, int n, List<String> result) {
        if (n * 2 == s.length()) {
            if (isValid(s)) {
                result.add(s);
            }
            return;
        }

        generateParenthesisString(s + '(', n, result);
        generateParenthesisString(s + ')', n, result);
    }

    private static boolean isValid(String s) {
        int balance = 0;
        for (char temp : s.toCharArray()) {
            if (temp == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }
}
