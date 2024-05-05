import java.util.*;
class LC32 { 
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int start = -1, res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') stack.push(i);
            else {
                // if invalid ) comes first
                if (stack.isEmpty()) start = i;
                else {
                    stack.pop();
                    if (stack.isEmpty()) res = Math.max(res, i - start);
                    else res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }
}