package arrays;

/**
 * Input : nums = [1, 2, 3, 4, 5]
 *
 * Output : true
 *
 * Explanation : For all i (1 <= i <= 4) it holds nums[i] <= nums[i+1], hence it is sorted and we return true.
 *
 * Input : nums = [1, 2, 1, 4, 5]
 *
 * Output : false
 *
 * Explanation : For i == 2 it does not hold nums[i] <= nums[i+1], hence it is not sorted and we return false.
 */
public class CheckArraySort {

    public static void main(String[] args) {
        //int[] nums =  new int[]{1, 2, 3, 4, 5};
        int[] nums =  new int[]{1, 2, 1, 4, 5};

        boolean isSorted = checkSorted(nums);
        System.out.println("Array Sorted is ==>" + isSorted);
    }

    private static boolean checkSorted(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if(nums[i-1]> nums[i])return  false;
        }
        return true;
    }
}
