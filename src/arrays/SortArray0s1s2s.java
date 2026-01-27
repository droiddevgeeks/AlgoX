package arrays;

import java.util.Arrays;
import java.util.LinkedHashMap;

/**
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * <p>
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 * <p>
 * Input: nums = [0]
 * Output: [0]
 */
public class SortArray0s1s2s {
    public static void main(String[] args) {
        //int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        int[] nums = new int[]{2, 0, 1};
        sortArrayBruteForce(nums);
        dutchNationalFlagSolution(nums);
        dutchNationalFlagSolutionOptimized(nums);
    }

    private static void sortArrayBruteForce(int[] a) {
        int[] copyA = Arrays.copyOf(a, a.length);
        LinkedHashMap<Integer, Integer> hm = new LinkedHashMap<>();

        for (int n : copyA) {
            if (hm.containsKey(n)) {
                int count = hm.get(n);
                hm.put(n, ++count);
            } else {
                hm.put(n, 1);
            }
        }
        int zeroCount = hm.get(0);
        Arrays.fill(copyA, 0, zeroCount, 0);
        int oneCount = hm.get(1);
        Arrays.fill(copyA, zeroCount, zeroCount + oneCount, 1);
        int twoCount = hm.get(2);
        Arrays.fill(copyA, zeroCount + oneCount, zeroCount + oneCount + twoCount, 2);
        System.out.println("Sort Array==>" + Arrays.toString(copyA));
    }

    private static void dutchNationalFlagSolution(int[] a) {
        int[] copyA = Arrays.copyOf(a, a.length);
        int left = 0;
        int right = copyA.length - 1;
        while (left < right) {
            if (copyA[left] == 0) {
                left++;
            }
            if (copyA[right] == 1 || copyA[right] == 2) {
                right--;
            }

            if ((copyA[left] == 1 || copyA[left] == 2) && copyA[right] == 0) {
                int temp = copyA[left];
                copyA[left] = copyA[right];
                copyA[right] = temp;

                left++;
                right--;
            }
        }

        right = copyA.length - 1;
        while (left < right) {
            if (copyA[left] == 1) {
                left++;
            } else if (copyA[right] == 2) {
                right--;
            } else {
                int temp = copyA[left];
                copyA[left] = copyA[right];
                copyA[right] = temp;
                left++;
                right--;
            }
        }
        System.out.println("dutchNationalFlagSolution Sort Array==>" + Arrays.toString(copyA));
    }

    private static void dutchNationalFlagSolutionOptimized(int[] a) {
        int[] copyA = Arrays.copyOf(a, a.length);
        int left = 0;
        int right = copyA.length - 1;
        int mid = 0;
        while (mid <= right) {
            if (copyA[mid] == 0) {
                int temp = copyA[left];
                copyA[left] = copyA[mid];
                copyA[mid] = temp;
                left++;
                mid++;
            } else if (copyA[mid] == 1) {
                mid++;
            } else {
                int temp = copyA[mid];
                copyA[mid] = copyA[right];
                copyA[right] = temp;
                right--;
            }
        }

        System.out.println("dutchNationalFlagSolution Optimal Sort Array==>" + Arrays.toString(copyA));
    }

}
