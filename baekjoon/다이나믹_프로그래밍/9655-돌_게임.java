import java.util.Scanner;

/**
 * 9655 - 돌 게임
 * https://www.acmicpc.net/problem/9655
 */
public class Main {

    private static final int SK = 1;
    private static final int CY = -1;
    private static final int[] dp = new int[1001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(dfs(n, SK) == SK ? "SK" : "CY");;
    }

    private static int dfs(int n, int turn) {
        if (n == 3 || n == 1) return turn;
        if (dp[n] != 0) return dp[n];

        if (n > 3) dp[n] = dfs(n - 3, -turn);
        dp[n] = dfs(n - 1, -turn);
        return dp[n];
    }
}
