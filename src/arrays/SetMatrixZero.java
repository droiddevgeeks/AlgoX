package arrays;

import java.util.Arrays;

/**
 * Examples 1:
 * <p>
 * Input: matrix=[[1,1,1],[1,0,1],[1,1,1]]
 * <p>
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 * <p>
 * Explanation: Since matrix[2][2]=0.Therefore the 2nd column and 2nd row wil be set to 0.
 * <p>
 * Input: matrix=[[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * <p>
 * Output:[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * <p>
 * Explanation:Since matrix[0][0]=0 and matrix[0][3]=0. Therefore 1st row, 1st column and 4th column will be set to 0
 */
public class SetMatrixZero {

    public static void main(String[] args) {
        int[][] nums = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        System.out.println("calling Brute force");
        setZeroBruteForce(nums);

        System.out.println("calling Optimal");
        int[][] num = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        setZeroOptimal(num);
    }

    private static void setZeroBruteForce(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == 0) {
                    for (int k = 0; k < a[0].length; k++) {
                        if (a[i][k] != 0) a[i][k] = -1;
                    }
                    for (int k = 0; k < a.length; k++) {
                        if (a[k][j] != 0) a[k][j] = -1;
                    }
                }
            }
        }

        System.out.println("After Setting -1");

        for (int[] d : a) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(d[j] + "\t");
            }
            System.out.print("\n");
        }

        for (int[] d : a) {
            for (int j = 0; j < a[0].length; j++) {
                if (d[j] == -1) d[j] = 0;
            }
        }

        System.out.println("Final Setting 0");

        for (int[] d : a) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(d[j] + "\t");
            }
            System.out.print("\n");
        }
    }

    private static void setZeroOptimal(int[][] a) {

        int[] row = new int[a.length];
        int[] col = new int[a[0].length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == 0) {
                    row[i] = -1;
                    col[j] = -1;
                }
            }
        }

        System.out.println("After Setting -1");

        System.out.println("Row Array" + Arrays.toString(row));
        System.out.println("Col Array" + Arrays.toString(col));

        for (int i = 0; i < row.length; i++) {
            if (row[i] == -1) {
                for (int j = 0; j < a[0].length; j++) {
                    a[i][j] = 0;
                }
            }
        }

        System.out.println("After Setting row to 0");
        for (int[] d : a) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(d[j] + "\t");
            }
            System.out.print("\n");
        }


        for (int i = 0; i < col.length; i++) {
            if (col[i] == -1) {
                for (int j = 0; j < a.length; j++) {
                    a[j][i] = 0;
                }
            }
        }

        System.out.println("After Setting Column to 0");
        for (int[] d : a) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(d[j] + "\t");
            }
            System.out.print("\n");
        }

    }
}
