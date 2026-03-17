package graph;

import java.util.*;

/**
 * Problem Statement: Given are the two distinct words startWord and targetWord,
 * and a list denoting wordList of unique words of equal lengths.
 * Find the length of the shortest transformation sequence from startWord to targetWord..
 * wordList = {"des","der","dfr","dgt","dfs"}
 * startWord = "der", targetWord = "dfs"
 * Output: 3
 * <p>
 * wordList = {"geek", "gefk"}
 * startWord = "gedk", targetWord= "geek"
 * Output: 2
 */
public class WordLadder1 {
    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("des", "der", "dfr", "dgt", "dfs");
        String startWord = "der";
        String targetWord = "dfs";
        int shortestPath = findShortestPathToTransform(startWord, targetWord, wordList);
        System.out.println("WordLadder1--->" + shortestPath);
    }

    /**
     * Time Complexity
     * Word length = L , Word list size = N , Alphabet = 26 : O(N * L * 26)
     * Space : O(N)
     */
    private static int findShortestPathToTransform(String startWord, String targetWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>(wordList);
        if (!set.contains(targetWord)) return 0;
        Queue<String> queue = new LinkedList<>();
        queue.add(startWord);

        int steps = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                if (currWord != null && currWord.equals(targetWord)) return steps;
                char[] arr = currWord.toCharArray();
                for (int j = 0; j < arr.length; j++) {
                    char original = arr[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        arr[j] = c;
                        String newWord = new String(arr);
                        if (set.contains(newWord)) {
                            queue.add(newWord);
                            set.remove(newWord);
                        }
                    }
                    arr[j] = original;
                }
            }
            steps++;
        }
        return steps;
    }

}
