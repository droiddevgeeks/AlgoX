package StackAndQueue;

import java.util.Arrays;
import java.util.Stack;

/**
 * Input:
 * arr = [4, 8, 5, 2, 25]
 * Output:
 * [2, 5, 2, -1, -1]
 */
public class NextSmallerElement {
    public static void main(String[] args) {
        //int[] input = new int[]{4, 8, 5, 2, 25};  //2, 5, 2, -1, -1
        int[] input = new int[]{10, 9, 8, 7};  //9, 8, 7, -1
        int[] result = new int[input.length];
        findNextSmallerElement(input, result);
        System.out.println(Arrays.toString(result));
    }

    private static void findNextSmallerElement(int[] input, int[] result) {
        Stack<Integer> st = new Stack<>();
        for (int i = input.length - 1; i >= 0; i--) {
            while (!st.empty() && input[i] < st.peek()) st.pop();
            result[i] = st.empty() ? -1 : st.peek();
            st.push(input[i]);
        }
    }
}
