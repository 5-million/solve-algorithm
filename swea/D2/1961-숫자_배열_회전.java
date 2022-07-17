import java.util.Scanner;

/**
 * D2
 * 1961 - 숫자 배열 회전
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5Pq-OKAVYDFAUq
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int c = 1; c <= t; c++) {
            int n = sc.nextInt();
            StringBuilder output = new StringBuilder();
            int[][] arr = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            int[][] once = rotate(arr, n);
            int[][] twice = rotate(once, n);
            int[][] third = rotate(twice, n);

            System.out.println("#" + c);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    output.append(once[i][j]);
                }
                output.append(" ");
                for (int j = 0; j < n; j++) {
                    output.append(twice[i][j]);
                }
                output.append(" ");
                for (int j = 0; j < n; j++) {
                    output.append(third[i][j]);
                }
                output.append("\n");
            }
            System.out.print(output);
        }
    }

    private static int[][] rotate(int[][] arr, int n) {
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = arr[n - 1 - j][i];
            }
        }

        return temp;
    }
}