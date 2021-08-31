import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    private static int n;
    private static int[][] dp;
    private static final int MOD = 9901;

    public static void main(String[] args) {
        n = new Scanner(System.in).nextInt();
        dp = new int[n][3];

        System.out.println(solution());
    }

    private static int solution() {
        dp[0][0] = 1;
        dp[0][1] = 1;
        dp[0][2] = 1;

        for (int i = 1; i < n; i++) {
            dp[i][0] = sumArray(dp[i-1]);
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % MOD;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % MOD;
        }

        return sumArray(dp[n - 1]);
    }

    private static int sumArray(int[] arr) {
        return IntStream.of(arr).sum() % MOD;
    }
}