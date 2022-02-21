import java.util.Scanner;

/**
 * 다이나믹 프로그래밍 > 타일 채우기
 */
public class Boj2133 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        System.out.println(solution(n));
    }

    private static int solution(int n) {
        int[] dp = new int[31];
        dp[0] = 1;
        dp[2] = 3;

        for (int i = 4; i <= n; i += 2) {
            dp[i] = dp[i - 2] * dp[2];

            for (int j = i - 4; j >= 0; j -= 2)
                dp[i] += dp[j] * 2;
        }

        return dp[n];
    }
}