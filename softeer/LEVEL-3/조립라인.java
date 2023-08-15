import java.util.*;
import java.io.*;


public class Main {

    private static int n;
    private static int[] a, b, moveA2B, moveB2A;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        a = new int[n + 1];
        b = new int[n + 1];
        moveA2B = new int[n + 1];
        moveB2A = new int[n + 1];

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st.nextToken());
            
            if (i < n) {
                moveA2B[i] = Integer.parseInt(st.nextToken());
                moveB2A[i] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] dp = new long[n + 1][2];
        dp[1][0] = a[1];
        dp[1][1] = b[1];
        for (int i = 2; i <= n; i++) {
            dp[i][0] = a[i] + Math.min(dp[i - 1][0], dp[i - 1][1] + moveB2A[i - 1]);
            dp[i][1] = b[i] + Math.min(dp[i - 1][1], dp[i - 1][0] + moveA2B[i - 1]);
        }

        System.out.println(Math.min(dp[n][0], dp[n][1]));
    }
}