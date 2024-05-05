class LC474 {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] count = new int[len][2];
        for (int i = 0; i < len; i++) count[i] = calculate(strs[i]);
        int[][][] dp = new int[m+1][n+1][len];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k < len; k++) {
                    if (k == 0) dp[i][j][k] = (count[k][0] <= i && count[k][1] <= j) ? 1 : 0;
                    else {
                        dp[i][j][k] = dp[i][j][k-1];
                        if (count[k][0] <= i && count[k][1] <= j) dp[i][j][k] = Math.max(dp[i][j][k], dp[i-count[k][0]][j-count[k][1]][k-1] + 1);
                    }
                }
            }
        }
        return dp[m][n][len-1];
    }

    private int[] calculate(String str) {
        int[] ans = new int[2];
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) ans[c[i] - '0']++;
        return ans;
    }
}