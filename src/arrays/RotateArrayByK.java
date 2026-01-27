package arrays;

import java.util.Arrays;

/**
 * Example 1:
 * Input: N = 7, array[] = {1,2,3,4,5,6,7} , k=2 , right
 * Output: 6 7 1 2 3 4 5
 * Explanation: array is rotated to right by 2 position .
 * <p>
 * Example 2:
 * Input: N = 6, array[] = {3,7,8,9,10,11} , k=3 , left
 * Output: 9 10 11 3 7 8
 * Explanation: Array is rotated to right by 3 position.
 */
public class RotateArrayByK {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k = 2;
        //int[] nums = new int[]{3,7,8,9,10,11};
        //int k  =3;

        //right
        rotateByK(nums, k);
        System.out.println("rotateByK ==>" + Arrays.toString(nums));
    }

    private static void rotateByK(int[] a, int k) {
        int len = a.length;
        if (len == 0) return;
        k = k % len;
        if (k > len) return;

        int[] temp = new int[k];
        for (int i = len-k; i < len ; i++) {
            temp[i-len+k] = a[i];
        }

        for (int i = len-k-1; i >=0 ; i--) {
            a[i+k] = a[i];
        }

        for (int i = 0; i < k; i++) {
            a[i] = temp[i];
        }
    }
}
