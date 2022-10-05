import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * P5
 * 10217 - KCM Travel
 * https://www.acmicpc.net/problem/10217
 */
public class KCM_Travel_10217 {

	private static class Airport implements Comparable<Airport> {
		int port;
		int cost;
		int dist;

		public Airport(int port, int cost, int dist) {
			this.port = port;
			this.cost = cost;
			this.dist = dist;
		}

		@Override
		public int compareTo(Airport o) {
			return Integer.compare(dist, o.dist);
		}
	}

	private static int n, m, k;
	private static List<Airport>[] adj;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		while (t-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			adj = new List[n + 1];
			for (int i = 0; i <= n; i++) {
				adj[i] = new ArrayList<>();
			}

			while (k-- > 0) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());

				adj[u].add(new Airport(v, c, d));
			}

			int result = dijkstra();
			System.out.println(result == Integer.MAX_VALUE ? "Poor KCM" : result);
		}
	}

	private static int dijkstra() {
		PriorityQueue<Airport> pq = new PriorityQueue<>();
		int[][] dist = new int[n + 1][m + 1];
		for (int i = 0; i <= n; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}

		pq.add(new Airport(1, 0, 0));
		dist[1][0] = 0;

		while (!pq.isEmpty()) {
			Airport now = pq.poll();

			if (now.dist > dist[now.port][now.cost])
				continue;

			for (Airport next : adj[now.port]) {
				int nextDist = dist[now.port][now.cost] + next.dist;
				int nextCost = now.cost + next.cost;

				if (nextCost > m)
					continue;

				if (dist[next.port][nextCost] > nextDist) {
					dist[next.port][nextCost] = nextDist;
					pq.add(new Airport(next.port, nextCost, nextDist));
				}
			}
		}

		int ret = Integer.MAX_VALUE;
		for (int i = 0; i <= m; i++) {
			ret = Math.min(ret, dist[n][i]);
		}
		return ret;
	}
}
