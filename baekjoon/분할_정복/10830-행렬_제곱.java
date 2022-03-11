import java.util.Scanner;

/**
 * 10830 - 행렬 제곱
 * https://www.acmicpc.net/problem/10830
 */
public class Main {

    private static final int P = 1000;
    private static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        long b = sc.nextLong();

        long[][] matrix = new long[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = sc.nextInt();

        matrix = pow(matrix, b);

        StringBuilder sb = new StringBuilder();
        for (long[] row : matrix) {
            for (long elem : row) {
                sb.append(elem % P).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static long[][] pow(long[][] matrix, long b) {
        if (b == 1) return matrix;

        long[][] temp = pow(matrix, b / 2);
        long[][] ret = multiply(temp, temp);

        if (b % 2 == 0) return ret;
        else return multiply(ret, matrix);
    }

    private static long[][] multiply(long[][] m1, long[][] m2) {
        long[][] temp = new long[n][n];

        for (int row = 0; row < n; row++)
            for (int col = 0; col < n; col++)
                for (int k = 0; k < n; k++)
                    temp[row][col] += m1[row][k] * m2[k][col] % P;
                
        return temp;
    }
}

