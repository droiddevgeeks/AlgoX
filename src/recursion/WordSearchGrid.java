package recursion;

/**
 * Given an m x n grid of characters board and a string word, return true if the word exists in the grid.
 * The word can be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * Input: [["A", "B", "C", "E"], ["S", "F", "C", "S"]["A", "D", "E", "E"]] word = "ABCCED"
 * Output: true
 * Explanation: We can easily find the given word in the matrix.
 * <p>
 * Input:[["A", "B", "C", "E"],["S", "F", "C", "S"],["A", "D", "E", "E"]]word = "ABCB"
 * Output: false
 * Explanation:  There is no such word in the given matrix.
 */
public class WordSearchGrid {
    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        //char[] word = "ABCB".toCharArray();
        char[] word = "SEE".toCharArray();
        //char[] word = "ABCCED".toCharArray();

        boolean isWordFound = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                isWordFound = findWordSearchGrid(grid, i, j, word, 0);
                if(isWordFound){
                    System.out.println("WordSearchGrid----" + isWordFound);
                    break; // stop searching
                }
            }
        }
    }

    private static boolean findWordSearchGrid(char[][] grid, int i, int j, char[] word, int k) {
        if (k == word.length) {
            return true;
        }
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != word[k]) return false;
        char temp = grid[i][j];
        grid[i][j] = '#';
        boolean down = findWordSearchGrid(grid, i + 1, j, word, k + 1);
        boolean right = findWordSearchGrid(grid, i, j + 1, word, k + 1);
        boolean up = findWordSearchGrid(grid, i - 1, j, word, k + 1);
        boolean left = findWordSearchGrid(grid, i, j - 1, word, k + 1);
        grid[i][j] = temp;
        return right || down || left || up;
    }
}
