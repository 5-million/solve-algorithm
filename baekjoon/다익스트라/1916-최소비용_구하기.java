import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    private static int n;
    private static int m;
    private static int start;
    private static int dest;
    private static ArrayList<Path>[] route;

    private static class Path implements Comparable<Path> {
        int city;
        int cost;

        public Path(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }

        @Override
        public int compareTo(Path o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) {
        init();
        System.out.println(dijkstra());
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        route = new ArrayList[n+1];
        for (int i = 0; i <= n; i++)
            route[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            route[from].add(new Path(to, cost));
        }

        start = sc.nextInt();
        dest = sc.nextInt();
    }

    private static int dijkstra() {
        PriorityQueue<Path> pq = new PriorityQueue<>();
        int[] costs = new int[n + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);

        pq.add(new Path(start, 0));
        costs[start] = 0;

        while (!pq.isEmpty()) {
            Path now = pq.poll();

            if (costs[now.city] < now.cost)
                continue;

            for (Path next : route[now.city]) {
                if (costs[next.city] > now.cost + next.cost) {
                    costs[next.city] = now.cost + next.cost;
                    pq.add(new Path(next.city, costs[next.city]));
                }
            }
        }

        return costs[dest];
    }
}