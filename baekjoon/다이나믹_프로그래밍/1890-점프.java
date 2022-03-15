import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1890 - 점프
 * https://www.acmicpc.net/problem/1890
 */
public class Main {

    private static int n;
    private static int[][] board;
    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution());
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        board = new int[n + 1][n + 1];
        dp = new long[n + 10][n + 10];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static long solution() {
        dp[n][n] = 1;
        for (int i = n; i > 0; i--) {
            for (int j = n; j > 0; j--) {
                int dist = board[i][j];
                if (dist == 0) continue;
                dp[i][j] += dp[i + dist][j] + dp[i][j + dist];
            }
        }

        return dp[1][1];
    }
}

