package StackAndQueue;

import java.util.Stack;

/**
 * Problem Statement: Design a stack that supports the following operations in constant time:
 * push, pop, top, and retrieving the minimum element.
 * <p>
 * Implement the MinStack class:
 * <p>
 * MinStack(): Initializes the stack object.
 * void push(int val): Pushes the element val onto the stack.
 * void pop(): removes the element on the top of the stack.
 * int top(): gets the top element of the stack.
 * int getMin(): retrieves the minimum element in the stack.
 * Example 1:
 * Input:["MinStack", "push", "push", "push", "getMin", "pop", "top", "getMin"]
 * [ [], [-2], [0], [-3], [ ], [ ], [ ], [ ] ]
 * Output:[null, null, null, null, -3, null, 0, -2]
 * Explanation:
 * <p>
 * MinStack minStack = new MinStack();
 * - minStack.push(-2);
 * - minStack.push(0);
 * - minStack.push(-3);
 * - minStack.getMin(); // returns -3
 * - minStack.pop();
 * - minStack.top(); // returns 0
 * - minStack.getMin(); // returns -2
 */
public class MinStackImpl {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        // Function calls
//        minStack.push(-2);
//        minStack.push(0);
//        minStack.push(-3);
//        System.out.print(minStack.getMin() + " ");
//        minStack.pop();
//        System.out.print(minStack.top() + " ");
//        minStack.pop();
//        System.out.print(minStack.getMin());

        minStack.push(5);
        minStack.push(1);
        System.out.print(minStack.getMin() + " ");
        minStack.push(3);
        minStack.pop();
        System.out.print(minStack.getMin() + " ");
        System.out.print(minStack.top() + " ");// returns 1
    }

}

class MinStack {

    private final Stack<Integer> st;
    private final Stack<Integer> minStack;

    public MinStack() {
        st = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        st.push(val);
        if (minStack.isEmpty()) minStack.push(val);
        else {
            minStack.push(Math.min(val, minStack.peek()));
        }
    }

    public int top() {
        return st.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public void pop() {
        if (!st.isEmpty()) {
            st.pop();
            minStack.pop();
        }

    }
}


