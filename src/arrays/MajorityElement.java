package arrays;

/**
 * Example 1:
 * Input Format: N = 3, nums[] = {3,2,3}
 * Result: 3
 * Explanation: When we just count the occurrences of each number and compare with half of the size of the array, you will get 3 for the above solution.
 * <p>
 * Example 2:
 * Input Format:  N = 7, nums[] = {2,2,1,1,1,2,2}
 * <p>
 * Result: 2
 * <p>
 * Explanation: After counting the number of times each element appears and comparing it with half of array size, we get 2 as result.
 * <p>
 * Example 3:
 * Input Format:  N = 10, nums[] = {4,4,2,4,3,4,4,3,2,4}
 * <p>
 * Result: 4
 */
public class MajorityElement {
    public static void main(String[] args) {
        int[] nums = new int[]{4,4,2,4,3,4,4,3,2,4};
        //int[] nums = new int[]{3, 2, 3};
        //int[] nums = new int[]{2, 2, 1, 1, 1, 2, 2};

        int majorityElement = majorityElement(nums);
        System.out.println("MajorityElement==>" + majorityElement);

    }


    private static int majorityElement(int[] nums) {
        int count = 0;
        int element = 0;
        for (int num : nums) {
            if (count == 0) {
                count = 1;
                element = num;
            } else if (element == num) count++;
            else count--;
        }

        int c1 = 0;
        for (int n : nums) {
            if (n == element) c1++;
        }

        if (c1 > nums.length / 2) return element;
        return -1;
    }
}
