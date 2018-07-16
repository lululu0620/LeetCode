// Implement a basic calculator to evaluate a simple expression string.

// The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

// Example 1:
// Input: "3+2*2"
// Output: 7

// Example 2:
// Input: " 3/2 "
// Output: 1

// Example 3:
// Input: " 3+5 / 2 "
// Output: 5


// Loop the string character by character
// 5 possible characters:
// (1) digit: add to num
// (2) +: plus the term to res and set term = num (which is the first number in one term)
// (3) -: plus the term to res and set term = -num (which is the first number in one term)
// (4) *: update term
// (5) /: update term
// op records the last operator
// if it is not a digit, we just care about the last operator

// Example: 3 + 2 * 2
// When loop to the '*', op = '+', num = 2, term = 3
// Then res += 3, term = 2


class Solution {
    public int calculate(String s) {
        int term = 0;
        int res = 0;
        int num = 0;
        char op = '+';
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0') num = num * 10 + s.charAt(i) - '0';
            if ((s.charAt(i) < '0' && s.charAt(i) != ' ') || i == s.length()-1) {
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
