import java.util.Scanner;

public class Boj2225 {

    private static final int MOD = 1000000000;

    private static int n;
    private static int k;
    private static int[][] dp;

    public static void main(String[] args) {
        init();
        System.out.println(solution());
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        dp = new int[n+1][k+1];
    }

    private static int solution() {
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][1] = 1;
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                for (int l = 0; l <= i; l++)
                    dp[i][j] = (dp[i][j] + dp[i - l][j - 1]) % MOD;
            }
        }

        return dp[n][k];
    }
}