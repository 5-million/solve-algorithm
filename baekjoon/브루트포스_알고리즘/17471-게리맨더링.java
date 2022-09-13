import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * G4
 * 17471 - 게리맨더링
 * https://www.acmicpc.net/problem/17471
 */
public class Main {

    private static final int INF = Integer.MAX_VALUE;

    private static int n;
    private static int[] population;
    private static List<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        population = new int[n + 1];
        adj = new List[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            population[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                adj[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        int result = dfs(new boolean[n + 1], 1, 0);
        System.out.println(result == INF ? -1 : result);
    }

    private static int dfs(boolean[] sel, int idx, int k) {
        int ret = INF;
        if (idx == n + 1) return INF;

        if (k > 0 && isTwoGroup(sel)) {
            ret = calDiff(sel);
        }

        sel[idx] = true;
        ret = Math.min(ret, dfs(sel, idx + 1, k + 1));
        sel[idx] = false;
        return Math.min(ret, dfs(sel, idx + 1, k));
    }

    private static boolean isTwoGroup(boolean[] sel) {
        List<Integer>[] groups = new List[2];
        for (int i = 0; i < 2; i++) {
            groups[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            if (sel[i]) groups[0].add(i);
            else groups[1].add(i);
        }

        boolean[] v = new boolean[n + 1];
        bfs(groups[0].get(0), v, groups[0]);
        bfs(groups[1].get(0), v, groups[1]);

        for (int i = 1; i <= n; i++) {
            if (!v[i]) return false;
        }
        return true;
    }

    private static void bfs(int start, boolean[] v, List<Integer> group) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        v[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Integer next : adj[cur]) {
                if (!v[next] && group.contains(next)) {
                    q.add(next);
                    v[next] = true;
                }
            }
        }
    }

    private static int calDiff(boolean[] sel) {
        int g1 = 0, g2 = 0;
        for (int i = 1; i <= n; i++) {
            if (sel[i]) g1 += population[i];
            else g2 += population[i];
        }
        return Math.abs(g1 - g2);
    }
}