package arrays;

/**
 * Example 1:
 * Input Format: N = 5, array[] = {1,2,2,3,2}
 * Result: 2
 * Explanation: Here we can see that the Count(1) = 1, Count(2) = 3 and Count(3) = 1.Therefore, the count of 2 is greater than N/3 times. Hence, 2 is the answer.
 * <p>
 * Example 2:
 * Input Format:  N = 6, array[] = {11,33,33,11,33,11}
 * Result: 11 33
 * Explanation: Here we can see that the Count(11) = 3 and Count(33) = 3. Therefore, the count of both 11 and 33 is greater than N/3 times. Hence, 11 and 33 is the answer.
 */
public class MajorityElementExtended {
    public static void main(String[] args) {
        //int[] nums = new int[]{1, 2, 2, 3, 2};
        int[] nums = new int[]{11,33,33,11,33,11};

        int majorityElement = majorityElement(nums);
        System.out.println("MajorityElement==>" + majorityElement);

    }


    private static int majorityElement(int[] nums) {
        int count1 = 0;
        int count2 = 0;
        int element1 = Integer.MIN_VALUE;
        int element2 = Integer.MIN_VALUE;
        for (int num : nums) {
            if (count1 == 0 && num != element2) {
                count1 += 1;
                element1 = num;
            } else if (count2 == 0 && num != element1) {
                count2 += 1;
                element2 = num;
            } else if (num == element1) count1++;
            else if (num == element2) count2++;
            else {
                count1--;
                count2--;
            }
        }

        int c1 = 0;
        int c2 = 0;
        for (int n : nums) {
            if (n == element1) c1++;
            if (n == element2) c2++;
        }

        if (c1 >= nums.length / 3) return element1;
        if (c2 >= nums.length / 3) return element2;
        return -1;
    }
}
