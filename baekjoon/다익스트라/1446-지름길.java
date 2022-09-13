import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * S1
 * 1446 - 지름길
 * https://www.acmicpc.net/problem/1446
 */
public class Main {

    private static final int INF = Integer.MAX_VALUE;

    private static class Shortcut implements Comparable<Shortcut> {
        int pos;
        int dist;

        public Shortcut(int pos, int dist) {
            this.pos = pos;
            this.dist = dist;
        }

        @Override
        public int compareTo(Shortcut o) {
            return dist - o.dist;
        }
    }

    private static int n, d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        int[][] road = new int[d + 1][d + 1];
        for (int i = 0; i <= d; i++) {
            for (int j = 0; j <= d; j++) {
                road[i][j] = j - i;
            }
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            if (end > d) {
                continue;
            }

            road[start][end] = Math.min(road[start][end], dist);
        }

        System.out.println(dijkstra(road));
    }

    private static int dijkstra(int[][] road) {
        PriorityQueue<Shortcut> pq = new PriorityQueue<>();
        int[] dist = new int[road.length + 1];
        Arrays.fill(dist, INF);

        pq.add(new Shortcut(0, 0));
        dist[0] = 0;

        while (!pq.isEmpty()) {
            Shortcut cur = pq.poll();

            if (dist[cur.pos] < cur.dist)
                continue;

            for (int i = cur.pos + 1; i <= d; i++) {
                if (cur.dist + road[cur.pos][i] < dist[i]) {
                    dist[i] = cur.dist + road[cur.pos][i];
                    pq.add(new Shortcut(i, dist[i]));
                }
            }
        }

        return dist[d];
    }
}