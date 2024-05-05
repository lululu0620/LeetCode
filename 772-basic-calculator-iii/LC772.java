public class LC772 {
    // 计算器 3 有括号和加减乘除
    // 大致思想和 计算器2 相同
    // 在计算器2的基础上添加了括号
    // 当我们遇见括号时 利用dfs重复调用 helper函数
    // helper函数的逻辑与计算器2一致
    public int calculate(String s) {
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 主动排除空字符的干扰
            if (c != ' ') queue.offer(c);
        }
        // 最后手动添加空字符 保证term的计算正确
        queue.offer(' ');
        return helper(queue);
    }

    private int helper(Queue<Character> queue) {
        int res = 0, num = 0, term = 0;
        char op = '+';
        while (!queue.isEmpty()) {
            char c = queue.poll();
            if (Character.isDigit(c)) num = num * 10 + (c - '0');
            // 遇见括号时 反复调用helper
            else if (c == '(') num = helper(queue);
            else {
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
                // 遇见后括号时 计算完term的结果后直接退出返回
                if (c == ')') break;
                num = 0;
                op = c;
            }
        }
        if (term != 0) res += term;
        return res;
    }
}