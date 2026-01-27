package december.binarySearch;

import java.util.Arrays;

/**
 * Given a sorted array of N integers, write a program to find the index of the last occurrence of the target key.
 * If the target is not found then return -1. Note: Consider 0 based indexing
 * Example 1:
 * Input:
 * N = 7, target = 13, array[] = {3, 4, 13, 13, 13, 20, 40}
 * Output:
 * 4
 * Explanation:
 * The target value 13 appears for the first time at index number 2 in the array.
 * <p>
 * Example 2:
 * Input:
 * N = 7, target = 60, array[] = {3, 4, 13, 13, 13, 20, 40}
 * Output:
 * -1
 * Explanation:
 * Target value 60 is not present in the array, so the output is -1.
 */
public class LastOccurrenceSortedArray {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 4, 13, 13, 13, 20, 40};
        int key = 13;
        //int key = 60;
        System.out.println("For Input ===>" + Arrays.toString(nums));
        int lastIndex = findLastIndexOfTarget(nums, key);
        System.out.println("LastOccurrenceSortedArray===>" + lastIndex);
    }

    private static int findLastIndexOfTarget(int[] nums, int key) {
        int low = 0;
        int high = nums.length - 1;
        int lastIndex = -1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[mid] == key) {
                lastIndex = mid;
                low = mid + 1;
            } else if (nums[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return lastIndex;
    }
}
