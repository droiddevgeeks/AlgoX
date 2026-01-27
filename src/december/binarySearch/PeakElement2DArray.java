package december.binarySearch;

/**
 * Given a 0-indexed n x m matrix mat where no two adjacent cells are equal,
 * find any peak element mat[i][j] and return the array [i, j].
 * A peak element in a 2D grid is an element that is strictly greater than all of its adjacent neighbours to the left, right, top, and bottom.
 * Assume that the entire matrix is surrounded by an outer perimeter with the value -1 in each cell.
 * <p>
 * Note: As there can be many peak values, 1 is given as output if the returned index is a peak number, otherwise 0.
 * Example 1:
 * Input: mat = [[5, 10, 8], [4, 25, 7], [3, 9, 6]]
 * Output: [1, 1]
 * Explanation: The value at index [1, 1] is 25, which is a peak because all its neighbors (10, 7, 4, 9) are smaller.
 * <p>
 * Example 2:
 * Input: mat = [[1, 2, 3], [6, 5, 4], [7, 8, 9]] Output: [2, 2]
 * Explanation: The value at index [2, 2] is 9, which is a peak as it is greater than its neighbors (8, 4).
 */
public class PeakElement2DArray {
    public static void main(String[] args) {
        //int[][] mat = new int[][]{{5, 10, 8}, {4, 25, 7}, {3, 9, 6}};
        int[][] mat = new int[][]{{1, 2, 3}, {6, 5, 4}, {7, 8, 9}};
        int col = mat[0].length;

        Solution solution = findPeakElement(mat, col);
        System.out.println("PeakElement2DArray---{" + solution.row + "," + solution.col + "}");
    }

    private static Solution findPeakElement(int[][] mat, int col) {
        int low = 0;
        int high = col - 1;
        Solution solution = new Solution(-1, -1);

        while (low <= high) {
            int mid = (low + high) / 2;
            int row = findRowForMaxElementInColumn(mat, mid);

            int left = mid - 1 >= 0 ? mat[row][mid - 1] : -1;
            int right = mid + 1 < col ? mat[row][mid + 1] : -1;

            if (left < mat[row][mid] && mat[row][mid] > right) {
                solution.row = row;
                solution.col = mid;
                break;
            } else if (left > mat[row][mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return solution;
    }

    private static int findRowForMaxElementInColumn(int[][] mat, int col) {
        int max = Integer.MIN_VALUE;
        int row = 0;
        int maxElementRow = 0;
        for (; row < mat.length; row++) {
            if (mat[row][col] > max) {
                max = mat[row][col];
                maxElementRow = row;
            }
        }
        return maxElementRow;
    }
}

class Solution {
    int row;
    int col;

    public Solution(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
