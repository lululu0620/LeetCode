// Implement a basic calculator to evaluate a simple expression string.

// The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

// Example 1:
// Input: "1 + 1"
// Output: 2

// Example 2:
// Input: " 2-1 + 2 "
// Output: 3

// Example 3:
// Input: "(1+(4+5+2)-3)+(6+8)"
// Output: 23

// Loop the string character by character
// 5 possible characters in every bit
// (1) digit: add to the num
// (2) +: plus num to the res and set num = 0, record the sign
// (3) -: minus num to the res and set num = 0, record the sign
// (4) (: put the current res and sign to the stack and set res = 0, sign = 1
// (5) ): pop out the top two numbers from stack
//        first one is the sign before this pair of parenthesis
//        second is the temporary result before this pair of parenthesis

class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        int res = 0;
        int sign = 1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0') num = num * 10 + s.charAt(i) - '0';
            if (s.charAt(i) < '0' && s.charAt(i) != ' ') {
                if (s.charAt(i) == '+') {
                    res += sign * num;
                    num = 0;
                    sign = 1;
                }
                if (s.charAt(i) == '-') {
                    res += sign * num;
                    num = 0;
                    sign = -1;
                }
                if (s.charAt(i) == '(') {
                    stack.push(res);
                    stack.push(sign);
                    res = 0;
                    sign = 1;
                }
                if (s.charAt(i) == ')') {
                    res += sign * num;
                    num = 0;
                    res *= stack.pop();
                    res += stack.pop();
                }
            }
        }
        if (num != 0) res += sign * num; // add the last number
        return res;
    }
}
