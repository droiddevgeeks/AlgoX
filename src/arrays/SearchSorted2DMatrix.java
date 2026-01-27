package arrays;

/**
 * Input Format: N = 3, M = 4, target = 8,
 * mat[] =
 * 1 2 3 4
 * 5 6 7 8
 * 9 10 11 12
 * Result: true
 * Explanation: The ‘target’  = 8 exists in the 'mat' at index (1, 3).
 * <p>
 * Example 2:
 * Input Format: N = 3, M = 3, target = 78,
 * mat[] =
 * 1 2 4
 * 6 7 8
 * 9 10 34
 * Result: false
 * Explanation: The ‘target' = 78 does not exist in the 'mat'. Therefore in the output, we see 'false'.
 */
public class SearchSorted2DMatrix {

    public static void main(String[] args) {
        int[][] nums = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        int target = 8;

//        int[][] nums = new int[][]{
//                {1, 2, 4},
//                {6, 7, 8},
//                {9, 10, 34}
//        };
//       int target = 78;

        boolean isPresent = isTargetPresent(nums, target);
        boolean isPresentWithBinarySearch = isTargetPresentWithBinarySearch(nums, target);
        System.out.println("SearchSorted2DMatrix ==>" + isPresent);
        System.out.println("SearchSorted2DMatrix isPresentWithBinarySearch ==>" + isPresentWithBinarySearch);
    }

    private static boolean isTargetPresent(int[][] a, int target) {
        int row = 0;
        int col = a[0].length - 1;

        while (row < a.length && col >= 0) {
            if (a[row][col] == target) return true;
            else if (a[row][col] < target) row++;
            else if (a[row][col] > target) col--;
        }
        return false;
    }

    private static boolean isTargetPresentWithBinarySearch(int[][] a, int target) {
        for (int i = 0; i < a.length; i++) {
            if (a[i][0] <= target && a[i][a[0].length - 1] >= target) {
                return doBinarySearch(a[i], target);
            }
        }
        return false;
    }

    private static boolean doBinarySearch(int[] a, int target) {
        int low = 0;
        int high = a.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == target) return true;
            else if (a[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }
}
