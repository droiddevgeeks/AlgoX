package StackAndQueue;

import java.util.Stack;

public class BalancedParentheses {
    public static void main(String[] args) {
        //String str = "()[{}()]";
        String str = "[()";
        boolean isBalanced = checkBalancedParentheses(str);
        System.out.println(isBalanced + "");
    }

    private static boolean checkBalancedParentheses(String str) {
        char[] exp = str.toCharArray();
        Stack<Character> st = new Stack<>();
        for (char temp : exp) {
            if (temp == '(' || temp == '{' || temp == '[') st.push(temp);
            else if (temp == ')') {
                if (st.isEmpty() || st.pop() != '(') return false;
            } else if (temp == '}') {
                if (st.isEmpty() || st.pop() != '{') return false;
            } else if (temp == ']') {
                if (st.isEmpty() || st.pop() != '[') return false;
            }
        }
        return st.isEmpty();
    }
}
