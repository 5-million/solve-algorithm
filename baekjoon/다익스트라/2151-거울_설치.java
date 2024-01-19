import java.io.*;
import java.util.*;

public class Main {

	private static class Node implements Comparable<Node> {
		int y;
		int x;
		int direction;
		int mirrorCount;

		public Node(int y, int x, int mirrorCount, int direction) {
			this.y = y;
			this.x = x;
			this.mirrorCount = mirrorCount;
			this.direction = direction;
		}

		@Override
		public int compareTo(Node o) {
			return this.mirrorCount - o.mirrorCount;
		}
	}

	private static final char INSTALLABLE_POSITION = '!', EMPTY = '.', WALL = '*';
	private static int n;
	private static char[][] homeInfo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 집의 크기 2 <= n <= 50

		int[][] doors = new int[2][2];
		int doorsIdx = 0;

		homeInfo = new char[n][n];
		for (int i = 0; i < n; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 0; j < n; j++) {
				homeInfo[i][j] = input[j];
				if (homeInfo[i][j] == '#') {
					doors[doorsIdx++] = new int[] {i, j};
				}
			}
		}

		System.out.println(solution(doors[0], doors[1]));
	}

	private static int solution(int[] start, int[] destination) {
		int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

		int[][][] mirrorCount = new int[4][n][n];
		for (int d = 0; d < 4; d++) {
			for (int i = 0; i < n; i++) {
				Arrays.fill(mirrorCount[d][i], Integer.MAX_VALUE);
			}
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int d = 0; d < 4; d++) {
			int ny = start[0] + dir[d][0];
			int nx = start[1] + dir[d][1];

			if (checkRange(ny, nx) && homeInfo[ny][nx] != WALL) {
				pq.add(new Node(ny, nx, 0, d));
				mirrorCount[d][ny][nx] = 0;
			}
		}

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			if (cur.y == destination[0] && cur.x == destination[1]) {
				break;
			}

			if (mirrorCount[cur.direction][cur.y][cur.x] < cur.mirrorCount) {
				continue;
			}

			// 진행방향 그대로 가는 경우
			int ny = cur.y + dir[cur.direction][0];
			int nx = cur.x + dir[cur.direction][1];
			if (checkRange(ny, nx) && homeInfo[ny][nx] != WALL && cur.mirrorCount < mirrorCount[cur.direction][ny][nx]) {
				pq.add(new Node(ny, nx, cur.mirrorCount, cur.direction));
				mirrorCount[cur.direction][ny][nx] = cur.mirrorCount;
			}

			// 거울을 설치할 수 있는 곳인 경우
			if (homeInfo[cur.y][cur.x] == INSTALLABLE_POSITION) {
				for (int d = 0; d < 4; d++) {
					if (cur.direction % 2 == d) {
						continue;
					}

					ny = cur.y + dir[d][0];
					nx = cur.x + dir[d][1];
					if (checkRange(ny, nx) && homeInfo[ny][nx] != WALL && cur.mirrorCount + 1 < mirrorCount[d][ny][nx]) {
						pq.add(new Node(ny, nx, cur.mirrorCount + 1, d));
						mirrorCount[d][ny][nx] = cur.mirrorCount + 1;
					}
				}
			}
		}

		int ans = Integer.MAX_VALUE;
		for (int d = 0; d < 4; d++) {
			ans = Math.min(ans, mirrorCount[d][destination[0]][destination[1]]);
		}

		return ans;
	}

	private static boolean checkRange(int y, int x) {
		return 0 <= y && y < n && 0 <= x && x < n;
	}
}
