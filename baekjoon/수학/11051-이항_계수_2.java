import java.util.Scanner;

/**
 * 11051 - 이항 계수 2
 * https://www.acmicpc.net/problem/11051
 */
public class Main {

    private static final int P = 10007;
    private static final int[] dp = new int[1001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int numerator = factorial(n);
        int denominator = factorial(k) * factorial(n - k) % P;
        System.out.println(numerator * pow(denominator, P - 2) % P);
    }

    private static int factorial(int n) {
        if (n < 2) return 1;
        if (dp[n] != 0) return dp[n];
        dp[n] = n * factorial(n - 1) % P;
        return dp[n];
    }

    private static int pow(int base, int expo) {
        int ret = 1;
        for (int i = 1; i <= expo; i++) {
            ret = ret * base % P;
        }

        return ret;
    }
}