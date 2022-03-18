import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 11049 - 행렬 곱셈 순서
 * https://www.acmicpc.net/problem/11049
 */
public class Main {

    private static class Matrix {
        int r;
        int c;

        public Matrix(String input) {
            String[] s = input.split(" ");
            this.r = Integer.parseInt(s[0]);
            this.c = Integer.parseInt(s[1]);
        }
    }

    private static final long INF = Long.MAX_VALUE;
    private static final List<Matrix> matrices = new ArrayList<>();
    private static int n;
    private static long dp[][];

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution(0, n - 1));
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            matrices.add(new Matrix(br.readLine()));
        }

        dp = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
    }

    private static long solution(int start, int end) {
        if (start == end) return 0;
        if (dp[start][end] != INF) return dp[start][end];

        for (int i = start; i < end; i++) {
            long temp = solution(start, i)
                    + solution(i + 1, end)
                    + ((long) matrices.get(start).r * matrices.get(i).c * matrices.get(end).c);

            dp[start][end] = Math.min(dp[start][end], temp);
        }

        return dp[start][end];
    }
}