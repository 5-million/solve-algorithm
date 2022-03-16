import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * 14002 - 가장 긴 증가하는 부분 수열 4
 * https://www.acmicpc.net/problem/14002
 */
public class Main {

    private static int n;
    private static int[] seq, dp;

    public static void main(String[] args) {
        init();
        solution();
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        seq = new int[n];
        dp = new int[n];

        for (int i = 0; i < n; i++)
            seq[i] = sc.nextInt();
        Arrays.fill(dp, 1);
    }

    private static void solution() {
        int len = lis();
        System.out.println(len);

        int index = n - 1;
        Stack<Integer> stack = new Stack<>();
        while (len > 0) {
            if (dp[index] == len) {
                stack.add(seq[index]);
                len--;
            }
            index--;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop()).append(" ");

        System.out.println(sb);
    }

    private static int lis() {
        int len = 1;

        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (seq[j] < seq[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    len = Math.max(len, dp[i]);
                }
            }
        }

        return len;
    }
}

