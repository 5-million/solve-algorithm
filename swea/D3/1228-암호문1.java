import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * D3
 * 1228 - 암호문1
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14w-rKAHACFAYD
 */
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = 1; tc <= 10; tc++) {
            int n = Integer.parseInt(br.readLine());
            LinkedList<String> password = new LinkedList<>(Arrays.asList(br.readLine().split(" ")));
            int orderCnt = Integer.parseInt(br.readLine());
            String[] temp = br.readLine().split("I ");

            List<String> orders = new ArrayList<>();
            for (String o : temp) {
                if (o.length() != 0) {
                    orders.add(o);
                }
            }

            for (String o : orders) {
                String[] s = o.split(" ");
                int start = Integer.parseInt(s[0]);
                int cnt = Integer.parseInt(s[1]);
                int end = Math.min(start + cnt, 10);
                int idx = 2;
                for (int i = start; i < end; i++) {
                    password.add(i, s[idx++]);
                }
            }

            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                answer.append(password.get(i)).append(" ");
            }
            System.out.printf("#%d %s\n", tc, answer);
        }
    }
}
