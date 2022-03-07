import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 11657 - 타임머신
 * https://www.acmicpc.net/problem/11657
 */
public class Main {

    private static class Edge {
        int a;
        int b;
        int time;

        public Edge(String edge) {
            String[] s = edge.split(" ");

            this.a = Integer.parseInt(s[0]);
            this.b = Integer.parseInt(s[1]);
            this.time = Integer.parseInt(s[2]);
        }
    }

    private static final long INF = Long.MAX_VALUE;
    private static final List<Edge> edges = new ArrayList<>();

    private static int n;
    private static int m;
    private static long[] time;

    public static void main(String[] args) throws IOException {
        init();

        if (bellmanFord()) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            if (time[i] == INF) sb.append(-1);
            else sb.append(time[i]);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        time = new long[n + 1];
        Arrays.fill(time, INF);

        for (int i = 0; i < m; i++)
            edges.add(new Edge(br.readLine()));
    }

    private static boolean bellmanFord() {
        time[1] = 0;
        for (int i = 0; i < n; i++) {
            for (Edge edge : edges) {
                if (time[edge.a] != INF && time[edge.a] + edge.time < time[edge.b]) {
                    time[edge.b] = time[edge.a] + edge.time;

                    if (i == n - 1)
                        return true;
                }
            }
        }

        return false;
    }
}