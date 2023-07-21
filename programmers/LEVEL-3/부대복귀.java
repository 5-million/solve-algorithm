import java.util.*;

class Solution {
    
    private int n;
	private List<Integer>[] adj;

	public int[] solution(int n, int[][] roads, int[] sources, int destination) {
		this.n = n;
		this.adj = new List[n + 1];
		for (int i = 0; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int[] road : roads) {
			adj[road[0]].add(road[1]);
			adj[road[1]].add(road[0]);
		}

		int[] time = dijkstra(destination);
		int[] answer = new int[sources.length];
		for (int i = 0; i < sources.length; i++) {
			int start = sources[i];
			answer[i] = time[start] == Integer.MAX_VALUE ? -1 : time[start];
		}

		return answer;
	}

	private int[] dijkstra(int start) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		int[] time = new int[n + 1]; // 도착지에서 출발해 인덱스까지 걸리는 시간
		Arrays.fill(time, Integer.MAX_VALUE);

		pq.add(new int[] {start, 0});
		time[start] = 0;

		while (!pq.isEmpty()) {
			int[] now = pq.poll();

			if (time[now[0]] < now[1]) {
				continue;
			}

			for (Integer next : adj[now[0]]) {
				int cost = now[1] + 1;
				if (time[next] >= cost) {
					time[next] = cost;
					pq.add(new int[] {next, cost});
				}
			}
		}

		return time;
	}
}