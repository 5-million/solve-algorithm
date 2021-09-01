import java.util.*;

public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static int n;
    private static int m;
    private static int t;
    private static int s;
    private static int g;
    private static int h;
    private static List<Integer> candidate;
    private static int[][] map;

    private static class Node implements Comparable<Node>{
        int number;
        int cost;

        public Node(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) {
        int t = sc.nextInt();

        for (int i = 0; i < t; i++)
            solution();
    }

    private static void solution() {
        init();

        StringBuilder sb = new StringBuilder();
        for (int target : candidate) {
            int[] distFromS = dijkstra(s);
            int st = distFromS[target]; // start - target
            int sght = distFromS[g] + map[g][h] + dijkstra(h)[target]; // start - g - h - target
            int shgt = distFromS[h] + map[h][g] + dijkstra(g)[target]; // start - h - g - target

            if (st == sght || st == shgt) {
                sb.append(target);
                sb.append(" ");
            }
        }

        System.out.println(sb);
    }

    private static void init() {
        n = sc.nextInt();
        m = sc.nextInt();
        t = sc.nextInt();
        s = sc.nextInt();
        g = sc.nextInt();
        h = sc.nextInt();

        map = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int d = sc.nextInt();

            map[a][b] = d;
            map[b][a] = d;
        }

        candidate = new ArrayList<>();
        for (int i = 0; i < t; i++)
            candidate.add(sc.nextInt());
        candidate.sort(Integer::compareTo);
    }

    private static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.add(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.number] < now.cost)
                continue;

            for (int next = 1; next < n + 1; next++) {
                if (map[now.number][next] != 0 && dist[next] > now.cost + map[now.number][next]) {
                    dist[next] = now.cost + map[now.number][next];
                    pq.add(new Node(next, dist[next]));
                }
            }
        }

        return dist;
    }
}