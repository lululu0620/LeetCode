class Solution {
    public int combinationSum4(int[] nums, int target) {
        int len = nums.length;
        // 状态 dp[i][j] 表示当target为j时 最后一个数选择nums[i]时 可以选择的combination的数量
        int[][] dp = new int[len][target + 1];
        for (int j = 1; j <= target; j++) {
            for (int i = 0; i < len; i++) {
                // 状态转移方程
                // 当我们不选择nums[i]时 dp[i][j] = 0;
                // 当我们选择使用nums[i]时
                if (j == nums[i]) dp[i][j] = 1;
                if (j > nums[i]) {
                    for (int k = 0; k < len; k++) dp[i][j] += dp[k][j-nums[i]];
                }
            }
        }
        int res = 0;
        for (int i = 0; i < len; i++) res += dp[i][target];
        return res;
    }
    
    // 空间优化: 可以看出我们可以将二维数组dp压缩为一维数组
    // 因为状态转移方程 dp[i][j] = dp[k][j-nums[i]] (0 <= k < len)
    // 压缩为一维后变成 dp[j] = dp[j] + dp[j-coins[i]];
    public int combinationSum4_optimized(int[] nums, int target) {
        int len = nums.length;
        // 状态 dp[j] 表示当target为j时 可以选择的combination的数量
        int[] dp = new int[target + 1];
        for (int j = 1; j <= target; j++) {
            for (int i = 0; i < len; i++) {
                // 状态转移方程
                // 当我们不选择nums[i]时 dp[i][j] = 0;
                // 当我们选择使用nums[i]时
                if (j >= nums[i]) dp[j] += dp[j-nums[i]];
            }
        }
        return dp[target];
    }
}