import java.util.Arrays;
import java.util.Scanner;

/**
 * 2098 - 외판원 순회
 * https://www.acmicpc.net/problem/2098
 */
public class Main {

    private static final int INF = 16000000;
    private static int n;
    private static int[][] load;
    private static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        load = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                load[i][j] = sc.nextInt();
            }
        }

        dp = new int[n][1 << n];
        for (int i = 0; i < n; i++)
            Arrays.fill(dp[i], INF);

        System.out.println(dfs(0, 1));
    }

    private static int dfs(int now, int bitmask) {
        if (bitmask == (1 << n) - 1) {
            if (load[now][0] == 0) return INF;
            else return load[now][0];
        }

        if (dp[now][bitmask] != INF)
            return dp[now][bitmask];

        for (int next = 0; next < n; next++) {
            if (load[now][next] == 0 || (bitmask & (1 << next)) != 0)
                continue;

            dp[now][bitmask] = Math.min(
                    dp[now][bitmask],
                    dfs(next, bitmask | (1 << next)) + load[now][next]
            );
        }

        return dp[now][bitmask];
    }

}