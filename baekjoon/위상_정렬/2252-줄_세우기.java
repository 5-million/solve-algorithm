import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * G3
 * 2252 - 줄 세우기
 * https://www.acmicpc.net/problem/2252
 */
public class Main {

	private static int[] indegree;
	private static List<Integer>[] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		indegree = new int[n + 1];
		adj = new List[n + 1];
		for (int i = 0; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adj[a].add(b);
            indegree[b]++;
		}

		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				q.add(i);
			}
		}

		topology(q);
	}

	private static void topology(Queue<Integer> q) {
		StringBuilder sb = new StringBuilder();
		while (!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur).append(" ");

			for (int next : adj[cur]) {
				indegree[next]--;
				if (indegree[next] == 0) {
					q.add(next);
				}
			}
		}
		System.out.println(sb);
	}
}
