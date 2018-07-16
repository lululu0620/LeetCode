// Implement a basic calculator to evaluate a simple expression string.

// The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

// The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

// You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].

// Some examples:

// "1 + 1" = 2
// " 6-4 / 2 " = 4
// "2*(5+5*2)/3+(6/2+8)" = 21
// "(2+6* 3+5- (3*14/7+2)*5)+3"=-12

// Use the recursive way to calculate the value of parentheses

class Solution {
  public int cal(String s, int start) {
        int term = 0;
        int res = 0;
        int num = 0;
        char op = '+';
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) >= '0') num = num * 10 + s.charAt(i) - '0';
            if (s.charAt(i) == '(') {
                int cnt = 0, j = i;
                for (; j < s.length(); j++) {
                    if (s.charAt(j) == '(') cnt++;
                    if (s.charAt(j) == ')') cnt--;
                    if (cnt == 0) break;
                }
                num = calculate(s.substring(i+1, j));
                i = j;
            }
            if ((s.charAt(i) < '0' && s.charAt(i) != '(' && s.charAt(i) != ' ') || i == s.length()-1) {
                if (op == '+') {
                    res += term;
                    term = num;
                }
                if (op == '-') {
                    res += term;
                    term = -num;
                }
                if (op == '*') term *= num;
                if (op == '/') term /= num;
                op = s.charAt(i);
                num = 0;
            }
        }
        if (term != 0) res += term;
        return res;
    }
}
