package december.arrays;

import java.util.Arrays;

public class SortArray0s1s2s {
    public static void main(String[] args) {

        int[] input1 = new int[]{2, 0, 2, 1, 1, 0};
        dutchNationalFlagSolution(input1);

        int[] input2 = new int[]{2, 0, 1};
        dutchNationalFlagSolutionOptimized(input2);
    }

    private static void dutchNationalFlagSolution(int[] nums) {
        int l = 0;
        int h = nums.length - 1;
        while (l < h) {
            if (nums[l] == 0) l++;
            if (nums[h] == 1 || nums[h] == 2) h--;
            if (nums[l] == 1 || nums[l] == 2) {
                int temp = nums[l];
                nums[l] = nums[h];
                nums[h] = temp;
                l++;
                h--;
            }
        }
        h = nums.length - 1;
        while (l < h) {
            if (nums[l] == 1) l++;
            if (nums[h] == 2) h--;
            else {
                int temp = nums[l];
                nums[l] = nums[h];
                nums[h] = temp;
            }
        }
        System.out.println("dutchNationalFlagSolution Sort Array==>" + Arrays.toString(nums));
    }

    private static void dutchNationalFlagSolutionOptimized(int[] nums) {
        int l = 0;
        int mid = 0;
        int h = nums.length - 1;

        while (mid <= h) {
            if (nums[mid] == 0) {
                int temp = nums[mid];
                nums[mid] = nums[l];
                nums[l] = temp;
                l++;
                mid++;
            }
            else if (nums[mid] == 1) mid++;
            else if (nums[mid] == 2) {
                int temp = nums[mid];
                nums[mid] = nums[h];
                nums[h] = temp;
                h--;
            }
        }
        System.out.println("dutchNationalFlagSolution Optimal Sort Array==>" + Arrays.toString(nums));
    }
}
