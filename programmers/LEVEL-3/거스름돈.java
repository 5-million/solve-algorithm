import java.util.Arrays;

/**
 * LEVEL-3
 * 연습문제 > 거스름돈
 */
public class Solution {

    public int solution(int n, int[] money) {
        final int mod = 1000000007;
        final int m = money.length;

        int[][] dp = new int[m][n + 1];

        int[] sorted = Arrays.stream(money).sorted().toArray();
        for (int i = 0; i < m; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= n; i++)
            if (i % sorted[0] == 0)
                dp[0][i] = 1;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= sorted[i])
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - sorted[i]]) % mod;
            }
        }

        return dp[m - 1][n];
    }
}