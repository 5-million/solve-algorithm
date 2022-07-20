import java.util.Scanner;

/**
 * bronze1
 * 2999 - 비밀 이메일
 * https://www.acmicpc.net/problem/2999
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        int n = input.length();
        int r = 0, c = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                int tempC = n / i;

                if (i > tempC) break;

                r = i;
                c = tempC;
            }
        }

        char[][] matrix = new char[r][c];
        int strIdx = 0;
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                matrix[j][i] = input.charAt(strIdx++);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                sb.append(matrix[i][j]);
            }
        }
        System.out.println(sb);
    }
}