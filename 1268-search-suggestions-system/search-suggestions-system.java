import java.util.*;
class LC1504 {
    
    class TrieNode {
        private TrieNode[] children;
        private boolean isEnd;
        public TrieNode() {
            children = new TrieNode[26];
            isEnd = false;
        }
    }
    
    private TrieNode root;
    private Map<Integer, TrieNode> map = new HashMap<>();

    private void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.children[c - 'a'] == null) node.children[c - 'a'] = new TrieNode();
            node = node.children[c - 'a'];
            if (i + 1 == word.length()) node.isEnd = true;
        }
    }
    
    private void search(String searchWord) {
        TrieNode node = root;
        for (int i = 0; i < searchWord.length(); i++) {
            char c = searchWord.charAt(i);
            node = node == null ? null : node.children[c - 'a'];
            map.put(i, node);
        }
    }
    
    private void match(TrieNode node, StringBuilder sb, List<String> list) {
        if (list.size() == 3) return;
        if (node.isEnd) list.add(sb.toString());
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                sb.append((char) (i + 'a'));
                match(node.children[i], sb, list);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
    
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();
        root = new TrieNode();
        for (String product: products) insert(product);
        search(searchWord);
        for (int i = 0; i < searchWord.length(); i++) {
            List<String> ans = new ArrayList<>();
            TrieNode node = map.get(i);
            StringBuilder sb = new StringBuilder();
            sb.append(searchWord.substring(0, i + 1));
            if (node != null) match(node, sb, ans);
            res.add(ans);
        }
        return res;
    }
}