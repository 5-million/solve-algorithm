import java.io.*;
import java.util.*;

public class Main {

    private static final char MINERAL = 'x', EMPTY = '.';
	private static final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	private static int turn = 1; // d > 0 = 왼쪽에서 오른쪽, d < 0 = 오른쪽에서 왼쪽
	private static int r, c;
	private static char[][] cave;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		cave = new char[r + 1][c];
		for (int row = r; row > 0; row--) {
			cave[row] = br.readLine().toCharArray();
		}

		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			int h = Integer.parseInt(st.nextToken());

			int[] target = getTargetMineral(turn, h);
			if (target != null) {
				Cluster floatingCluster = getFloatingCluster(target);
				if (floatingCluster != null) {
					fall(floatingCluster);
				}
			}

			turn *= -1;
		}

		StringBuilder sb = new StringBuilder();
		for (int row = r; row > 0; row--) {
			for (int col = 0; col < c; col++) {
				sb.append(cave[row][col]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void fall(Cluster floatingCluster) {
		int down = Integer.MAX_VALUE;
		int[] floor = floatingCluster.floor;
		for (int i = 0; i < c; i++) {
			int height = floor[i];

			if (height == Integer.MAX_VALUE) {
				continue;
			}

			int temp = 1;
			while (height - temp > 0 && cave[height - temp][i] == EMPTY) {
				temp++;
			}

			down = Math.min(down, temp - 1);
		}

		for (int[] mineral : floatingCluster.minerals) {
			cave[mineral[0]][mineral[1]] = EMPTY;
		}

		for (int[] mineral : floatingCluster.minerals) {
			cave[mineral[0] - down][mineral[1]] = MINERAL;
		}
	}

	/**
	 * 타겟 미네랄을 중심으로 연결된 모든 클러스터 중 공중에 떠있는 클러스터를 반환한다.
	 * 두 개 이상의 클러스터가 떨어지는 경우는 없기 때문에 하나만 발견하면 바로 반환한다.
	 *
	 * @param target 부메랑으로 맞춰 사라진 미네랄(타겟 미네랄)의 위치
	 * @return 공중에 떠있는 클러스터, 공중에 떠있는 클러스터가 없는 경우 null
	 */
	private static Cluster getFloatingCluster(int[] target) {
		boolean[][] visit = new boolean[r + 1][c];
		for (int d = 0; d < 4; d++) {
			int sr = target[0] + dir[d][0];
			int sc = target[1] + dir[d][1];

			if (!checkRange(sr, sc) || cave[sr][sc] == EMPTY || visit[sr][sc]) {
				continue;
			}

			Cluster cluster = getCluster(new int[] {sr, sc}, visit);
			if (cluster.isFloating()) {
				return cluster;
			}
		}

		return null;
	}

	/**
	 * {sr, sc} 위치를 포함한 클러스터를 반환하며 클러스터에 포함된 모든 미네랄의 위치를 visit에 표시한다.
	 * if cave[r][c] == MINERAL == 'x' then visit[r][c] = true
	 * @param start 시작 미네랄의 위치 {sr, sc}
	 * @param visit 방문한 미네랄 위치
	 * @return {sr, sc}를 포함하는 클러스터
	 */
	private static Cluster getCluster(int[] start, boolean[][] visit) {
		Cluster cluster = new Cluster();
		Queue<int[]> que = new ArrayDeque<>();

		cluster.addMineral(start);
		que.add(start);
		visit[start[0]][start[1]] = true;

		while (!que.isEmpty()) {
			int[] cur = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dir[d][0];
				int nc = cur[1] + dir[d][1];

				if (checkRange(nr, nc) && !visit[nr][nc] && cave[nr][nc] == MINERAL) {
					int[] mineral = {nr, nc};
					cluster.addMineral(mineral);
					que.add(mineral);
					visit[nr][nc] = true;
				}
			}
		}

		return cluster;
	}

	/**
	 * {row, column}의 범위 유효성 체크
	 * @param row 행
	 * @param column 열
	 * @return {row, column} 유효하면 true, 유효하지 않으면 false
	 */
	private static boolean checkRange(int row, int column) {
		return 1 <= row && row <= r && 0 <= column && column < c;
	}

	/**
	 * 턴에 맞춰 던진 높이에 따라 부메랑에 맞은 미네랄의 위치를 반환한다.
	 * 부메랑으로 미네랄을 맞춘 경우 해당 위치의 미네랄은 빈 공간으로 변경한다.
	 * @param turn 턴, 던지는 방향 1 = 왼쪽 -> 오른쪽, -1 = 오른쪽 -> 왼쪽
	 * @param h 던진 높이
	 * @return 부메랑으로 미네랄을 맞춘 좌표, 맞춘 미네랄이 없는 경우 null
	 */
	private static int[] getTargetMineral(int turn, int h) {
		int[] target = null;
		int col = turn > 0 ? 0 : c - 1;

		while (0 <= col && col < c) {
			if (cave[h][col] == MINERAL) {
				cave[h][col] = EMPTY;
				target = new int[] {h, col};
				break;
			}

			col += turn;
		}

		return target;
	}

	private static class Cluster {
		List<int[]> minerals = new ArrayList<>();
		int[] floor = new int[c]; // floor[column] = 클러스터에서 column에 해당하는 가장 아래에 위치하는 미네랄의 높이(row)

		public Cluster() {
			Arrays.fill(floor, Integer.MAX_VALUE);
		}

		void addMineral(int[] mineral) {
			minerals.add(mineral);

			int row = mineral[0];
			int col = mineral[1];
			floor[col] = Math.min(floor[col], row);
		}

		boolean isFloating() {
			for (int i = 0; i < c; i++) {
				if (floor[i] == 1) { // 클러스터 중 바닥에 위치한 미네랄이 있을 경우
					return false;
				}
			}

			return true;
		}
	}
}
