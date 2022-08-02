import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * D4
 * 1219 - 길찾기
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AV14geLqABQCFAYD
 */
public class Solution {

    private static final int MAX_NODE_COUNT = 100;
    private static final int START = 0;
    private static final int DEST = 99;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int t = 0; t < 10; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int tc = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            List<Integer>[] edges = new List[MAX_NODE_COUNT];
            for (int i = 0; i < MAX_NODE_COUNT; i++) {
                edges[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                edges[from].add(to);
            }

            System.out.printf("#%d %d\n", tc, bfs(edges) ? 1 : 0);
        }
    }

    private static boolean bfs(List<Integer>[] edges) {
        Queue<Integer> qu = new LinkedList<>();
        boolean[] visit = new boolean[MAX_NODE_COUNT];
        qu.add(START);
        visit[START] = true;

        while (!qu.isEmpty()) {
            int now = qu.poll();
            for (Integer to : edges[now]) {
                if (to == DEST) return true;
                if (!visit[to]) {
                    visit[to] = true;
                    qu.add(to);
                }
            }
        }

        return false;
    }
}