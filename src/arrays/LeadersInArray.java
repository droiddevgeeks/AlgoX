package arrays;

import java.util.*;

/**
 * Example 1:
 * Input:
 * arr = [4, 7, 1, 0]
 * Output:
 * 7 1 0
 * Explanation:
 * Rightmost element is always a leader. 7 and 1 are greater than the elements in their right side.
 * <p>
 * Example 2:
 * Input:
 * arr = [10, 22, 12, 3, 0, 6]
 * Output:
 * 22 12 6
 * Explanation:
 * 6 is a leader. In addition to that, 12 is greater than all the elements in its right side (3, 0, 6), also 22 is greater than 12, 3, 0, 6.
 */
public class LeadersInArray {

    public static void main(String[] args) {
        //int[] nums = new int[]{4, 7, 1, 0};
        int[] nums = new int[]{10, 22, 12, 3, 0, 6};

        int[] leaders = leadersInArray(nums);
        System.out.println("LeadersInArray ==>" + Arrays. toString(leaders));
    }

    private static int[] leadersInArray(int[] a) {
        int len = a.length;
        int max = a[len - 1];
        List<Integer> leaders = new ArrayList<>();
        leaders.add(max); //last element is always leader
        for (int i = len - 2; i >= 0; i--) {
            if (a[i] > max) {
                leaders.add(a[i]);
                max = a[i];
            }
        }
        Collections.reverse(leaders);
        return leaders.stream().mapToInt(Integer::intValue).toArray();
    }
}
