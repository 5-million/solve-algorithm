/**
 * LEVEL-3
 * 연습문제 > 멀리 뛰기
 */
public class Solution {
    public long solution(int n) {
        final int MOD = 1234567;

        long[] dp = new long[2001];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++)
            dp[i] = (dp[i - 2] + dp[i - 1]) % MOD;

        return dp[n];
    }
}
