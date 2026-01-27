package arrays;

import java.util.Arrays;

/**
 * Example 1 :
 * <p>
 * Input format: Arr[] = {1,3,2}
 * Output: Arr[] = {2,1,3}
 * Explanation: All permutations of {1,2,3} are {{1,2,3} , {1,3,2}, {2,13} , {2,3,1} , {3,1,2} , {3,2,1}}. So, the next permutation just after {1,3,2} is {2,1,3}.
 * Example 2:
 * <p>
 * Input format: Arr[] = {3,2,1}
 * Output: Arr[] = {1,2,3}
 * Explanation: As we see all permutations of {1,2,3}, we find {3,2,1} at the last position. So, we have to return the topmost permutation.
 */
public class NextPermutation {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 2};
        //int[] nums = new int[]{3, 2, 1};

        int[] nextPermutation = nextPermutation(nums);
        System.out.println("NextPermutation ==>" + Arrays.toString(nextPermutation));
    }

    private static int[] nextPermutation(int[] a) {
        int index = -1;
        for (int i = a.length - 1; i >= 1; i--) {
            if (a[i - 1] < a[i]) {
                index = i - 1;
                break;
            }
        }

        if (index == -1) {
            reverse(a, 0, a.length - 1);
            return a;
        }
        for (int i = index + 1; i < a.length - 1; i++) {
            if (a[i] > a[i + 1]) {
                int temp = a[i + 1];
                a[i + 1] = a[index];
                a[index] = temp;
                break;
            }
        }

        reverse(a, index + 1, a.length - 1);
        return a;
    }

    private static void reverse(int[] arr, int start, int endIndex) {
        while (start < endIndex) {
            int tmp = arr[start];
            arr[start] = arr[endIndex];
            arr[endIndex] = tmp;
            start++;
            endIndex--;
        }
    }

}
