package StackAndQueue;

import java.util.Arrays;
import java.util.Stack;

/**
 * Example:
 * <p>
 * Input: N =6, heights[] = {2,1,5,6,2,3}
 * Output: 10
 */
public class LargestRectangleHistogram {
    public static void main(String[] args) {
        int[] ht = new int[]{2, 1, 5, 6, 2, 3};
        int maxArea = findLargestRectangleHistogramBruteForce(ht);
        System.out.println("LargestRectangleHistogram---" + maxArea);

        maxArea = findLargestRectangleHistogramStackBased(ht);
        System.out.println("LargestRectangleHistogram Optimized---" + maxArea);
    }

    private static int findLargestRectangleHistogramStackBased(int[] ht) {
        int[] pse = new int[ht.length];
        int[] nse = new int[ht.length];
        int maxArea = 0;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < ht.length; i++) {
            while (!stack.isEmpty() && ht[stack.peek()] >= ht[i]) stack.pop();
            pse[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        System.out.println("PSE---" + Arrays.toString(pse));


        for (int i = ht.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && ht[stack.peek()] >= ht[i]) stack.pop();
            nse[i] = stack.isEmpty() ? ht.length : stack.peek();
            stack.push(i);
        }
        System.out.println("NSE---" + Arrays.toString(nse));

        for (int i = 0; i < ht.length; i++) {
            int width = nse[i] - pse[i] - 1;
            int area = width * ht[i];
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;

    }

    private static int findLargestRectangleHistogramBruteForce(int[] ht) {
        int maxArea = 0;
        for (int i = 0; i < ht.length; i++) {
            int area;
            int smallestLeft = -1;
            int smallestRight = ht.length;
            for (int j = i - 1; j >= 0; j--) {
                if (ht[j] < ht[i]) {
                    smallestLeft = j;
                    break;
                }
            }

            for (int j = i + 1; j < ht.length; j++) {
                if (ht[j] < ht[i]) {
                    smallestRight = j;
                    break;
                }
            }

            int width = smallestRight - smallestLeft - 1;
            area = width * ht[i];
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
}
