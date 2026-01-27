package december.binarySearch;

import java.util.Arrays;

/**
 * Given an integer array arr of size N, sorted in ascending order (may contain duplicate values) and a target value k.
 * Now the array is rotated at some pivot point unknown to you.
 * Return True if k is present and otherwise, return False.
 * Example 1:
 * Input Format: arr = [7, 8, 1, 2, 3, 3, 3, 4, 5, 6], k = 3
 * Result: True
 * Explanation: The element 3 is present in the array. So, the answer is True.
 * <p>
 * Example 2:
 * Input Format: arr = [7, 8, 1, 2, 3, 3, 3, 4, 5, 6], k = 10
 * Result: False
 * Explanation: The element 10 is not present in the array. So, the answer is False.
 */
public class SearchRotatedSortedArrayWithDuplicates {

    public static void main(String[] args) {
        int[] nums = new int[]{7, 8, 1, 2, 3, 3, 3, 4, 5, 6};
        //int key = 3;
        int key = 10;
        System.out.println("For Input ===>" + Arrays.toString(nums));
        boolean rotateIndex = findInRotatedSortedArrayWithDuplicates(nums, key);
        System.out.println("findInRotatedSortedArray===>" + rotateIndex);
    }

    private static boolean findInRotatedSortedArrayWithDuplicates(int[] nums, int key) {
        int low = 0;
        int high = nums.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[mid] == key) return true;
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low++;
                high--;
            }
            if (nums[low] < nums[mid]) { // left part sort
                if (nums[low] <= key && key < nums[mid]) high = mid - 1;
                else low = mid + 1;
            } else { // right part sorted
                if (nums[mid] < key && key <= nums[high]) low = mid + 1;
                else high = mid - 1;
            }

        }
        return false;
    }
}
