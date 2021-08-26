import java.util.Scanner;

public class Main {

    private static int t;
    private static int totalChapter;
    private static int[] chapters;
    private static int[] sum;
    private static int[][] dp;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        t = sc.nextInt();
        for (int i = 0; i < t; i++)
            System.out.println(solution());
    }

    private static int solution() {
        initChapter();
        for (int i = 1; i < totalChapter; i++) {
            for (int start = 1; i + start <= totalChapter; start++) {
                int end = i + start;
                dp[start][end] = Integer.MAX_VALUE;

                for (int mid = start; mid < end; mid++) {
                    dp[start][end] =
                            Math.min(dp[start][end], dp[start][mid] + dp[mid + 1][end] + sum[end] - sum[start - 1]);
                }
            }
        }

        return dp[1][totalChapter];
    }

    private static void initChapter() {
        totalChapter = sc.nextInt();

        chapters = new int[totalChapter + 1];
        sum = new int[totalChapter + 1];
        dp = new int[totalChapter + 1][totalChapter + 1];

        for (int i = 1; i < totalChapter + 1; i++) {
            chapters[i] = sc.nextInt();
            sum[i] = sum[i - 1] + chapters[i];
        }
    }
}