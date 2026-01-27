package StackAndQueue;

/**
 * Input : height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * Output : 6
 * Explanation : Water is trapped in the dips between bars. The total trapped water units add up to 6 (1+1+2+1+1).
 * <p>
 * Input : height = [4,2,0,3,2,5]
 * Output : 9
 * Explanation : The elevation map traps 9 units of water in total, as water fills the spaces between higher bars on both sides.
 */
public class TrappingRainwater {
    public static void main(String[] args) {
        //int[] ht = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] ht = new int[]{4,2,0,3,2,5};
        int result = findTrappingRainwater(ht);
        System.out.println("result==" + result);
        System.out.println("result Optimized===" + findTrappingRainwaterOptimized(ht));
    }

    private static int findTrappingRainwater(int[] ht) {
        int total = 0;
        for (int i = 0; i < ht.length; i++) {
            int maxLeft = 0;
            int maxRight = 0;
            for (int j = 0; j <= i; j++) {
                maxLeft = Math.max(maxLeft, ht[j]);
            }

            for (int j = i; j < ht.length; j++) {
                maxRight = Math.max(maxRight, ht[j]);
            }
            total += Math.min(maxLeft, maxRight) - ht[i];
        }
        return total;
    }

    private static int findTrappingRainwaterOptimized(int[] ht) {
        int left = 0, maxLeft = 0;
        int right = ht.length - 1, maxRight = 0;
        int total = 0;

        while (left < right) {
            if (ht[left] < ht[right]) {
                if (maxLeft <= ht[left]) {
                    maxLeft = ht[left];
                } else {
                    total += maxLeft - ht[left];
                }
                left++;
            } else {
                if (ht[right] >= maxRight) {
                    maxRight = ht[right];
                } else {
                    total += maxRight - ht[right];
                }
                right--;
            }
        }
        return total;
    }
}
