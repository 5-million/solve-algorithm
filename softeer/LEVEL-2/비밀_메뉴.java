import java.util.*;
import java.io.*;


public class Main {

    private static int m, n, k;
    private static int[] seq;
    private static int[] input;


    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken()); // 비밀 메뉴의 조작 개수
        n = Integer.parseInt(st.nextToken()); // 사용자가 누른 버튼의 개수
        k = Integer.parseInt(st.nextToken()); // 버튼의 개수

        seq = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        input = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        String answer = "normal";
        for (int i = 0; i <= n - m; i++) {
            if (input[i] == seq[0] && isSecretMenu(i)) {
                answer = "secret";
                break;
            }
        }

        System.out.println(answer);
    }

    private static boolean isSecretMenu(int idx) {
        for (int i = idx; i < idx + m; i++) {
            if (seq[i - idx] != input[i]) {
                return false;
            }
        }

        return true;
    }
}