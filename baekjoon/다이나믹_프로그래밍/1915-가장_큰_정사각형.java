import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1915 - 가장 큰 정사각형
 * https://www.acmicpc.net/problem/1915
 */
public class Main {


    private static int n, m;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution());
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            String input = br.readLine();
            for (int j = 1; j <= m; j++) {
                dp[i][j] = input.charAt(j - 1) - '0';
            }
        }
    }

    private static int solution() {
        int len = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (dp[i][j] == 0) continue;
                dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                len = Math.max(len, dp[i][j]);
            }
        }

        return len * len;
    }
}