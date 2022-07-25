import java.util.Scanner;

/**
 * S3
 * 1244 - 스위치 켜고 끄기
 * https://www.acmicpc.net/problem/1244
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] switches = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            int state = sc.nextInt();
            switches[i] = state == 1;
        }

        int studentCnt = sc.nextInt();
        for (int i = 0; i < studentCnt; i++) {
            int type = sc.nextInt();
            int switchNumber = sc.nextInt();

            if (type == 1) switchingByMan(switches, switchNumber);
            else switchingByWoman(switches, switchNumber);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < switches.length; i++) {
            sb.append(switches[i] ? 1 : 0).append(" ");
            if (i % 20 == 0) sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void switchingByMan(boolean[] switches, int n) {
        for (int i = 1; n * i < switches.length; i++) {
            int idx = n * i;
            switches[idx] = !switches[idx];
        }
    }

    private static void switchingByWoman(boolean[] switches, int n) {
        int head = n - 1, tail = n + 1;
        while (head > 0 && tail < switches.length && switches[head] == switches[tail]) {
            head--;
            tail++;
        }

        for (int i = head + 1; i < tail; i++) {
            switches[i] = !switches[i];
        }
    }
}