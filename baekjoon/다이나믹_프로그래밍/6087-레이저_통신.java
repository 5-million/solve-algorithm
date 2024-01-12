import java.io.*;
import java.util.*;

public class Main {

	private static int w, h;
	private static char[][] map;
	private static int[][] points = new int[2][2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new char[h][w];

		int cnt = 0;
		for (int i = 0; i < h; i++) {
			char[] chars = br.readLine().toCharArray();
			for (int j = 0; j < w; j++) {
				map[i][j] = chars[j];

				if (map[i][j] == 'C') {
					points[cnt][0] = i;
					points[cnt][1] = j;
					cnt++;
				}
			}
		}

		System.out.println(getMinimumMirrorCount());
	}

	private static int getMinimumMirrorCount() {
		int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		int[][][] visit = new int[4][h][w];
		for (int d = 0; d < 4; d++) {
			for (int i = 0; i < h; i++) {
				Arrays.fill(visit[d][i], Integer.MAX_VALUE);
			}
			visit[d][points[0][0]][points[0][1]] = 0;
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();

		for (int d = 0; d < 4; d++) {
			int ny = dir[d][0] + points[0][0];
			int nx = dir[d][1] + points[0][1];

			if (checkRange(ny, nx) && map[ny][nx] == '.') {
				pq.add(new Node(new int[] {ny, nx}, d));
				visit[d][ny][nx] = 0;
			}
		}

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			int curY = cur.point[0];
			int curX = cur.point[1];
			if (cur.mirrorCount > visit[cur.d][curY][curX]) {
				continue;
			}

			for (int d = 0; d < 4; d++) {
				int ny = dir[d][0] + curY;
				int nx = dir[d][1] + curX;

				int nextMirrorCount = cur.mirrorCount + (cur.d % 2 == d % 2 ? 0 : 1);
				if (checkRange(ny, nx) && map[ny][nx] != '*' && nextMirrorCount < visit[d][ny][nx]) {
					pq.add(new Node(new int[] {ny, nx}, d, nextMirrorCount));
					visit[d][ny][nx] = nextMirrorCount;
				}
			}
		}

		int minimumMirrorCount = Integer.MAX_VALUE;
		for (int d = 0; d < 4; d++) {
			minimumMirrorCount = Math.min(minimumMirrorCount, visit[d][points[1][0]][points[1][1]]);
		}
		return minimumMirrorCount;
	}

	private static boolean checkRange(int y, int x) {
		return 0 <= y && y < h && 0 <= x && x < w;
	}

	private static class Node implements Comparable<Node> {
		int[] point = new int[2]; // 현재 위치
		int d; // 현재 진행 방향
		int mirrorCount; // 90도로 꺾이는 횟수 = 거울 갯수

		public Node(int[] point, int d) {
			this.point = point;
			this.d = d;
		}

		public Node(int[] point, int d, int mirrorCount) {
			this.point = point;
			this.d = d;
			this.mirrorCount = mirrorCount;
		}

		@Override
		public int compareTo(Node node) {
			return Integer.compare(this.mirrorCount, node.mirrorCount);
		}
	}
}
