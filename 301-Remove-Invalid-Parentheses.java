// Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

// Note: The input string may contain letters other than the parentheses ( and ).

// Example 1:
// Input: "()())()"
// Output: ["()()()", "(())()"]

// Example 2:
// Input: "(a)())()"
// Output: ["(a)()()", "(a())()"]

// Example 3:
// Input: ")("
// Output: [""]

class Solution {

    // BFS
    // Everytime delete one character and use a set to avoid duplicated string
    // Use a queue to store all the strings
    // Once find a level which is valid and push all the string in this level into a list
    
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        int flag = 0;
        Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            String temp = queue.poll();
            if (isValid(temp)) {
                res.add(temp);
                flag = 1;
            }
            if (flag == 1) continue;
            for (int i = 0; i < temp.length(); i++) {
                if (temp.charAt(i) != '(' && temp.charAt(i) != ')') continue;
                String remove = temp.substring(0, i) + temp.substring(i+1, temp.length());
                if (!set.contains(remove)) {
                    set.add(remove);
                    queue.offer(remove);
                }
            }
        }
        return res;
    }
    public boolean isValid(String s) {
        int stack = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stack++;
            else if (s.charAt(i) == ')' && stack == 0) return false;
            else if (s.charAt(i) == ')') stack--;
        }
        return stack == 0;
    }
    
    // DFS
    // First record the number of parenthese which is not valid and there are three cases
    // Case #1 left = 0  right = 0  => Judge whether the string is valid => add this string to a list
    // loop the string    Case #2 left > 0 and this character is ( => delete this character and recurse
    //                    Case #3 right > 0 and this character is ) => delete this character and recurse
    // Record the location of start which can save a lot of repeated calculations
    // Skip the character which is the same of the last one
    
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
        	if (s.charAt(i) == '(') left++;
        	else if (s.charAt(i) == ')' && left > 0) left--;
        	else if (s.charAt(i) == ')') right++;
        }
        helper(s, left, right, res, 0);
        return res;
    }
    
    public void helper(String s, int left, int right, List<String> res, int start) {
        if (left ==  0 && right == 0 && isValid(s)) res.add(s);
        else {
        	for (int i = start; i < s.length(); i++) {
        		if ((s.charAt(i) != '(' && s.charAt(i) != ')') || (i != start && s.charAt(i) == s.charAt(i-1))) continue;
        		String remove = s.substring(0, i) + s.substring(i+1, s.length());
        		if (left > 0 && s.charAt(i) == '(') helper(remove, left-1, right, res, i);
        		if (right > 0 && s.charAt(i) == ')') helper(remove, left, right-1, res, i);
        	}
        }
    }
}
