package recursion;

import java.util.Stack;

/**
 * You are given a stack of integers.
 * Your task is to reverse the stack using recursion.
 * You may only use standard stack operations (push, pop, top/peek, isEmpty).
 * You are not allowed to use any loop constructs or additional data structures like arrays or queues.
 * Your solution must modify the input stack in-place to reverse the order of its elements.
 * Example 1:
 * Input: stack = [4, 1, 3, 2]
 * Output: [2, 3, 1, 4]
 * Example 2: Input: stack = [1]
 * Output: [1]
 */
public class ReverseStack {
    public static void main(String[] args) {

        Stack<Integer> st = new Stack<>();
        st.push(2);
        st.push(3);
        st.push(1);
        st.push(4);

        reverseWithRecursion(st);

        while (!st.empty()) {
            System.out.print(st.pop() + "--");
        }
    }

    private static void reverseWithRecursion(Stack<Integer> st) {
        if (st.empty()) return;
        int top = st.pop();
        reverseWithRecursion(st);
        insertInStack(st, top);
    }

    private static void insertInStack(Stack<Integer> st, int value) {
        if (st.empty()) {
            st.push(value);
            return;
        }
        int top = st.pop();
        insertInStack(st, value);
        st.push(top);
    }
}
