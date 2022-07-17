import java.util.Scanner;

/**
 * D2
 * 2001 - 파리 퇴치
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PzOCKAigDFAUq
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int i = 1; i <= t; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] arr = new int[n][n];

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    arr[j][k] = sc.nextInt();
                }
            }

            System.out.printf("#%d %d\n", i, solution(arr, n, m));
        }
    }

    private static int solution(int[][] arr, int n, int m) {
        int ans = 0;
        for (int i = 0; i <= n - m; i++) {
            for (int j = 0; j <= n - m; j++) {
                ans = Math.max(ans, countFly(arr, i, j, m));
            }
        }

        return ans;
    }

    private static int countFly(int[][] arr, int y, int x, int m) {
        int count = 0;
        for (int i = y; i < y + m; i++) {
            for (int j = x; j < x + m; j++) {
                count += arr[i][j];
            }
        }

        return count;
    }
}