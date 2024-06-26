/*
 * @lc app=leetcode id=224 lang=java
 *
 * [224] Basic Calculator
 */

// @lc code=start
class Solution {
    // 计算器 1 只有加减和括号
    public int calculate(String s) {
        // (1+(4+5+2)-3)+(6+8)
        // num 表示表达式中的数字 比如 上式中的 1,4,5,2,3,6,8
        // sign 表示表达式中的符号 比如 +,-  加号用1表示 减号用-1表示 
        // 初始化为1 因为每一个没有符号的数字前面都有一个隐藏的加号
        // res 表示表达式的结果
        int num = 0, sign = 1, res = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            // 获取表达式中的数字 如有多位需乘10叠加
            if (c >= '0' && c <= '9') num = num * 10 + (c - '0');
            else {
                // 当出现新的符号时 根据符号的正负 我们将之前的数字加入或减去到结果中
                // 此时需记录下新的符号并将数字重新初始化
                if (c == '+' || c == '-') {
                    res += sign * num;
                    num = 0;
                    sign = (c == '+' ? 1 : -1);
                }
                // 当出现前括号时 一个新的表达式出现（括号内）
                // 我们将之前计算出的结果 和出现在这个括号之前的符号 暂时保存在栈中
                // (1+(4+5+2)-3)+(6+8)
                // 比如在上式中 计算到第二个括号时 我们将 1 和 + 暂时保存在栈中
                // 此时需将所有的数字 符号 和结果重新初始化 因为我们即将开始计算一个新的表示式（括号内）
                if (c == '(') {
                    stack.push(res);
                    stack.push(sign);
                    res = 0;
                    num = 0;
                    sign = 1;
                }
                // 当出现后括号时 说明括号内的表达式已经计算完毕
                // 我们需要将这个结果 和之前暂时保存在栈中的结果相加/相减（根据保存在栈中的符号）
                if (c == ')') {
                    res += sign * num;
                    num = 0;
                    sign = 1;
                    // 此时栈顶保存的符号是出现在这个括号表达式之前的符号
                    res *= stack.pop();
                    // 此时栈顶保存的数字是之前的计算结果
                    res += stack.pop();
                }
            }
        }
        // 当表达式最后一位是数字时 我们需要将它纳入计算结果
        if (num != 0) res += sign * num;
        return res;
    }
}
// @lc code=end

