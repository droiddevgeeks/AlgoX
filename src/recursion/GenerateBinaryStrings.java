package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer n, return all binary strings of length n that do not contain consecutive 1s.
 * Return the result in lexicographically increasing order.
 * <p>
 * A binary string is a string consisting only of characters '0' and '1'.
 */
public class GenerateBinaryStrings {
    public static void main(String[] args) {
        int n = 3;
        List<String> result = new ArrayList<>();
        generateBinaryString("", n, result);
        System.out.println(result);
    }

    private static void generateBinaryString(String s, int n, List<String> result) {
        if (n == 0) {
            result.add(s);
            return;
        }
        generateBinaryString(s + '0', n - 1, result);
        if (s.isBlank() || s.charAt(s.length() - 1) != '1') {
            generateBinaryString(s + '1', n - 1, result);
        }
    }
}
