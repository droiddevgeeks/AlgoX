package StackAndQueue;

import java.util.Stack;

/**
 * Remove K Digits
 * Problem Statement: Given a string nums representing a non-negative integer, and an integer k,
 * find the smallest possible integer after removing k digits from num.
 * Examples
 * nums = "1002991", k = 3
 * Output:"21"
 * nums = "541892", k = 2
 * Output:"1892"
 */
public class RemoveKdigits {
    public static void main(String[] args) {
        //String nums = "541892";
        String nums = "1002991";
        int k = 3;

        String res = removeKdigitForSmallestNumber(nums, k);
        System.out.println(res);
    }

    private static String removeKdigitForSmallestNumber(String nums, int k) {
        Stack<Character> stack = new Stack<>();
        char[] temp = nums.toCharArray();
        for (Character digit : temp) {
            while (!stack.isEmpty() && k > 0 && stack.peek() > digit) {
                stack.pop();
                k--;
            }
            stack.push(digit);
        }
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            if (sb.isEmpty() && c == '0') continue;
            sb.append(c);
        }
        return sb.isEmpty() ? "0" : sb.toString();
    }
}
