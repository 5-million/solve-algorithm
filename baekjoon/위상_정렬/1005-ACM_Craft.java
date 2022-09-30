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
 * 1005 - ACM Craft
 * https://www.acmicpc.net/problem/1005
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		while (t-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			int[] buildTime = new int[n + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				buildTime[i] = Integer.parseInt(st.nextToken());
			}
			
			List<Integer>[] adj = new List[n + 1];
			for (int i=0; i<=n; i++) {
				adj[i] = new ArrayList<>();
			}
			
			int[] inDegree = new int[n + 1];
			while (k-- > 0) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adj[from].add(to);
				inDegree[to]++;
			}
			
			int dest = Integer.parseInt(br.readLine());
			
			System.out.println(topologySort(adj, inDegree, buildTime, dest));
		}
	}
	
	private static int topologySort(List<Integer>[] adj, int[] inDegree, int[] buildTime, int dest) {
		Queue<Integer> q = new ArrayDeque<>();
		int[] time = buildTime.clone();
		for (int i=1; i<inDegree.length; i++) {
			if (inDegree[i] == 0) {
				q.add(i);
			}
		}
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int next : adj[cur]) {
				inDegree[next]--;
				time[next] = Math.max(time[next], time[cur] + buildTime[next]);
				
				if (inDegree[next] == 0) {
					q.add(next);
				}
			}
		}
		
		
		return time[dest];
	}
}
