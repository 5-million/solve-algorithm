import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * dp
 * 2631 - 줄세우기(G5)
 * https://www.acmicpc.net/problem/2631
 */
public class Main {

    private static int n;
    private static int[] line;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(n - lis());
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        line = new int[n];

        for (int i = 0; i < n; i++) {
            line[i] = Integer.parseInt(br.readLine());
        }
    }

    private static int lis() {
        int ret = 1;
        int dp[] = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (line[j] < line[i]) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i] += 1;
            ret = Math.max(ret, dp[i]);
        }

        return ret;
    }
}
