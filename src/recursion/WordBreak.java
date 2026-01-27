package recursion;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Problem Statement: Given a string s and a dictionary of strings wordDict,
 * return true if s can be segmented into a space-separated sequence of one or more dictionary words otherwise return false.
 * <p>
 * Examples Input:
 * s = "takeuforward", wordDict = ["take", "forward", "you", "u"]
 * Output:true
 * Explanation: Return true because "takeuforward" can be segmented as "take", "u", "forward".
 * <p>
 * Example 2:Input: s = "applepineapple", wordDict = ["apple"]
 * Output:false
 * Explanation:Return false because "applepineapple" can be segmented as "apple", "pine", "apple" but "pine" is not in the dictionary.
 */
public class WordBreak {
    public static void main(String[] args) {
        String s = "takeuforward";
        HashSet<String> dict = new HashSet<>(Arrays.asList("take", "forward", "you", "u"));
        boolean canSegmented = canSegmentedWord(s, 0, dict);
        System.out.println("WordBreak--" + canSegmented);
    }

    private static boolean canSegmentedWord(String s, int index, HashSet<String> dict) {
        if (index == s.length()) return true;
        for (int end = index; end < s.length(); end++) {
            String temp = s.substring(index, end + 1);
            if (dict.contains(temp)) {
                if (canSegmentedWord(s, end + 1, dict)) {
                    return true;
                }
            }
        }
        return false;
    }
}
