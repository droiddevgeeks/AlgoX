package StackAndQueue;

import java.util.Arrays;
import java.util.Stack;

/**
 * Problem Statement: Given an array of integers asteroids,
 * where each integer represents an asteroid in a row, determine the state of the asteroids after all collisions.
 * In this array, the absolute value represents the size of the asteroid, and the sign represents its direction (positive meaning right and negative meaning left). All asteroids move at the same speed.
 * <p>
 * When two asteroids meet, the smaller one will explode. If they are the same size, both will explode.
 * Asteroids moving in the same direction will never meet.
 * Example 1:
 * Input:
 * asteroids = [2, -2]
 * Output:[]
 * Explanation: The asteroid with size 2 and the one with size -2 collide, exploding each other.
 * <p>
 * Example 2:
 * Input:asteroids = [10, 20, -10]
 * Output:[10, 20]
 * Explanation: The asteroid with size 20 and the one with size -10 collide, resulting in the remaining asteroid with size 20. The asteroids with sizes 10 and 20 never collide.
 */
public class AsteroidCollision {

    public static void main(String[] args) {
        int[] input = new int[]{10, 20, -10};
        int[] res = findAsteroidCollision(input);
        System.out.println(Arrays.toString(res));
    }

    private static int[] findAsteroidCollision(int[] input) {
        Stack<Integer> st = new Stack<>();
        for (int i : input) {
            boolean isAlive = true;
            while (isAlive && !st.isEmpty() && st.peek() > 0 && i < 0) {
                int top = st.peek();

                if (Math.abs(top) < Math.abs(i)) st.pop();
                else if (Math.abs(top) == Math.abs(i)) {
                    st.pop();
                    isAlive = false;
                } else isAlive = false;
            }
            if (isAlive) {
                st.push(i);
            }
        }
        int[] res = new int[st.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = st.pop();
        }
        return res;
    }
}
