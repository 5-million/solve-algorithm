import java.util.*;

class Solution {
    
    private static final int MOD = 20170805;
	
	public int solution(int m, int n, int[][] cityMap) {
		int[][] map = new int[m + 1][n + 1];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				map[i + 1][j + 1] = cityMap[i][j];
			}
		}

		Queue<int[]> que = new ArrayDeque<>();
		boolean[][] visit = new boolean[m + 1][n + 1];
		int[][][] dp = new int[m + 1][n + 1][2];

		if (m == 1 && n == 1) {
			return 1;
		}

		if (m > 1) {
			que.add(new int[] {2, 1});
			dp[2][1][0] = 1;
		}

		if (n > 1) {
			que.add(new int[] {1, 2});
			dp[1][2][1] = 1;
		}

		int[][] dir = {{0, 1}, {1, 0}};
		while (!que.isEmpty()) {
			int[] now = que.poll();

			for (int[] d : dir) {
				int ny = now[0] + d[0];
				int nx = now[1] + d[1];

				if (ny <= m && nx <= n && !visit[ny][nx] && map[ny][nx] != 1) {
					dp[ny][nx][0] = map[ny - 1][nx] == 2 ? dp[ny - 1][nx][0] : (dp[ny - 1][nx][0] + dp[ny - 1][nx][1]) % MOD;
					dp[ny][nx][1] = map[ny][nx - 1] == 2 ? dp[ny][nx - 1][1] : (dp[ny][nx - 1][0] + dp[ny][nx - 1][1]) % MOD;

					que.add(new int[] {ny, nx});
					visit[ny][nx] = true;
				}
			}
		}

		return (dp[m][n][0] + dp[m][n][1]) % MOD;
	}
}