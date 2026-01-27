package december.binarySearch;

/**
 * Given a row-wise sorted matrix of size M*N,
 * where M is no. of rows and N is no. of columns, find the median in the given matrix.
 * Note: M*N is odd.
 * Input: M = 3, N = 3, matrix[][] =
 * 1 4 9
 * 2 5 6
 * 3 8 7
 * Output: 5
 * Explanation:
 * If we find the linear sorted array, the array becomes 1 2 3 4 5 6 7 8 9. Therefore, median = 5
 * Input: M = 3, N = 3, matrix[][] =
 * 1 3 8
 * 2 3 4
 * 1 2 5
 * Output: 3
 * Explanation:
 * If we find the linear sorted array, the array becomes 1 1 2 2 3 3 4 5 7 8. Therefore, median = 3.
 */
public class MedianRowWiseSortedMatrix {
    public static void main(String[] args) {
        //int[][] mat = new int[][]{{1, 4, 9}, {2, 5, 6}, {3, 8, 7}};
        int[][] mat = new int[][]{{1, 3, 8}, {2, 3, 4}, {1, 2, 5}};

        int median = findMedianRowWiseSortedMatrix(mat);
        System.out.println("MedianRowWiseSortedMatrix--" + median);
    }

    private static int findMedianRowWiseSortedMatrix(int[][] mat) {
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        int col = mat[0].length;
        int row = mat.length;

        for (int i = 0; i < mat.length; i++) {
            low = Math.min(low, mat[i][0]);
            high = Math.max(high, mat[i][col - 1]);
        }

        int totalElement = row * col;
        int requiredElementsBeforeMedian = (totalElement + 1) / 2;
        while (low < high) {
            int mid = (low + high) / 2;
            int count = 0;
            for (int i = 0; i < row; i++) {
                count += countElementBeforeMid(mat[i], mid);
            }

            if (count < requiredElementsBeforeMedian) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    private static int countElementBeforeMid(int[] row, int targetMedian) {
        int low = 0;
        int high = row.length - 1;
        int count = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (row[mid] <= targetMedian) {
                count++;
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return low;
    }
}
