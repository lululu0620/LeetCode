public class Solution {
    public int change(int amount, int[] coins) {
        int len = coins.length;
        // 状态 dp[i][j] 表示当金额为j时 coins的index取值范围为 [0, i]时 可以选择的combination的数量
        int[][] dp = new int[len][amount + 1];
        // 初始化 当金额为0时 combination的数量为1
        for (int i = 0; i < len; i++) dp[i][0] = 1;
        // 初始化 更新当i为0时的情况
        for (int j = 1; j <= amount; j++) {
            if (j >= coins[0]) dp[0][j] = dp[0][j - coins[0]];
        }
        for (int i = 1; i < len; i++) {
            for (int j = 1; j <= amount; j++) {
                // 状态转移方程
                // 当我们不选择第i种coin时
                dp[i][j] = dp[i-1][j];
                // 当我们选择使用第i种coin时
                if (j >= coins[i]) dp[i][j] += dp[i][j-coins[i]];
            }
        }
        return dp[len-1][amount];
    }

    // 空间优化: 可以看出我们可以将二维数组dp压缩为一维数组
    // 因为状态转移方程 dp[i][j] = dp[i-1][j] + dp[i][j-coins[i]] if (j >= coins[i])
    // 压缩为一维后变成 dp[j] = dp[j] + dp[j-coins[i]];
    public int change_optimized(int amount, int[] coins) {
        int len = coins.length;
        // 状态 dp[j] 表示当金额为j时 可以选择的combination的数量
        int[] dp = new int[amount + 1];
        // 初始化 当金额为0时 combination的数量为1
        dp[0] = 1;
        for (int i = 0; i < len; i++) {
            for (int j = 1; j <= amount; j++) {
                // 状态转移方程
                // 当我们不选择第i种coin时 dp[j] 保持不变
                // 当我们选择使用第i种coin时
                if (j >= coins[i]) dp[j] += dp[j-coins[i]];
            }
        }
        return dp[amount];
    }
}