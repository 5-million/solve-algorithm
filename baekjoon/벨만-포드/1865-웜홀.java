import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1865 - 웜홀
 * https://www.acmicpc.net/problem/1865
 */
public class Main {

    private static final int INF = 5000000;

    private static class Connection {
        int s;
        int e;
        int t;

        public Connection(int s, int e, int t) {
            this.s = s;
            this.e = e;
            this.t = t;
        }
    }

    private static int n, m, w;
    private static List<Connection> connections = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (tc > 0) {
            tc--;

            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            connections.clear();

            for (int i = 0; i < m + w; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                if (i < m) {
                    connections.add(new Connection(s, e, t));
                    connections.add(new Connection(e, s, t));
                } else connections.add(new Connection(s, e, -1 * t));
            }

            sb.append(bellmanFord() ? "NO" : "YES").append("\n");
        }

        System.out.println(sb);
    }

    private static boolean bellmanFord() {
        int[] time = new int[n + 1];
        Arrays.fill(time, INF);
        
        for (int i = 0; i < n; i++) {
            for (Connection conn : connections) {
                if (conn.t + time[conn.s] < time[conn.e]) {
                    time[conn.e] = conn.t + time[conn.s];

                    if (i == n - 1)
                        return false;
                }
            }
        }

        return true;
    }
}