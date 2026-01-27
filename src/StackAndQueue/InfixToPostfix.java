package StackAndQueue;

import java.util.Stack;

public class InfixToPostfix {
    public static void main(String[] args) {
        String infix = "a+b*(c^d-e)^(f+g*h)-i";
        StringBuilder postfix = new StringBuilder();
        convertInfix2Postfix(infix, postfix);
        System.out.println("Infix exp---" + infix);
        System.out.println("Postfix exp---" + postfix);
    }

    private static void convertInfix2Postfix(String infix, StringBuilder postfix) {
        Stack<Character> st = new Stack<>();
        char[] exp = infix.toCharArray();
        for (char temp : exp) {
            if (Character.isLetterOrDigit(temp)) postfix.append(temp);
            if (temp == '(') st.push(temp);
            if (temp == ')') {
                while (st.peek() != '(') {
                    postfix.append(st.pop());
                }
                st.pop();
            }
            if (isOperator(temp)) {
                while (!st.empty() && st.peek() != '(' && getPrecedence(temp) <= getPrecedence(st.peek())) {
                    postfix.append(st.pop());
                }
                st.push(temp);
            }
        }
        while (!st.empty()) {
            postfix.append(st.pop());
        }
    }

    private static boolean isOperator(char operator) {
        return operator == '^'
                || operator == '/' || operator == '*'
                || operator == '-' || operator == '+';
    }

    private static int getPrecedence(char operator) {
        if (operator == '^') return 3;
        else if (operator == '/' || operator == '*') return 2;
        else if (operator == '-' || operator == '+') return 1;
        return -1;
    }
}
