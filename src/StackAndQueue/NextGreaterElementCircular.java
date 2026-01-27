package StackAndQueue;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElementCircular {
    public static void main(String[] args) {
        int[] input = {1, 2, 1};
        int[] result = nextGreaterElements(input);
        System.out.println(Arrays.toString(result));
    }

    private static int[] nextGreaterElements(int[] input) {
        int n = input.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 2 * n - 1; i >= 0; i--) {
            int idx = i % n;

            while (!stack.isEmpty() && stack.peek() <= input[idx]) {
                stack.pop();
            }

            // Only fill result in first pass
            if (i < n) {
                result[idx] = stack.isEmpty() ? -1 : stack.peek();
            }

            stack.push(input[idx]);
        }
        return result;
    }
}
