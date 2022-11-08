import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * G2
 * 1167 - 트리의 지름
 * https://www.acmicpc.net/problem/1167
 */
public class Main {
	
	private static class Node {
        int v;
        int dist;

        public Node(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }
    }

    private static int v;
    private static List<Node>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        v = Integer.parseInt(br.readLine());

        adj = new List[v + 1];
        for (int i=1; i<=v; i++) {
        	adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= v; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            while (to != -1) {
                int dist = Integer.parseInt(st.nextToken());
                adj[from].add(new Node(to, dist));

                to = Integer.parseInt(st.nextToken());
            }
        }

        Node v1 = dfs(1, 0, new boolean[v + 1]);
        System.out.println(dfs(v1.v, 0, new boolean[v  +1]).dist);
    }

    private static Node dfs(int cur, int dist, boolean[] visit) { // return (시작 정점으로부터 가장 먼 정점, 가장 먼 정점까지의 거리)
        visit[cur] = true;
        Node ret = new Node(cur, dist);

        for (Node next : adj[cur]) {
            if (!visit[next.v]) {
                Node result = dfs(next.v, dist + next.dist, visit);
                if (result.dist > ret.dist) {
                    ret = result;
                }
            }
        }

        return ret;
    }
}
