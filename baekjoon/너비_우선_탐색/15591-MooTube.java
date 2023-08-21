import java.io.*;
import java.util.*;

public class Main {

	private static List<int[]>[] network;
	private static int n, q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());

		network = new List[n + 1];
		for (int i = 1; i <= n; i++) {
			network[i] = new ArrayList<>();
		}

		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int pv = Integer.parseInt(st.nextToken());
			int qv = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			network[pv].add(new int[] {qv, r});
			network[qv].add(new int[] {pv, r});
		}

		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			System.out.println(solution(v, k));
		}
	}

	private static int solution(int video, int k) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[] visit = new boolean[n + 1];

		q.add(new int[] {video, Integer.MAX_VALUE});
		visit[video] = true;

		int count = -1;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			int v = now[0];
			int u = now[1];

			if (u >= k) {
				count++;
			}

			for (int[] next : network[v]) {
				int nv = next[0];
				int nu = next[1];

				if (!visit[nv]) {
					visit[nv] = true;
					q.add(new int[] {nv, Math.min(u, nu)});
				}
			}
		}

		return count;
	}
}
