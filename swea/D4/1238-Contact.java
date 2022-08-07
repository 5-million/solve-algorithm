import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * D4
 * 1238 - Contact
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AV15B1cKAKwCFAYD
 */
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int tc = 1; tc <= 10; tc++) {
            int[][] network = new int[101][101];
            boolean[] v = new boolean[101];
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                network[from][to] = 1;
            }

            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            v[start] = true;
            int answer = 0;
            while (!queue.isEmpty()) {
                int qSize = queue.size();

                answer = 0;
                for (int i = 0; i < qSize; i++) {
                    int now = queue.poll();
                    answer = Math.max(answer, now);

                    for (int next = 1; next < 101; next++) {
                        if (network[now][next] == 1 && !v[next]) {
                            v[next] = true;
                            queue.add(next);
                        }
                    }
                }
            }

            System.out.printf("#%d %d\n", tc, answer);
        }
    }
}