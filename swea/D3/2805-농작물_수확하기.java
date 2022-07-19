import java.util.Arrays;
import java.util.Scanner;

/**
 * D3
 * 2805 - 농작물 수확하기
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7GLXqKAWYDFAXB
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int tc = 1; tc <= t; tc++) {
            int n = sc.nextInt();
            int[][] farm = new int[n][n];

            for (int i = 0; i < n; i++) {
                farm[i] = Arrays.stream(sc.next().split("")).mapToInt(Integer::parseInt).toArray();
            }

            int earn = 0;
            int empty = n / 2;
            for (int i = 0; i < n / 2; i++) {
                for (int j = empty; j < n - empty; j++) {
                    earn += farm[i][j];
                    earn += farm[n - i - 1][j];
                }
                empty--;
            }

            for (int i = 0; i < n; i++) {
                earn += farm[n / 2][i];
            }

            System.out.printf("#%d %d\n", tc, earn);
        }
    }
}