package arrays;

/**
 * Example 1:
 * Input: Matrix[][] = { { 1, 2, 3, 4 },
 * { 5, 6, 7, 8 },
 * { 9, 10, 11, 12 },
 * { 13, 14, 15, 16 } }
 * <p>
 * Outhput: 1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10.
 * Explanation: The output of matrix in spiral form.
 * <p>
 * Example 2:
 * Input: Matrix[][] = { { 1, 2, 3 },
 * { 4, 5, 6 },
 * { 7, 8, 9 } }
 * <p>
 * Output: 1, 2, 3, 6, 9, 8, 7, 4, 5.
 * Explanation: The output of matrix in spiral form.
 */
public class PrintMatrixSpiral {

    public static void main(String[] args) {
//        int[][] nums = new int[][]{
//                {1, 2, 3, 4},
//                {5, 6, 7, 8},
//                {9, 10, 11, 12},
//                {13, 14, 15, 16}
//        };

        int[][] nums = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        printSpiralForm(nums);
    }

    private static void printSpiralForm(int[][] a) {
        int bottom = a.length-1;
        int right = a[0].length-1;
        int top = 0;
        int left = 0;
        while (top <= bottom && left <=right) {

            //First row
            for (int i = left; i <= right ; i++) {
                System.out.print(a[top][i] + " ");
            }
            top++;

            //Last Column
            for (int i = top; i <= bottom ; i++) {
                System.out.print(a[i][right] + " ");
            }

            right--;


            //Last Row
            for (int i = right; i>=left ; i--) {
                System.out.print(a[bottom][i] + " ");
            }
            bottom--;

            //First Column
            for (int i = bottom; i >=top ; i--) {
                System.out.print(a[i][left] + " ");
            }
            left++;
        }
    }
}
