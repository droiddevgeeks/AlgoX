package december.arrays;

/**
 * Problem Statement: Given an integer array nums of size n, return the majority element of the array.
 * The majority element of an array is an element that appears more than n/2 times in the array.
 * The array is guaranteed to have a majority element.
 * Input:
 * nums = [7, 0, 0, 1, 7, 7, 2, 7, 7]
 * Output:
 * 7
 * Explanation:
 * The number 7 appears 5 times in the 9-sized array, making it the most frequent element.
 * <p>
 * Example 2:
 * Input:
 * nums = [1, 1, 1, 2, 1, 2]
 * Output:
 * 1
 * Explanation:
 * The number 1 appears 4 times in the 6-sized array, making it the most frequent element.
 */
public class MajorityElement {

    public static void main(String[] args) {
        int[] nums = {7, 0, 0, 1, 7, 7, 2, 7, 7};
       // int[] nums = {1, 1, 1, 2, 1, 2};
        int res = findMajorityElement(nums);
        System.out.println("MajorityElement===>" + res);
    }

    private static int findMajorityElement(int[] nums) {
        int count = 1;
        int element = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (element == nums[i]) count++;
            if (element != nums[i]) count--;
            if (count == 0) {
                element = nums[i];
                count = 1;
            }
        }
        return element;
    }
}
