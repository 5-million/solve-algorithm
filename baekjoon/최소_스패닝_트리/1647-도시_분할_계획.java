import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * G4
 * 1647 - 도시 분할 계획
 * https://www.acmicpc.net/problem/1647
 */
public class Main {

    private static class E implements Comparable<E> {
        int h1;
        int h2;
        int cost;

        public E(int h1, int h2, int cost) {
            this.h1 = h1;
            this.h2 = h2;
            this.cost = cost;
        }

        @Override
        public int compareTo(E o) {
            return cost - o.cost;
        }
    }

    private static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        PriorityQueue<E> pq = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(st.nextToken());
            int h2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pq.add(new E(h1, h2, cost));
        }

        int ans = 0;
        int cost = 0;
        while (!pq.isEmpty()) {
            E e = pq.poll();
            if (union(parent, e)) {
                cost = e.cost;
                ans += cost;
            }
        }

        System.out.println(ans - cost);
    }

    private static boolean union(int[] parent, E e) {
        int p1 = findParent(parent, e.h1);
        int p2 = findParent(parent, e.h2);

        if (p1 != p2) {
            if (p1 < p2) parent[p2] = p1;
            else parent[p1] = p2;
            return true;
        } else return false;
    }

    private static int findParent(int[] parent, int home) {
        if (parent[home] != home) {
            parent[home] = findParent(parent, parent[home]);
        }
        return parent[home];
    }
}