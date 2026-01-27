package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Statement: You are given a string s, partition it in such a way that every substring is a palindrome.
 * Return all such palindromic partitions of string s.
 * A palindrome string is a string that reads the same backward as forward.
 * Input:  s = “aab”
 * Output: {["a","a","b"], ["aa","b"]]
 */
public class PalindromePartitioning {
    public static void main(String[] args) {
        String s = "aabb";
        List<List<String>> res = new ArrayList<>();
        System.out.println("PalindromePartitioning of" + s);
        generatePalindromePartitioning(0, s, new ArrayList<>(), res);
        for (List<String> temp : res) {
            System.out.println(temp);
        }

    }

    private static void generatePalindromePartitioning(int start, String s, ArrayList<String> currPartition, List<List<String>> res) {
        if (s.length() == start) {
            res.add(new ArrayList<>(currPartition));
            return;
        }
        System.out.print("Start before recursion----" + start);
        for (int end = start; end < s.length(); end++) {
            if (isPalindrome(s, start, end)) {
                currPartition.add(s.substring(start, end + 1));
                generatePalindromePartitioning(end + 1, s, currPartition, res);
                currPartition.removeLast();
            }
        }
    }

    private static boolean isPalindrome(String s, int start, int i) {
        System.out.print("---Start--" + start + "---end---" + i);
        System.out.println("-----SubsString-- " + s.substring(start, i+1));
        char[] temp = s.toCharArray();
        while (start < i) {
            if (temp[start] != temp[i]) {
                return false;
            }
            start++;
            i--;
        }
        return true;
    }
}
