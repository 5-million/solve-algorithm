class Solution {
    
    private static final int MOD = 1000000007;
    
    public int solution(int n) {
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[2] = 3;
        
        for (int i = 4; i <= n; i += 2) {
            dp[i] = 3 * dp[i - 2] % MOD;
            for (int j = 4; j <= i; j += 2) {
                dp[i] = (dp[i] + (2 * dp[i - j]) % MOD) % MOD;
            }
        }
        
        return (int) dp[n];
    }
}
