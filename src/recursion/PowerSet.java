package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Power Set: Print all the possible subsequences of the String
 * <p>
 * Problem Description: Given a string, find all the possible subsequences of the string.
 * Input: str = "abc"
 * Output: [a, ab, abc, ac, b, bc, c]
 * Explanation: Given string has 7 subsequences.
 * Input: str = "aa"
 * Output: [a, a, aa]
 * Explanation: Given string has 3 subsequences.
 */
public class PowerSet {
    public static void main(String[] args) {

        String str = "abc";
        List<String> res = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        generatePowerSet(str, 0, stringBuilder, res);
        System.out.println("PowerSet" + res);
    }

    private static void generatePowerSet(String str, int index, StringBuilder curr, List<String> res) {
        if (str.length() == index) {
            res.add(curr.toString());
            return;
        }
        generatePowerSet(str, index + 1, curr, res);
        curr.append(str.charAt(index));
        generatePowerSet(str, index + 1, curr, res);
        curr.deleteCharAt(curr.length() - 1);
    }
}
