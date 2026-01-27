package december.binarySearch;

import java.util.Arrays;

/**
 * Given an array of N integers.
 * Every number in the array except one appears twice. Find the single number in the array.
 * Input : arr[] = {1,1,2,2,3,3,4,5,5,6,6}
 * Output: 4
 * Explanation: Only the number 4 appears once in the array.
 * <p>
 * Input: arr[] = {1,1,3,5,5}
 * Output : 3
 * Explanation: Only the number 3 appears once in the array.
 */
public class SearchSingleElement {
    public static void main(String[] args) {
        //int[] nums1 = new int[]{1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6};
        int[] nums1 = new int[]{1, 1, 3, 5, 5};


        System.out.println("Input Array===>" + Arrays.toString(nums1));
        int singleElement = findSingleElementInSortedArray(nums1);
        System.out.println("SearchSingleElement===>" + singleElement);
    }

    private static int findSingleElementInSortedArray(int[] nums) {
        int len = nums.length;
        if (len == 0) return -1;
        if (len == 1 || nums[0] != nums[1]) return nums[0];
        if (nums[len - 1] != nums[len - 2]) return nums[len - 1];
        int low = 1;
        int high = len - 2;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid - 1] != nums[mid] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }
            if ((mid % 2 == 1 && nums[mid] == nums[mid - 1]) || (mid % 2 == 0 && nums[mid] == nums[mid + 1]))
                low = mid + 1;
            else
                high = mid - 1;
        }
        return -1;
    }
}
