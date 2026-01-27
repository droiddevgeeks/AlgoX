package StackAndQueue;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement {
    public static void main(String[] args) {
        //int[] input = new int[]{1, 3, 2, 4};  //3, 4, 4, -1
        //int[] input = new int[]{6, 8, 0, 1, 3};  //8, -1, 1, 3, -1
        int[] input = new int[]{2, 1, 2, 4, 3};  //4, 2, 4, -1, -1
        int[] result = new int[input.length];
        findNextGreaterElement(input, result);
        System.out.println(Arrays.toString(result));
    }

    private static void findNextGreaterElement(int[] input, int[] result) {
        Stack<Integer> stack = new Stack<>();
        for (int i = input.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= input[i]) stack.pop();
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(input[i]);
        }
    }
}
