package december.binarySearch;

/**
 * You are given a positive integer n. Your task is to find and return its square root.
 * If ‘n’ is not a perfect square, then return the floor value of sqrt(n).
 * Input: N = 36
 * Output: 6
 * Explanation: Square root of 36 is 6.
 * Input: N = 28
 * Output: 5
 * Explanation: Square root of 28 is approximately 5.292. So, the floor value will be 5.
 */
public class FindSQrtUsingBinarySearch {
    public static void main(String[] args) {
        int num = 28;
        int sqrt = findSqrt(num);
        System.out.println("FindSQrtUsingBinarySearch---" + sqrt);
    }

    private static int findSqrt(int num) {
        int low = 1;
        int high = num / 2;
        int res = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (mid * mid <= num) {
                res = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return res;
    }


}
