package dp;

import java.util.Arrays;

/**
 * Ninja trains for N days
 * Each day he must choose exactly one of 3 activities
 * (0 = Running, 1 = Fighting, 2 = Learning)
 * Each activity gives some points
 * Constraint:  He cannot repeat the same activity on consecutive days
 * Goal: Maximize total points.
 * Input: matrix = [[10, 40, 70], [20, 50, 80], [30, 60, 90]]
 * Output: 210
 * Input: matrix = [[70, 40, 10], [180, 20, 5], [200, 60, 30]]
 * Output: 290
 */
public class NinjaTraining {
    public static void main(String[] args) {
        int[][] tr = new int[][]{{10, 40, 70}, {20, 50, 80}, {30, 60, 90}};
        //int[][] tr = new int[][]{{70, 40, 10}, {180, 20, 5}, {200, 60, 30}};
        int lastTask = 3;
        int maxPoints = findMaxNinjaPointsRecursion(tr, tr.length - 1, lastTask);
        System.out.println("NinjaTraining--->" + maxPoints);

        maxPoints = findMaxNinjaPointsMemo(tr, tr.length, lastTask);
        System.out.println("NinjaTraining Memo--->" + maxPoints);

        maxPoints = findMaxNinjaPointsTabular(tr, tr.length);
        System.out.println("NinjaTraining Tabular--->" + maxPoints);
    }


    private static int findMaxNinjaPointsRecursion(int[][] tr, int day, int lastTask) {
        int max;
        if (day == 0) {
            max = Integer.MIN_VALUE;
            for (int task = 0; task < 3; task++) {
                if (task != lastTask) {
                    max = Math.max(max, tr[day][task]);
                }
            }
            return max;
        }

        max = Integer.MIN_VALUE;
        for (int task = 0; task < 3; task++) {
            if (task != lastTask) {
                int point = tr[day][task] + findMaxNinjaPointsRecursion(tr, day - 1, task);
                max = Math.max(max, point);
            }
        }
        return max;
    }

    private static int findMaxNinjaPointsMemo(int[][] tr, int day, int lastTask) {
        int[][] memo = new int[day][4];
        for (int[] m : memo) {
            Arrays.fill(m, -1);
        }
        return findMaxNinjaPointsHelper(tr, memo, day - 1, lastTask);
    }

    private static int findMaxNinjaPointsHelper(int[][] tr, int[][] memo, int day, int lastTask) {
        if (memo[day][lastTask] != -1) return memo[day][lastTask];
        int max;
        if (day == 0) {
            max = Integer.MIN_VALUE;
            for (int task = 0; task < 3; task++) {
                if (task != lastTask) {
                    max = Math.max(max, tr[day][task]);
                }
            }
            return memo[day][lastTask] = max;
        }

        max = Integer.MIN_VALUE;
        for (int task = 0; task < 3; task++) {
            if (task != lastTask) {
                int point = tr[day][task] + findMaxNinjaPointsHelper(tr, memo, day - 1, task);
                max = Math.max(max, point);
            }
        }
        return memo[day][lastTask] = max;
    }

    private static int findMaxNinjaPointsTabular(int[][] tr, int day) {
        int[][] dp = new int[day][4];
        dp[0][0] = Math.max(tr[0][1], tr[0][2]);
        dp[0][1] = Math.max(tr[0][0], tr[0][2]);
        dp[0][2] = Math.max(tr[0][0], tr[0][1]);
        dp[0][3] = Math.max(tr[0][0], Math.max(tr[0][1], tr[0][2]));

        for (int i = 1; i < day; i++) {
            for (int last = 0; last < 4; last++) {
                dp[i][last] = 0;
                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        dp[i][last] = Math.max(dp[i][last], tr[i][task] + dp[i - 1][task]);
                    }
                }
            }
        }
        return dp[day - 1][3];
    }

}
