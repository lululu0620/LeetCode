class Solution {
    // 计算器 2 只有加减乘除
    public int calculate(String s) {
        // 3+2*2
        // res 表示表达式的结果
        // num 表示表达式中的数字 比如 上式中的 3,2,2
        // term 表示表达式中只含有乘法或者除法部分的结果 比如 上式中的 3,2*2
        // 由term的定义可知 term是由加号或减号分隔开的
        int res = 0, num = 0, term = 0;
        // op 表示表达式中的符号 比如 +,-,*,/
        char op = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 获取表达式中的数字 如有多位需乘10叠加
            if (Character.isDigit(c)) num = num * 10 + (c - '0');
            // 当出现新的符号（c不为数字时）需要根据上一个符号（op）的种类 将数字加入res或term中
            // ⚠️注意: 条件 i == s.length() - 1 是为了更新最后一个term的结果 不可以省略
            // 我们只有遇到下一个符号时 才会更新上一个符号带来的计算 从而导致最后一个符号的计算没有被纳入term的结果中
            // 所以我们也不可以跳过空字符 因为空字符也有可能是最后一位
            else if (c == '+' || c == '-' || c == '*' || c == '/' || i == s.length() - 1){
                // ⚠️注意: 此时我们需要观察的是出现在当前数字num之前的符号 即op 而非c本身（c出现在当前数字num之后）
                // 当op为加号或减号时 我们知道之前的term的结果已经计算完毕 所以我们可以将其加入的结果中
                // 同时此时的数字num即为下一个term中出现的第一个数 根据op的符号更新
                if (op == '+') {
                    res += term;
                    term = num;
                }
                if (op == '-') {
                    res += term;
                    term = -num;
                }
                // 当op为乘号或除号时 我们知道我们仍然在进行当前term的计算 所以只需要更新term的值即可
                if (op == '*') term *= num;
                if (op == '/') term /= num;
                // 此时需记录下新的符号并将数字重新初始化
                num = 0;
                op = c;
            }
        }
        // 当表达式最后一位是数字时 我们需要将它所在的term也纳入计算结果
        if (term != 0) res += term;
        return res;
    }
}