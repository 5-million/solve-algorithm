import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static final int HORIZONTAL = 0;
	private static final int VERTICAL = 1;
	private static final int DIAGONAL = 2;

	private static int n;
	private static int[][] home;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		home = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				home[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(dfs(0, 1, HORIZONTAL));
	}

	private static int dfs(int y, int x, int d) {
		if (!checkRange(y, x) || home[y][x] == 1)
			return 0;

		if (y == n - 1 && x == n - 1)
			return 1;

		int ret = 0;
		if (canGoDiagonal(y, x))
			ret += dfs(y + 1, x + 1, DIAGONAL);

		if (d == HORIZONTAL) {
			ret += dfs(y, x + 1, d);
		} else if (d == VERTICAL) {
			ret += dfs(y + 1, x, d);
		} else {
			ret += dfs(y, x + 1, HORIZONTAL);
			ret += dfs(y + 1, x, VERTICAL);
		}

		return ret;
	}

	private static boolean checkRange(int y, int x) {
		return 0 <= y && y < n && 0 <= x && x < n;
	}

	private static boolean canGoDiagonal(int y, int x) {
		int[][] dir = { { 0, 1 }, { 1, 1 }, { 1, 0 } };
		for (int[] d : dir) {
			int ny = y + d[0];
			int nx = x + d[1];

			if (!checkRange(ny, nx)) {
				return false;
			}
			
			if (home[ny][nx] == 1) {
				return false;
			}
		}
		return true;
	}
}
