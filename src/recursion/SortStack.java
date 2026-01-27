package recursion;

import java.util.Stack;

/**
 * You are given a stack of integers.
 * Your task is to sort the stack in descending order using recursion,
 * such that the top of the stack contains the greatest element.
 * You are not allowed to use any loop-based sorting methods (e.g., quicksort, mergesort).
 * You may only use recursive operations and the standard stack operations (push, pop, peek/top, and isEmpty).
 * Example 1:
 * Input: stack = [4, 1, 3, 2]
 * Output: [4, 3, 2, 1]
 * Example 2: Input: stack = [1]
 * Output: [1]
 */
public class SortStack {
    public static void main(String[] args) {

        Stack<Integer> st = new Stack<>();
        st.push(2);
        st.push(3);
        st.push(1);
        st.push(4);

        sortWithRecursion(st);

        while (!st.empty()) {
            System.out.print(st.pop() + "--");
        }
    }

    private static void sortWithRecursion(Stack<Integer> st) {
        if (st.empty()) return;
        int top = st.pop();
        sortWithRecursion(st);
        insertInStack(st, top);
    }

    private static void insertInStack(Stack<Integer> st, int value) {
        if (st.empty() || st.peek() < value) {
            st.push(value);
            return;
        }
        int top = st.pop();
        insertInStack(st, value);
        st.push(top);
    }
}
