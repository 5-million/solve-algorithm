import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * dp
 * 5557 - 1학년(G5)
 * https://www.acmicpc.net/problem/5557
 */
public class Main {

    private static int n, target;
    private static final List<Integer> numbers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution());
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            numbers.add(Integer.parseInt(st.nextToken()));
        }
        target = Integer.parseInt(st.nextToken());
    }

    private static long solution() {
        long[][] dp = new long[n + 1][21];

        dp[1][numbers.get(0)] = 1;
        for (int i = 2; i < n; i++) {
            int number = numbers.get(i - 1);
            for (int j = 0; j <= 20; j++) {
                if (j + number <= 20) {
                    dp[i][j + number] += dp[i-1][j];
                }

                if (j - number >= 0) {
                    dp[i][j - number] += dp[i - 1][j];
                }
            }
        }

        return dp[n - 1][target];
    }
}
