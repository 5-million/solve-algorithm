import java.util.*;
import java.io.*;


public class Main {

    private static final int MOD = 1000000007;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        long answer = k * dnq(p, n) % MOD;
        System.out.println(answer);
    }

    private static long dnq(int p, long n) {
        if (n == 1) {
            return p;
        }

        if (n % 2 != 0) {
            return p * dnq(p, n - 1) % MOD;
        }

        long temp = dnq(p, n / 2);
        return temp * temp % MOD;
    }
}