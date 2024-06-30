import java.util.*;
/*
 * @lc app=leetcode id=212 lang=java
 *
 * [212] Word Search II
 */

// @lc code=start
class Solution {
    class TrieNode {
        TrieNode[] children;
        String word;
        
        public TrieNode() {
            children = new TrieNode[26];
        }
    }
    
    TrieNode root;
    List<String> res;
    int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int row, col;

    public List<String> findWords(char[][] board, String[] words) {
        root = new TrieNode();
        for (String word: words) insert(root, word);
        
        res = new ArrayList<String>();
        row = board.length;
        col = board[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dfs(board, i, j, root);
            }
        }

        return res;
    }

    private void insert(TrieNode root, String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (root.children[c - 'a'] == null) root.children[c - 'a'] = new TrieNode();
            root = root.children[c - 'a'];
        }
        root.word = word;
    }

    private void dfs(char[][] board, int x, int y, TrieNode node) {
        char c = board[x][y];
        if (c == 'X' || node.children[c - 'a'] == null) return;
        node = node.children[c - 'a'];
        if (node.word != null) {
            res.add(node.word);
            node.word = null;
        }
        board[x][y] = 'X';
        for (int[] dir: dirs) {
            int i = x + dir[0], j = y + dir[1];
            if (i < 0 || i >= row || j < 0 || j >= col || board[i][j] == 'X') continue;
            dfs(board, i, j, node);
        }
        board[x][y] = c;
    }
}
// @lc code=end

