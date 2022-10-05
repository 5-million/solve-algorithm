import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * G3
 * 1939 - 중량제한
 * https://www.acmicpc.net/problem/1939
 */
public class Main {

	private static class Bridge {
		int to;
		int limit;

		public Bridge(int to, int limit) {
			this.to = to;
			this.limit = limit;
		}
	}

	private static int n, m;
	private static int from, to;
	private static List<Bridge>[] conn;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		conn = new List[n + 1];
		for (int i = 0; i <= n; i++) {
			conn[i] = new ArrayList<>();
		}

		int max = 0;
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			max = Math.max(max, c);

			conn[a].add(new Bridge(b, c));
			conn[b].add(new Bridge(a, c));
		}

		st = new StringTokenizer(br.readLine());
		from = Integer.parseInt(st.nextToken());
		to = Integer.parseInt(st.nextToken());

		System.out.println(binarySearch(max));
	}

	private static int binarySearch(int max) {
		int ans = 0;
		int start = 1;
		int end = max;

		while (start <= end) {
			int mid = (start + end) / 2;
			if (bfs(mid)) {
				start = mid + 1;
				ans = mid;
			} else {
				end = mid - 1;
			}
		}

		return ans;
	}

	private static boolean bfs(int weight) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] v = new boolean[n + 1];

		q.add(from);
		v[from] = true;

		while (!q.isEmpty()) {
			int now = q.poll();

			if (now == to) {
				return true;
			}

			for (Bridge next : conn[now]) {
				if (!v[next.to] && next.limit >= weight) {
					v[next.to] = true;
					q.add(next.to);
				}
			}
		}

		return false;
	}
}
