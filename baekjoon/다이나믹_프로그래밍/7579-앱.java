import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G3
 * 7579 - 앱
 * https://www.acmicpc.net/problem/7579
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] memory = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int[] cost = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        int ans = Integer.MAX_VALUE;
        int[][] dp = new int[n + 1][n * 100 + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n * 100; j++) {
                if (j < cost[i]) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }

                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost[i]] + memory[i]);

                if (dp[i][j] >= m) {
                    ans = Math.min(ans, j);
                    break;
                }
            }
        }

        System.out.println(ans);
    }
}