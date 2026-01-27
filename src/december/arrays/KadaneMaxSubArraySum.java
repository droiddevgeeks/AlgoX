package december.arrays;

import java.util.Arrays;

public class KadaneMaxSubArraySum {
    public static void main(String[] args) {
        //int[] input = {2, 3, 5, -2, 7, -4};
        int[] input = {-2, -3, -7, -2, -10, -4};
        //int[] input = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        //Result result = kadaneMaxSubArraySum(input);
        Result result = kadaneAlgo(input);
        System.out.println("Max Sum ==>" + result.maxSum);
        System.out.println("Max Sub Array ==>" + Arrays.toString(Arrays.copyOfRange(input, result.sIndex, result.eIndex + 1)));
    }

    private static Result kadaneMaxSubArraySum(int[] input) {
        Result res = new Result();
        int sIndex = -1;
        int eIndex = -1;
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum = 0;
            for (int j = i; j < input.length; j++) {
                sum += input[j];
                if (sum > maxSum) {
                    maxSum = sum;
                    sIndex = i;
                    eIndex = j;
                }
            }
        }
        res.maxSum = maxSum;
        res.sIndex = sIndex;
        res.eIndex = eIndex;
        return res;
    }

    private static Result kadaneAlgo(int[] input) {
        Result res = new Result();
        int sIndex = -1;
        int eIndex = -1;
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        int start = 0;
        for (int i = 0; i < input.length; i++) {
            if (sum == 0) start = i;
            sum += input[i];
            if (sum > maxSum) {
                maxSum = sum;
                sIndex = start;
                eIndex = i;
            }
            if (sum < 0) sum = 0;
        }
        res.maxSum = maxSum;
        res.sIndex = sIndex;
        res.eIndex = eIndex;
        return res;
    }
}

class Result {
    int sIndex = -1;
    int eIndex = -1;
    int maxSum = Integer.MIN_VALUE;
}
