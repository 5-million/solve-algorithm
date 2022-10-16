import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G5
 * 9084 - 동전
 * https://www.acmicpc.net/problem/9084
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] coin = new int[n + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }

            int money = Integer.parseInt(br.readLine());
            int[][] dp = new int[n + 1][money + 1];
            for (int i = 1; i <= n; i++) {
                dp[i][0] = 1;
            }

            for (int i = 1; i <= n; i++) {
                int c = coin[i];

                for (int j = 1; j <= money; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= c) {
                        dp[i][j] += dp[i][j - c];
                    }
                }
            }

            System.out.println(dp[n][money]);
        }
    }
}