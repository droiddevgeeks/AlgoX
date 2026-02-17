package dp.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Problem Statement: Given an array of strings words[], the task is to return the longest string chain.
 * A string chain is defined as a sequence of words where:
 * <p>
 * Each word (except the first) can be formed by inserting exactly one character into the previous word.
 * The first word of the chain can be any word from the words[] array.
 * The task is to determine the length of the longest chain.
 * Input: words = ["a", "ab", "abc", "abcd", "abcde"]   Output:5
 * Explanation:The longest chain is ["a", "ab", "abc", "abcd", "abcde"]. Each word in the chain is formed by adding exactly one character to the previous word.
 */
public class LongestStringChain {
    public static void main(String[] args) {
        String[] words = new String[]{"a", "ab", "abc", "abcd", "abcde"};

        Arrays.sort(words, Comparator.comparingInt(String::length));
        int maxChainLength = findLongestStringChain(words);
        System.out.println("LongestStringChain---->" + maxChainLength);

        maxChainLength = findLongestStringChainRec(words, 0, -1);
        System.out.println("LongestStringChain Rec---->" + maxChainLength);

        int n = words.length;
        Integer[][] memo = new Integer[n + 1][n + 1];
        maxChainLength = findLongestStringChainMemo(words, 0, -1, memo);
        System.out.println("LongestStringChain Memo---->" + maxChainLength);
    }

    private static int findLongestStringChain(String[] words) {
        HashMap<String, Integer> map = new HashMap<>();
        int maxLength = 1;
        for (String word : words) {
            int best = 1;

            for (int i = 0; i < word.length(); i++) {
                //Takes characters before index i
                //Skips character at i
                //Takes characters after index i
                //Concatenates them
                String prev = word.substring(0, i) + word.substring(i + 1);
                best = Math.max(best, map.getOrDefault(prev, 0) + 1);
            }

            map.put(word, best);
            maxLength = Math.max(maxLength, best);
        }
        return maxLength;
    }

    //From currIndex onward,what is the longest chain ,assuming last picked word is prevIndex?
    private static int findLongestStringChainRec(String[] words, int index, int prevIndex) {
        if (index == words.length) return 0;

        int skip = findLongestStringChainRec(words, index + 1, prevIndex);
        int take = 0;
        if (prevIndex == -1 || isPredecessor(words[prevIndex], words[index])) {
            take = 1 + findLongestStringChainRec(words, index + 1, index);
        }
        return Math.max(skip, take);
    }

    private static boolean isPredecessor(String a, String b) {
        if (a.length() + 1 != b.length()) return false;
        int i = 0, j = 0;
        while (i < a.length() && j < b.length()) {
            if (a.charAt(i) == b.charAt(j)) i++;
            j++;
        }
        return i == a.length();
    }

    private static int findLongestStringChainMemo(String[] words, int index, int prevIndex, Integer[][] memo) {
        if (index == words.length) return 0;
        if (memo[index][prevIndex + 1] != null) return memo[index][prevIndex + 1];
        int skip = findLongestStringChainMemo(words, index + 1, prevIndex, memo);
        int take = 0;
        if (prevIndex == -1 || isPredecessor(words[prevIndex], words[index])) {
            take = 1 + findLongestStringChainMemo(words, index + 1, index, memo);
        }
        memo[index][prevIndex + 1] = Math.max(skip, take);
        return memo[index][prevIndex + 1];
    }
}
