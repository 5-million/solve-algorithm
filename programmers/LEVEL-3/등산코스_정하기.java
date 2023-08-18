import java.util.*;

class Solution {
    
    private int n;
	private int[] gates, summits;
	private List<int[]>[] connects;

	public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		this.n = n;
		this.gates = gates;
		this.summits = summits;

		connects = new List[n + 1];
		for (int i = 1; i <= n; i++) {
			connects[i] = new ArrayList<>();
		}

		for (int[] path : paths) {
			int p1 = path[0];
			int p2 = path[1];
			int intensity = path[2];

			connects[p1].add(new int[] {p2, intensity});
			connects[p2].add(new int[] {p1, intensity});
		}

		int[] intensity = dijkstra();
		int[] answer = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
		for (int summit : summits) {
			if (answer[1] > intensity[summit]) {
				answer[0] = summit;
				answer[1] = intensity[summit];
			} else if (answer[0] > summit && answer[1] == intensity[summit]) {
				answer[0] = summit;
			}
		}

		return answer;
	}

	private int[] dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
			if (a[1] != b[1]) {
				return a[1] - b[1];
			} else return a[0] - b[0];
		});
		int[] intensity = new int[n + 1];
		Arrays.fill(intensity, Integer.MAX_VALUE);

		for (int gate : gates) {
			intensity[gate] = 0;
			pq.add(new int[] {gate, -1});
		}

		while (!pq.isEmpty()) {
			int[] now = pq.poll();
			int point = now[0];

			if (isSummit(point) || intensity[point] < now[1]) {
				continue;
			}

			for (int[] next : connects[point]) {
				int nextIntensity = Math.max(now[1], next[1]);
				if (intensity[next[0]] > nextIntensity) {
					intensity[next[0]] = nextIntensity;
					pq.add(new int[] {next[0], nextIntensity});
				}
			}
		}

		return intensity;
	}

	private boolean isSummit(int point) {
		for (int summit : summits) {
			if (point == summit) {
				return true;
			}
		}
		return false;
	}
}