package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Statement: Given a string consisting of digits from 2 to 9 (inclusive).
 * Return all possible letter combinations that the number can represent.
 * Example 1:Input:digits = "34"
 * Output:[ "dg", "dh", "di", "eg", "eh", "ei", "fg", "fh", "fi" ]
 * Explanation:The 3 is mapped with "def" and 4 is mapped with "ghi".
 * So all possible combinations by replacing the digits with characters are shown in the output.
 * <p>
 * Example 2: Input: digits = "3"
 * Output:[ "d", "e", "f" ]
 * Explanation:The 3 is mapped with "def".
 */
public class LetterCombinationsPhoneNumber {
    public static String[] KEYPAD = {
            "",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };

    public static void main(String[] args) {
        String digit = "34";
        List<String> res = new ArrayList<>();
        generateLetterCombination(digit, 0, new StringBuilder(), res);
        System.out.println(res);

    }

    private static void generateLetterCombination(String digit, int currIndex, StringBuilder sb, List<String> res) {
        if (currIndex == digit.length()) {
            res.add(sb.toString());
            return;
        }
        int number = digit.charAt(currIndex) - '0';
        String letter = KEYPAD[number];

        for (Character temp : letter.toCharArray()) {
            sb.append(temp);
            generateLetterCombination(digit, currIndex + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
