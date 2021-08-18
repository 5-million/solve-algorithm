import java.util.Scanner;

public class Main {

    private static int[][][] dp = new int[101][101][101];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        while (a != -1 || b != -1 || c != -1) {
            a += 50;
            b += 50;
            c += 50;

            System.out.println(makeOutput(a, b, c, recursive(a, b, c)));

            a = sc.nextInt();
            b = sc.nextInt();
            c = sc.nextInt();
        }
    }

    private static int recursive(int a, int b, int c) {
        int ret;

        if (dp[a][b][c] != 0) {
            ret = dp[a][b][c];
        } else {
            if (a <= 50 || b <= 50 || c <= 50)
                ret = 1;
            else if (a > 70 || b > 70 || c > 70)
                ret = recursive(70, 70, 70);
            else if (a < b && b < c)
                ret = recursive(a, b, c - 1) + recursive(a, b - 1, c - 1) - recursive(a, b - 1, c);
            else
                ret = recursive(a - 1, b, c) + recursive(a - 1, b - 1, c) + recursive(a - 1, b, c - 1) - recursive(a - 1, b - 1, c - 1);

            dp[a][b][c] = ret;
        }

        return ret;
    }

    private static String makeOutput(int a, int b, int c, int result) {
        return String.format("w(%d, %d, %d) = %d", a - 50, b - 50, c - 50, result);
    }
}