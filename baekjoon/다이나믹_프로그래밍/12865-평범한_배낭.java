import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * dp
 * 12865 - 평범한 배낭(G5)
 * https://www.acmicpc.net/problem/12865
 */
public class Main {

    private static class Thing {
        int w;
        int v;

        public Thing(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }

    private static int n, k;
    private static int[][] dp;
    private static Thing[] things;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(knapsack());
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[n + 1][k + 1];
        things = new Thing[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            things[i] = new Thing(w, v);
        }
    }

    private static int knapsack() {
        int value = 0;

        for (int i = 1; i <= n; i++) {
            int w = things[i].w, v = things[i].v;
            for (int j = 0; j <= k; j++) {
                if (j < w) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);

                    if (j == k) {
                        value = Math.max(value, dp[i][j]);
                    }
                }
            }
        }

        return value;
    }
}