import java.util.Scanner;

/**
 * 10162 - 전자레인지
 * https://www.acmicpc.net/problem/10162
 */
public class Main {

    private static final int[] button = new int[]{300, 60, 10};

    public static void main(String[] args) {
        int t = new Scanner(System.in).nextInt();

        if (t % button[2] != 0) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int btn : button) {
                sb.append(t / btn).append(" ");
                t %= btn;
            }
            System.out.print(sb);
        }
    }
}