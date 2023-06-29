import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	private static final int[][] DIR = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; // x좌표 증가, y좌표 감소, x좌표 감소, y좌표 증가
	private static final int MAP_SIZE = 100;
	private static boolean[][] map = new boolean[MAP_SIZE + 1][MAP_SIZE + 1];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());

			int[] start = {y + DIR[d][0], x + DIR[d][1]};

			// 0세대
			map[y][x] = true;
			map[start[0]][start[1]] = true;

			List<Integer> dir = new ArrayList<>();
			dir.add(d);

			dragonCurve(dir, start, g);
		}

		int answer = 0;
		for (int y = 0; y < MAP_SIZE; y++) {
			for (int x = 0; x < MAP_SIZE; x++) {
				if (map[y][x] && map[y + 1][x] && map[y][x + 1] && map[y + 1][x + 1]) {
					answer++;
				}
			}
		}

		System.out.println(answer);
	}

	private static void dragonCurve(List<Integer> dir, int[] cur, int g) {
		for (int i = 1; i <= g; i++) {
			int size = dir.size();
			for (int j = size - 1; j >= 0; j--) {
				int nd = (dir.get(j) + 1) % 4;
				dir.add(nd);

				cur[0] += DIR[nd][0];
				cur[1] += DIR[nd][1];

				map[cur[0]][cur[1]] = true;
			}
		}
	}
}
