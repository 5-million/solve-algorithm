import java.io.*;
import java.util.*;

public class Main {

    private static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	private static int n, m; // 행, 열
	private static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];

		for (int r = 0; r < n; r++) {
			char[] input = br.readLine().toCharArray();
			for (int c = 0; c < m; c++) {
				map[r][c] = input[c] - '0';
			}
		}

		int[] section = setSection();
		boolean[] visit = new boolean[section.length];
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if (map[r][c] != 1) {
					sb.append(0);
				} else {
					int sectionCnt = 1;

					for (int[] d : DIR) {
						int nr = r + d[0];
						int nc = c + d[1];

						if (checkRange(nr, nc) && map[nr][nc] != 1 && !visit[map[nr][nc]]) {
							visit[map[nr][nc]] = true;
							sectionCnt += section[map[nr][nc]];
						}
					}

					for (int[] d : DIR) {
						int nr = r + d[0];
						int nc = c + d[1];

						if (checkRange(nr, nc)) {
							visit[map[nr][nc]] = false;
						}
					}

					sb.append(sectionCnt % 10);
				}
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}

	private static int[] setSection() {
		int sectionNum = 2;
		int[] section = new int[(n * m) / 2 + 3];

		for (int r = 0; r < n; r++) {
			for (int c = 0; c < m; c++) {
				if (map[r][c] == 0) {
					section[sectionNum] = bfs(r, c, sectionNum++);
				}
			}
		}

		return section;
	}

	// 영역에 포함된 공간을 sectionNum으로 바꾸고 총 영역개수 리턴
	private static int bfs(int sr, int sc, int sectionNum) {
		int sectionCount = 0;
		Queue<int[]> que = new ArrayDeque<>();

		que.add(new int[] {sr, sc});
		map[sr][sc] = sectionNum;

		while (!que.isEmpty()) {
			int[] cur = que.poll();
			sectionCount++;

			for (int[] d : DIR) {
				int nr = cur[0] + d[0];
				int nc = cur[1] + d[1];

				if (checkRange(nr, nc) && map[nr][nc] == 0) {
					map[nr][nc] = sectionNum;
					que.add(new int[] {nr, nc});
				}
			}
		}

		return sectionCount;
	}

	private static boolean checkRange(int r, int c) {
		return 0 <= r && r < n && 0 <= c && c < m;
	}
}
