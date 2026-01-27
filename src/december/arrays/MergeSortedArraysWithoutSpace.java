package december.arrays;

import java.util.Arrays;

/**
 * Input : nums1 = [-5, -2, 4, 5, 0, 0, 0], nums2 = [-3, 1, 8]
 * Output : [-5, -3, -2, 1, 4, 5, 8]
 * Explanation : The merged array is: [-5, -3, -2, 1, 4, 5, 8], where [-5, -2, 4, 5] are from nums1 and [-3, 1, 8] are from nums2
 * Input : nums1 = [0, 2, 7, 8, 0, 0, 0], nums2 = [-7, -3, -1]
 * Output :  [-7, -3, -1, 0, 2, 7, 8]
 * Explanation :  The merged array is: [-7, -3, -1, 0, 2, 7, 8], where [0, 2, 7, 8] are from nums1 and [-7, -3, -1] are from nums2
 */
public class MergeSortedArraysWithoutSpace {
    public static void main(String[] args) {
        //int[] nums1 = {-5, -2, 4, 5, 0, 0, 0}, nums2 = {-3, 1, 8};
        int[] nums1 = {0, 2, 7, 8, 0, 0, 0}, nums2 = {-7, -3, -1};

        int i = nums1.length - 1; // last index in num1
        int j = nums2.length - 1; // last index in num2
        int k = i - j - 1; // index after which 0's are present

        while (j >= 0 && k >= 0) {
            if (nums2[j] > nums1[k]) {
                nums1[i--] = nums2[j--];
            } else if (nums2[j] < nums1[k]) {
                nums1[i--] = nums1[k--];
            }
        }
        while (j>=0){
            nums1[i--] = nums2[j--];
        }

        System.out.println("Merged Array==>"+ Arrays.toString(nums1));
    }
}
