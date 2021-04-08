class Solution {
    private long MOD = 1000000007;
    public int numMusicPlaylists(int N, int L, int K) {
        long dp[][] = new long[N + 1][L + 1];
        dp[1][1] = (long) N;
        for (int n = 1; n <= N; n++) {
            for (int l = 1; l <= L; l++) {
                if (n == 1 && l == 1) continue;
                if (n > K) dp[n][l] += dp[n][l-1] * (long) (n - K) % MOD;
                dp[n][l] += dp[n-1][l-1] * (long) (N - n + 1) % MOD;
            }
        }
        return (int) (dp[N][L] % MOD);
    }
}