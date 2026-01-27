package december.binarySearch;

import java.util.Arrays;

/**
 * You are given a sorted array containing N integers and a number X, you have to find the occurrences of X in the given array.
 * Example 1:
 * Input:
 * N = 7,  X = 3 , array[] = {2, 2 , 3 , 3 , 3 , 3 , 4}
 * Output : 4
 * Explanation: 3 is occurring 4 times in the given array so it is our answer.
 * <p>
 * Example 2:
 * Input:
 * N = 8,  X = 2 , array[] = {1, 1, 2, 2, 2, 2, 2, 3}
 * Output : 5
 * Explanation: 2 is occurring 5 times in the given array so it is our answer.
 */
public class FistAndLastOccurrenceSortedArray {

    public static void main(String[] args) {
        //int[] nums = new int[]{2, 2, 3, 3, 3, 3, 4};
        //int key = 3;
        int[] nums = new int[]{1, 1, 2, 2, 2, 2, 2, 3};
        int key = 2;
        System.out.println("For Input ===>" + Arrays.toString(nums));
        int firstIndex = findFirstIndexOfTarget(nums, key);
        System.out.println("FistOccurrenceSortedArray===>" + firstIndex);
        int lastIndex = findLastIndexOfTarget(nums, key);
        System.out.println("LastOccurrenceSortedArray===>" + lastIndex);

        System.out.println("Total Count===>" + (lastIndex - firstIndex + 1));
    }

    private static int findFirstIndexOfTarget(int[] nums, int key) {
        int low = 0;
        int high = nums.length - 1;
        int mid = 0;
        int firstIndex = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (nums[mid] == key) {
                firstIndex = mid;
                high = mid - 1;
            } else if (nums[mid] < key) low = mid + 1;
            else high = mid - 1;
        }
        return firstIndex;
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
