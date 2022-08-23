import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * D4
 * 6109 - 추억의 2048게임
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWbrg9uabZsDFAWQ
 */
public class Solution {

	private static final int UP = 0;
	private static final int RIGHT = 1;
	private static final int DOWN = 2;
	private static final int LEFT = 3;
	private static final int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	private static int n;
	private static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			String s = st.nextToken();

			map = new int[n][n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			switch (s) {
			case "up":
				up();
				break;

			case "right":
				right();
				break;

			case "down":
				down();
				break;

			default:
				left();
				break;
			}

			System.out.printf("#%d\n", tc);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int num = map[i][j] <= 0 ? 0 : map[i][j];
					System.out.print(num + " ");
				}
				System.out.println();
			}
		}
	}

	private static void left() {
		for (int y = 0; y < n; y++) {
			for (int x = 1; x < n; x++) {
				moveTile(dir[LEFT], y, x);
			}
		}
	}

	private static void moveTile(int[] d, int y, int x) {
		if (map[y][x] <= 0)
			return;

		int ny = y + d[0];
		int nx = x + d[1];

		while (true) {
			if (map[ny][nx] == 0) {
				if (checkRange(ny + d[0], nx + d[1])) {
					ny += d[0];
					nx += d[1];
				} else {
					map[ny][nx] = map[y][x];
					map[y][x] = 0;
				}
			} else if (map[ny][nx] == -1) {
				map[ny][nx] = map[y][x];
				map[y][x] = 0;
			} else if (map[ny][nx] != map[y][x]) {
				int temp = map[y][x];
				map[y][x] = 0;
				map[ny - d[0]][nx - d[1]] = temp;
				break;
			} else {
				map[ny][nx] *= 2;
				map[y][x] = 0;
				map[ny - d[0]][nx - d[1]] = -1;
				break;
			}
		}
	}

	private static boolean checkRange(int y, int x) {
		return 0 <= y && y < n && 0 <= x && x < n;
	}

	private static void right() {
		for (int y = 0; y < n; y++) {
			for (int x = n - 2; x >= 0; x--) {
				moveTile(dir[RIGHT], y, x);
			}
		}
	}

	private static void up() {
		for (int x = 0; x < n; x++) {
			for (int y = 1; y < n; y++) {
				moveTile(dir[UP], y, x);
			}
		}
	}

	private static void down() {
		for (int x = 0; x < n; x++) {
			for (int y = n - 2; y >= 0; y--) {
				moveTile(dir[DOWN], y, x);
			}
		}
	}
}
