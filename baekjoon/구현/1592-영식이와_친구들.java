import java.util.Scanner;

/**
 * B1
 * 1592 - 영식이와 친구들
 * https://www.acmicpc.net/problem/1592
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int L = sc.nextInt();

        int[] receiveCnt = new int[n];
        int ballIdx = 0;
        receiveCnt[0] = 1;

        int throwCount = 0;
        while (true) {
            if (receiveCnt[ballIdx] == m)
                break;

            throwCount++;
            if (receiveCnt[ballIdx] % 2 == 1) {
                ballIdx = throwToLeft(ballIdx, n, L);
            } else {
                ballIdx = throwToRight(ballIdx, n, L);
            }

            receiveCnt[ballIdx]++;
        }

        System.out.println(throwCount);
    }

    private static int throwToLeft(int cur, int n, int L) {
        int to = cur - L;
        return to < 0 ? to + n : to;
    }

    private static int throwToRight(int cur, int n, int L) {
        return (cur + L) % n;
    }
}