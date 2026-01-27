package StackAndQueue;

import java.util.Stack;

/**
 * Example 1:
 * Input:expression = "+ab
 * Output:(a+b)
 * <p>
 * Example 2:
 * Input:expression = "*+ab-cd
 * Output:((a+b)*(c-d))
 */
public class Prefix2InfixConversion {
    public static void main(String[] args) {
        String prefix = "*+ab-cd";
        System.out.println("Prefix---" + prefix);
        String infix = convertPrefix2Infix(prefix);
        System.out.println("Infix---" + infix);
    }

    private static String convertPrefix2Infix(String prefix) {
        Stack<String> st = new Stack<>();
        char[] prefixArray = prefix.toCharArray();
        for (int i = prefixArray.length - 1; i >= 0; i--) {
            char temp = prefixArray[i];
            if (Character.isLetterOrDigit(temp)) st.push(temp + "");
            if (isOperator(temp)) {
                String op1 = st.pop();
                String op2 = st.pop();
                st.push('(' + op1 + temp + op2 + ')');
            }
        }
        return st.pop();
    }

    private static boolean isOperator(char operator) {
        return operator == '^'
                || operator == '/' || operator == '*'
                || operator == '-' || operator == '+';
    }
}
