package arrays;

/**
 * Example 1:
 * Input: [1,2,4,7,7,5]
 * Output: Second Smallest : 2
 * Second Largest : 5
 * Explanation: The elements are as follows 1,2,3,5,7,7 and hence second largest of these is 5 and second smallest is 2
 * <p>
 * Example 2:
 * Input: [1]
 * Output: Second Smallest : -1
 * Second Largest : -1
 * Explanation: Since there is only one element in the array, it is the largest and smallest element present in the array. There is no second largest or second smallest element present.
 */
public class SecondLargestAndSmallest {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 4, 7, 7, 5};
        //int[] nums = new int[]{0, 5, 2, 8, 1, 9, 0};
        //int[] nums = new int[]{1};

        int secondSmallest = secondSmallest(nums);
        int secondLargest = secondLargest(nums);
        System.out.println("SecondSmallest ==>" + secondSmallest);
        System.out.println("SecondLargest ==>" + secondLargest);
    }

    private static int secondSmallest(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return -1;
        int small = Integer.MAX_VALUE;
        int secondSmall = Integer.MAX_VALUE;

        if (nums[0] > nums[1]) {
            small = nums[1];
            secondSmall = nums[0];
        } else {
            secondSmall = nums[1];
            small = nums[0];
        }
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] < small) {
                secondSmall = small;
                small = nums[i];
            }
            if(nums[i]< secondSmall && nums[i]> small){
                secondSmall = nums[i];
            }
        }
        return secondSmall;
    }

    private static int secondLargest(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return -1;
        int large = Integer.MIN_VALUE;
        int secondLarge = Integer.MIN_VALUE;
        if (nums[0] > nums[1]) {
            large = nums[0];
            secondLarge = nums[1];
        } else {
            large = nums[1];
            secondLarge = nums[0];
        }
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] > large) {
                secondLarge = large;
                large = nums[i];
            }
            if (nums[i] > secondLarge && nums[i] < large) {
                secondLarge = nums[i];
            }
        }
        return secondLarge;
    }
}
