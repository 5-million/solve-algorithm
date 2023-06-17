import java.util.*;

class Solution {

    private static final char LEVER = 'L';
	private static final char EXIT = 'E';

	int n, m;
	private char[][] map;
	private int[] start, lever;

	public int solution(String[] maps) {
		init(maps);

		int timeToLever = bfs(start, LEVER);
		if (timeToLever == -1) {
			return -1;
		}

		int timeToExit = bfs(lever, EXIT);
		return timeToExit == -1 ? -1 : timeToLever + timeToExit;
	}

	private void init(String[] maps) {
		n = maps.length;
		m = maps[0].length();
		map = new char[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = maps[i].charAt(j);

				if (map[i][j] == 'S') {
					start = new int[] {i, j};
				} else if (map[i][j] == 'L') {
					lever = new int[] {i, j};
				}
			}
		}
	}

	private int bfs(int[] start, char dest) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[n][m];

		q.add(start);
		visit[start[0]][start[1]] = true;

		int time = 0;
		int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		while (!q.isEmpty()) {
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				int[] cur = q.poll();

				if (map[cur[0]][cur[1]] == dest) {
					return time;
				}

				for (int[] d : dir) {
					int ny = cur[0] + d[0];
					int nx = cur[1] + d[1];

					if (checkRange(ny, nx) && !visit[ny][nx] && map[ny][nx] != 'X') {
						visit[ny][nx] = true;
						q.add(new int[] {ny, nx});
					}
				}
			}

			time++;
		}

		return -1;
	}

	private boolean checkRange(int y, int x) {
		return 0 <= y && y < n && 0 <= x && x < m;
	}
}