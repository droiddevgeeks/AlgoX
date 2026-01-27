package december.arrays;

import java.util.Arrays;

/**
 * You are given an array of integers, your task is to move all the zeros in the array to the end of the array
 * and move non-negative integers to the front by maintaining their order.
 * Input: 1 ,0 ,2 ,3 ,0 ,4 ,0 ,1
 * Output: 1 ,2 ,3 ,4 ,1 ,0 ,0 ,0
 * Explanation: All the zeros are moved to the end and non-negative integers are moved to front by maintaining order
 * Input : 1,2,0,1,0,4,0
 * Output: 1,2,1,4,0,0,0
 * Explanation : All the zeros are moved to the end and non-negative integers are moved to front by maintaining order
 */
public class MoveZerosEnd {
    public static void main(String[] args) {

        int[] input = {1, 0, 2, 3, 0, 4, 0, 1};
        //int[] input = {1, 2, 0, 1, 0, 4, 0};

        moveAllZerosToEnd(input);
        System.out.println(Arrays.toString(input));
    }

    private static void moveAllZerosToEnd(int[] input) {
        int firstZeroIndex = -1;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == 0) {
                firstZeroIndex = i;
                break;
            }
        }

        for (int i = firstZeroIndex + 1; i < input.length; i++) {
            if (input[i] != 0) {
                int temp = input[i];
                input[i] = input[firstZeroIndex];
                input[firstZeroIndex] = temp;
                firstZeroIndex++;
            }
        }
    }
}
