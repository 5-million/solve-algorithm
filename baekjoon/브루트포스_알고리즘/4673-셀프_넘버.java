/**
 * 4673 - 셀프 넘버
 * https://www.acmicpc.net/problem/4673
 */
public class Main {

    private static final int MAX = 10001;

    public static void main(String[] args) {
        boolean[] d = new boolean[MAX];
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < MAX; i++) {
            if (!d[i]) sb.append(i).append("\n");

            int temp = i;
            int created = temp;
            while (temp != 0) {
                created += temp % 10;
                temp /= 10;
            }

            if (created < MAX) d[created] = true;
        }

        System.out.print(sb);
    }
}
