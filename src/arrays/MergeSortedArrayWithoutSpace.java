package arrays;

import java.util.Arrays;

/**
 * Example 1:
 * <p>
 * Input:
 * n = 4, arr1[] = [1 4 8 10]
 * m = 5, arr2[] = [2 3 9]
 * <p>
 * Output:
 * arr1[] = [1 2 3 4]
 * arr2[] = [8 9 10]
 * <p>
 * Explanation:
 * After merging the two non-decreasing arrays, we get, 1,2,3,4,8,9,10.
 * <p>
 * Example2:
 * <p>
 * Input:
 * n = 4, arr1[] = [1 3 5 7]
 * m = 5, arr2[] = [0 2 6 8 9]
 * <p>
 * Output:
 * arr1[] = [0 1 2 3]
 * arr2[] = [5 6 7 8 9]
 * <p>
 * Explanation:
 * After merging the two non-decreasing arrays, we get, 0 1 2 3 5 6 7 8 9.
 */
public class MergeSortedArrayWithoutSpace {

    public static void main(String[] args) {
        int[] a = new int[]{1, 4, 8, 10};
        int[] b = new int[]{2, 3, 9};

        mergeSortedArrayBruteforce(a, b);

        int[] c = new int[]{1, 3, 5, 7};
        int[] d = new int[]{0, 2, 6, 8, 9};

        //mergeSortedArrayBetter(c, d);
        mergeSortedArrayOptimal(c, d);
    }


    //Using Space
    private static void mergeSortedArrayBruteforce(int[] a, int[] b) {
        int lenA = a.length;
        int lenB = b.length;

        int[] res = new int[lenA + lenB];

        int l = 0;
        int r = 0;
        int k = 0;
        while (l < lenA && r < lenB) {
            if (a[l] < b[r]) {
                res[k++] = a[l];
                l++;
            } else if (a[l] > b[r]) {
                res[k++] = b[r];
                r++;
            } else {
                l++;
                r++;
            }
        }

        while (l < lenA) {
            res[k++] = a[l++];
        }

        while (r < lenB) {
            res[k++] = b[r++];
        }

        l = 0;
        r = 0;
        for (int i = 0; i < res.length; i++) {
            if (i < lenA) {
                a[l++] = res[i];
            } else if (i < lenA + lenB) {
                b[r++] = res[i];
            }
        }
        System.out.println("Array Res ==>" + Arrays.toString(res));
        System.out.println("Array A ==>" + Arrays.toString(a));
        System.out.println("Array B ==>" + Arrays.toString(b));
    }

    // Without Space but with sorting
    private static void mergeSortedArrayBetter(int[] a, int[] b) {
        int lenA = a.length;
        int lenB = b.length;

        int l = lenA - 1;
        int r = 0;

        while (l >= 0 && r < lenB) {
            if (a[l] > b[r]) {
                int temp = a[l];
                a[l] = b[r];
                b[r] = temp;
                l--;
                r++;
            } else break;
        }

        System.out.println("Array A ==>" + Arrays.toString(a));
        System.out.println("Array B ==>" + Arrays.toString(b));

        System.out.println("sort");
        Arrays.sort(a);
        Arrays.sort(b);

        System.out.println("After sort");

        System.out.println("Array A ==>" + Arrays.toString(a));
        System.out.println("Array B ==>" + Arrays.toString(b));
    }

    //Gap method approach
    private static void mergeSortedArrayOptimal(int[] a, int[] b) {
        int lenA = a.length;
        int lenB = b.length;

        int len = (lenA + lenB);
        int gap = len / 2 + len % 2;

        while (gap > 0) {
            int left = 0;
            int right = left + gap;
            while (right < len) {
                // In array A and in Array B
                if (left < lenA && right >= lenA) swapIfGreater(a, b, left, right - lenA);
                    // In array B both
                else if (left >= lenA) swapIfGreater(b, b, left - lenA, right - lenA);
                    // In array A both
                else swapIfGreater(a, a, left, right);

                left++;
                right++;
            }

            if (gap == 1) break;
            gap = gap / 2 + gap % 2;
        }

        System.out.println("Array A==>" + Arrays.toString(a));
        System.out.println("Array B==>" + Arrays.toString(b));
    }

    private static void swapIfGreater(int[] a, int[] b, int l, int r) {
        if (a[l] > b[r]) {
            int temp = a[l];
            a[l] = b[r];
            b[r] = temp;
        }
    }
}
