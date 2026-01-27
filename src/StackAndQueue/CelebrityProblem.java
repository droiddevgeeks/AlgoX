package StackAndQueue;

import java.util.Stack;

/**
 * Problem Statement: A celebrity is a person who is known by everyone else at the party but does not know anyone in return.
 * Given a square matrix M of size N x N where M[i][j] is 1 if person i knows person j, and 0 otherwise,
 * determine if there is a celebrity at the party. Return the index of the celebrity or -1 if no such person exists.
 * <p>
 * Note that M[i][i] is always 0.
 */
public class CelebrityProblem {
    public static void main(String[] args) {
        //int[][] mat = new int[][]{{0, 1, 1, 0}, {0, 0, 0, 0}, {1, 1, 0, 0}, {0, 1, 1, 0}};
        int[][] mat = new int[][]{{0, 1}, {1, 0}};
        int celebrity = findCelebrityNormal(mat);
        System.out.println("CelebrityProblem====>" + celebrity);

        celebrity = findCelebrityStackBased(mat);
        System.out.println("findCelebrityStackBased====>" + celebrity);
    }

    private static int findCelebrityStackBased(int[][] mat) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < mat.length; i++) stack.push(i);

        while (stack.size() > 1) {
            int a = stack.pop();
            int b = stack.pop();
            if (mat[a][b] == 1) stack.push(b);
            else stack.push(a);
        }
        int candidate = stack.pop();
        for (int k = 0; k < mat.length; k++) {
            if (k != candidate) {
                if (mat[candidate][k] == 1 || mat[k][candidate] == 0) return -1;
            }
        }
        return candidate;
    }

    private static int findCelebrityNormal(int[][] mat) {
        int i = 0;
        int j = mat.length - 1;

        while (i < j) {
            if (mat[i][j] == 1) i++;
            else j--;
        }
        int candidate = i;
        for (int k = 0; k < mat.length; k++) {
            if (k != candidate) {
                if (mat[candidate][k] == 1 || mat[k][candidate] == 0) return -1;
            }
        }
        return candidate;
    }
}
