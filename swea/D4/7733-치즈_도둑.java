import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * D4
 * 7733 - 치즈 도둑
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWrDOdQqRCUDFARG
 */
public class Solution {

	private static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	private static int[][] map;
	private static int n;

	static class Pos {
		int y;
		int x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];

			for (int i = 0; i < n; i++) {
				map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			
			System.out.printf("#%d %d\n", tc, bf());
		}
	}

	private static int bf() {
		int ret = 0;
		for (int d = 0; d < 100; d++) {
			boolean[][] v = new boolean[n][n];
			int lump = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] > d && !v[i][j]) {
						dfs(new Pos(i, j), v, d);
						lump += 1;
					}
				}
			}
			
			ret = Math.max(ret, lump);
		}
		return ret;
	}
	
	private static void dfs(Pos cur, boolean[][] v, int day) {
		v[cur.y][cur.x] = true;
		
		for (int[] d : dir) {
			int ny = cur.y + d[0];
			int nx = cur.x + d[1];
			
			if (checkRange(ny, nx) && map[ny][nx] > day && !v[ny][nx]) {
				dfs(new Pos(ny, nx), v, day);
			}
		}
	}

	private static boolean checkRange(int y, int x) {
		return 0 <= y && y < n && 0 <= x && x < n;
	}
}
