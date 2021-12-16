/**
 * LEVEL-3
 * 연습문제 > 2 X n 타일링
 */
public class Solution {

    public int solution(int n) {
        int MOD = 1000000007;
        int[] dp = new int[60001];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++)
            dp[i] = (dp[i-1] + dp[i-2]) % MOD;

        return dp[n];
    }
}