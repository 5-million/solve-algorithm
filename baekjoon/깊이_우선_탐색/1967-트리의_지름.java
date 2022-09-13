import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * G4
 * 1967 - 트리의 지름
 * https://www.acmicpc.net/problem/1967
 */
public class Main {

    private static class Child {
        int node;
        int weight;

        public Child(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    private static int n;
    private static List<Child>[] conn;
    private static int[] diameter;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        diameter = new int[n + 1];
        conn = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            conn[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            conn[parent].add(new Child(child, weight));
        }

        dfs(1);

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (ans < diameter[i]) {
                ans = diameter[i];
            }
        }
        System.out.println(ans);
    }

    private static int dfs(int node) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (Child child : conn[node]) {
            pq.add(child.weight + dfs(child.node));
        }

        int ret = pq.isEmpty() ? 0 : pq.peek();

        int cnt = 0, dia = 0;
        while (cnt < 2 && !pq.isEmpty()) {
            dia += pq.poll();
            cnt++;
        }
        diameter[node] = dia;

        return ret;
    }
}