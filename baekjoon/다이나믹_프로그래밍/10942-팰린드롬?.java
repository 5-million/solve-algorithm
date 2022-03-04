import java.util.Scanner;

/**
 * 10942 - 팰린드롬?
 * https://www.acmicpc.net/problem/10942
 */
public class Main {

    private static final int[] sequence = new int[2001];
    private static final boolean[][] palindrome = new boolean[2001][2001];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++)
            sequence[i] = sc.nextInt();

        set(n);

        int m = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            if (palindrome[start][end]) sb.append("1\n");
            else sb.append("0\n");
        }

        System.out.println(sb);
    }

    private static void set(int n) {
        for (int i = 1; i <= n; i++) {
            palindrome[i][i] = true;
            if (sequence[i - 1] == sequence[i])
                palindrome[i - 1][i] = true;
        }

        for (int i = 2; i < n; i++) {
            for (int j = 1; j <= n - i; j++) {
                if (sequence[j] == sequence[j + i] && palindrome[j + 1][j + i - 1])
                    palindrome[j][j + i] = true;
            }
        }
    }
}