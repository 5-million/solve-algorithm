import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * P4
 * 9376 - 탈옥
 * https://www.acmicpc.net/problem/9376
 */
public class Main {

	private static class Pos implements Comparable<Pos> {
		int y;
		int x;
		int open;

		public Pos(int y, int x, int open) {
			this.y = y;
			this.x = x;
			this.open = open;
		}

		@Override
		public int compareTo(Pos o) {
			return Integer.compare(open, o.open);
		}
	}

	private static int h, w;
	private static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		while (t-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			map = new char[h + 2][w + 2];

			for (int i = 0; i < h + 2; i++) {
				Arrays.fill(map[i], '.');
			}

			List<Pos> prisoner = new ArrayList<>();
			prisoner.add(new Pos(0, 0, 0));
			for (int i = 1; i <= h; i++) {
				String input = br.readLine();
				for (int j = 1; j <= w; j++) {
					map[i][j] = input.charAt(j - 1);
					if (map[i][j] == '$')
						prisoner.add(new Pos(i, j, 0));
				}
			}

			int[][] result = new int[h + 2][w + 2];
			for (Pos p : prisoner) {
				bfs(p, result);
			}
			
			int ans = Integer.MAX_VALUE;
			for (int i = 1; i <= h; i++) {
				for (int j = 1; j <= w; j++) {
					if (map[i][j] == '#') {
						ans = Math.min(ans, result[i][j] - 2);
					}
				}
			}
			
			System.out.println(Math.min(ans, result[0][0]));
		}
	}

	private static void bfs(Pos start, int[][] result) {
		int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		boolean[][] v = new boolean[h + 2][w + 2];

		pq.add(start);
		v[start.y][start.x] = true;

		while (!pq.isEmpty()) {
			Pos now = pq.poll();

			result[now.y][now.x] += now.open;

			for (int[] d : dir) {
				int ny = now.y + d[0];
				int nx = now.x + d[1];

				if (checkRange(ny, nx) && !v[ny][nx] && map[ny][nx] != '*') {
					int nextOpen = now.open;
					v[ny][nx] = true;

					if (map[ny][nx] == '#') {
						nextOpen++;
					}

					pq.add(new Pos(ny, nx, nextOpen));
				}
			}
		}
	}

	private static boolean checkRange(int y, int x) {
		return 0 <= y && y < h + 2 && 0 <= x && x < w + 2;
	}
}
