import java.util.Scanner;

/**
 * B2
 * 8320 - 직사각형을 만드는 방법
 * https://www.acmicpc.net/problem/8320
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int answer = n;
        boolean[] width = new boolean[n + 1];
        for (int w = 2; w < n / 2; w++) {
            for (int h = 2; w * h <= n; h++) {
                if (!width[h])
                    answer++;
            }
            width[w] = true;
        }

        System.out.println(answer);
    }
}