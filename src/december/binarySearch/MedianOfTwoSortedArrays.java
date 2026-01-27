package december.binarySearch;

import java.util.Arrays;

/**
 * Given two sorted arrays arr1 and arr2 of size m and n respectively, return the median of the two sorted arrays.
 * The median is defined as the middle value of a sorted list of numbers.
 * In case the length of the list is even, the median is the average of the two middle elements.
 * Input: n1 = 3, arr1[] = {2,4,6}, n2 = 3, arr2[] = {1,3,5} , Output: 3.5
 * Explanation:
 * The array after merging 'a' and 'b' will be {1, 2, 3, 4, 5, 6}.
 * As the length of the merged list is even, the median is the average of the two middle elements.
 * Here two medians are 3 and 4. So the median will be the average of 3 and 4, which is 3.5.
 * Input: n1 = 3, arr1[] = {2,4,6}, n2 = 2, arr2[] = {1,3} , Output: 3
 * Explanation: The array after merging 'a' and 'b' will be { 1, 2, 3, 4, 6 }. The median is 3.
 */
public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {

        int[] arr1 = new int[]{2, 4, 6};
        int[] arr2 = new int[]{1, 3};

        System.out.println("Arr1--" + Arrays.toString(arr1));
        System.out.println("Arr2--" + Arrays.toString(arr2));
        double median = findMedian(arr1, arr2);

        System.out.println("MedianOfTwoSortedArrays--" + median);
    }

    private static double findMedian(int[] arr1, int[] arr2) {
        if (arr1.length > arr2.length) return findMedian(arr2, arr1);

        int m = arr1.length;
        int n = arr2.length;
        int low = 0;
        int high = m; // smaller len.

        while (low <= high) {
            int cut1 = (low + high) / 2;
            int cut2 = (m + n + 1) / 2 - cut1;

            int left1 = (cut1 == 0) ? Integer.MIN_VALUE : arr1[cut1-1];
            int left2 = (cut2 == 0) ? Integer.MIN_VALUE : arr2[cut2-1];

            int right1 = cut1 == m ? Integer.MAX_VALUE : arr1[cut1];
            int right2 = cut2 == n ? Integer.MAX_VALUE : arr2[cut2];

            if (left1 <= right2 && left2 <= right1) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
                } else {
                    return Math.max(left1, left2);
                }
            } else if (left1 > right2) {
                high = cut1 - 1;
            } else {
                low = cut1 + 1;
            }
        }
        return 0.0;
    }
}
