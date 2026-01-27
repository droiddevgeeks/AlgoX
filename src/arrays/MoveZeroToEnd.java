package arrays;

import java.util.Arrays;

/**
 * Example 1:
 * Input: 1 ,0 ,2 ,3 ,0 ,4 ,0 ,1
 * Output: 1 ,2 ,3 ,4 ,1 ,0 ,0 ,0
 * Explanation: All the zeros are moved to the end and non-negative integers are moved to front by maintaining order
 * <p>
 * Example 2:
 * Input: 1,2,0,1,0,4,0
 * Output: 1,2,1,4,0,0,0
 * Explanation: All the zeros are moved to the end and non-negative integers are moved to front by maintaining order
 */
public class MoveZeroToEnd {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,0,1,0,4,0};
        //int[] nums = new int[]{1, 0, 2, 3, 0, 4, 0, 1};

        moveZerosToEnd(nums);
        System.out.println("moveZerosToEnd ==>" + Arrays.toString(nums));
    }

    private static void moveZerosToEnd(int[] a) {
        int currIndex = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                currIndex = i;
                break;
            }
        }
        if (currIndex == -1) return;
        for (int i = currIndex + 1; i < a.length; i++) {
            if (a[i] != 0) {
                int temp = a[currIndex];
                a[currIndex] = a[i];
                a[i] = temp;
                currIndex++;
            }
        }
    }
}
