import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * G5
 * 7569 - 토마토
 * https://www.acmicpc.net/problem/7569
 */
public class Main {

	private static final int[][] dir = { // 위, 아래, 왼쪽, 오른쪽, 앞, 뒤
			{ 1, 0, 0 }, { -1, 0, 0 }, { 0, 0, -1 }, { 0, 0, 1 }, { 0, 1, 0 }, { 0, -1, 0 } };

	private static class Pos {
		int z;
		int y;
		int x;

		public Pos(int z, int y, int x) {
			this.y = y;
			this.x = x;
			this.z = z;
		}
	}

	private static int m, n, h;
	private static int[][][] store;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		store = new int[h][n][m];

		Queue<Pos> q = new LinkedList<>();
		for (int z = 0; z < h; z++) {
			for (int y = 0; y < n; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < m; x++) {
					store[z][y][x] = Integer.parseInt(st.nextToken());
					if (store[z][y][x] == 1) {
						q.add(new Pos(z, y, x));
					}
				}
			}
		}

		int ans = bfs(q);

		for (int z = 0; z < h; z++) {
			for (int y = 0; y < n; y++) {
				for (int x = 0; x < m; x++) {
					if (store[z][y][x] == 0) {
						System.out.println(-1);
						return;
					}
				}
			}
		}
		
		System.out.println(ans);
	}

	private static int bfs(Queue<Pos> q) {
		int day = -1;
		while (!q.isEmpty()) {
			int qSize = q.size();
			day++;

			for (int i = 0; i < qSize; i++) {
				Pos cur = q.poll();

				for (int[] d : dir) {
					int nz = cur.z + d[0];
					int ny = cur.y + d[1];
					int nx = cur.x + d[2];

					if (checkRange(nz, ny, nx) && store[nz][ny][nx] == 0) {
						store[nz][ny][nx] = 1;
						q.add(new Pos(nz, ny, nx));
					}
				}
			}
		}

		return day;
	}

	private static boolean checkRange(int z, int y, int x) {
		return 0 <= z && z < h && 0 <= y && y < n && 0 <= x && x < m;
	}
}
