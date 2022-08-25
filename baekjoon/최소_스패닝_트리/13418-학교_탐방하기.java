import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * G3
 * 13418 - 학교 탐방하기
 * https://www.acmicpc.net/problem/13418
 */
public class Main {

    private static class Road {
        int a;
        int b;
        int c;

        public Road(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    private static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken()) + 1;

        int[][] parent = new int[2][n + 1];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j <= n; j++) {
                parent[i][j] = j;
            }
        }

        PriorityQueue<Road>[] pq = new PriorityQueue[2];
        pq[0] = new PriorityQueue<>((o1, o2) -> o2.c - o1.c); // 최선
        pq[1] = new PriorityQueue<>(Comparator.comparingInt(o -> o.c)); // 최악

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            Road road = new Road(a, b, c);
            pq[0].add(road);
            pq[1].add(road);
        }

        int best = 0, worst = 0;
        while (m-- > 0) {
            best += union(parent[0], pq[0].poll());
            worst += union(parent[1], pq[1].poll());
        }
        System.out.println(squared(worst) - squared(best));
    }

    private static int findParent(int[] parent, int node) {
        if (parent[node] != node) {
            parent[node] = findParent(parent, parent[node]);
        }
        return parent[node];
    }

    private static int union(int[] parent, Road road) {
        int ap = findParent(parent, road.a);
        int bp = findParent(parent, road.b);

        if (ap != bp) {
            if (ap > bp) parent[ap] = bp;
            else parent[bp] = ap;
            return road.c == 0 ? 1 : 0;
        }

        return 0;
    }

    private static int squared(int number) {
        return number * number;
    }
}