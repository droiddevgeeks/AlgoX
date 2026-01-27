package december.binarySearch;

import java.util.Arrays;

/**
 * Given an integer array nums, sorted in ascending order (with distinct values) and a target value k.
 * The array is rotated at some pivot point that is unknown.
 * Find the index at which k is present and if k is not present return -1.
 * Input:nums = [4, 5, 6, 7, 0, 1, 2], k = 0
 * Output :4
 * Explanation : Here, the target is 0. We can see that 0 is present in the given rotated sorted array, nums. Thus, we get output as 4, which is the index at which 0 is present in the array.
 * <p>
 * Input: nums = [4, 5, 6, 7, 0, 1, 2], k = 3
 * Output :-1
 * Explanation :Here, the target is 3. Since 3 is not present in the given rotated sorted array. Thus, we get the output as -1.
 */
public class SearchRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int key = 3;
        //int key = 3;
        System.out.println("For Input ===>" + Arrays.toString(nums));
        int rotateIndex = findInRotatedSortedArray(nums, key);
        System.out.println("findInRotatedSortedArray===>" + rotateIndex);
    }

    private static int findInRotatedSortedArray(int[] nums, int key) {
        int low = 0;
        int high = nums.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[mid] == key) return mid;
            if (nums[low] < nums[mid]) { // left part sort
                if (nums[low] <= key && key < nums[mid]) high = mid - 1;
                else low = mid + 1;
            } else { // right part sorted
                if (nums[mid] < key && key <= nums[high]) low = mid + 1;
                else high = mid - 1;
            }

        }
        return -1;
    }
}
