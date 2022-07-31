import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * D4
 * 1249 - 보급로
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15QRX6APsCFAYD
 */
public class Solution {

    private static class Node implements Comparable<Node> {
        int r;
        int c;
        int dist;

        public Node(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    private static int[][] map;
    private static int n;
    private static boolean[][] visit;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int tc = 1; tc <= t; tc++) {
            n = sc.nextInt();
            map = new int[n][n];
            for (int r = 0; r < n; r++) {
                map[r] = Arrays.stream(sc.next().split("")).mapToInt(Integer::parseInt).toArray();
            }

            System.out.printf("#%d %d\n", tc, dijkstra());
        }
    }

    public static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, 0));
        int[][] dist = new int[n][n];
        for (int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }

        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.dist > dist[now.r][now.c]) {
                continue;
            }

            for (int[] d : dir) {
                int nr = now.r + d[0];
                int nc = now.c + d[1];

                if (checkRange(nr, nc) && now.dist + map[nr][nc] < dist[nr][nc]) {
                    dist[nr][nc] = now.dist + map[nr][nc];
                    pq.add(new Node(nr, nc, dist[nr][nc]));
                }
            }
        }

        return dist[n - 1][n - 1];
    }

    public static boolean checkRange(int r, int c) {
        if (0 <= r && r < n && 0 <= c && c < n)
            return true;
        return false;
    }
}