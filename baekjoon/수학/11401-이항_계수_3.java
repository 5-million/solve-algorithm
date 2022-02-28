import java.util.Scanner;

/**
 * 수학 > 이항 계수 3
 */
public class Main {

    private static long[] mem = new long[4000001];
    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        mem[0] = 1;

        System.out.println(bc(n, k));
    }

    private static long bc(int n, int k) {
        long numerator = factorial(n);
        long denominator = factorial(k) * factorial(n - k) % MOD;
        return (numerator * pow(denominator, MOD - 2)) % MOD;
    }

    private static long factorial(int number) {
        if (mem[number] != 0) return mem[number];
        mem[number] = (number * factorial(number - 1)) % MOD;
        return mem[number];
    }

    private static long pow(long base, int expo) {
        if (expo == 1) return base % MOD;

        long temp = pow(base, expo / 2);

        if (expo % 2 == 0) return temp * temp % MOD;
        else return temp * temp % MOD * base % MOD;
    }
}
