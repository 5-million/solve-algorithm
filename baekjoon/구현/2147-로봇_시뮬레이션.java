import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int a, b; // a: 가로, b: 세로
	private static int n, m; // n: 로봇의 개수, m: 명령 횟수
	private static final int[][] DIR = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; // N E S W
	private static Robot[] robots;
	private static Robot[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		map = new Robot[b + 1][a + 1];

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		robots = new Robot[n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = getDirIndex(st.nextToken());

			robots[i] = new Robot(i, y, x, d);
            map[y][x] = robots[i];
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int robotId = Integer.parseInt(st.nextToken());
			String command = st.nextToken();
			int repeat = Integer.parseInt(st.nextToken());

			Robot robot = robots[robotId];

			try {
				robot.run(command, repeat);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return;
			}
		}
        
        System.out.println("OK");
	}

	private static int getDirIndex(String dir) {
		switch (dir) {
			case "N":
				return 0;
			case "E":
				return 1;
			case "S":
				return 2;
			default:
				return 3;
		}
	}

	private static class Robot {

		int id;
		int y;
		int x;
		int d;

		public Robot(int id, int y, int x, int d) {
			this.id = id;
			this.y = y;
			this.x = x;
			this.d = d;
		}

		public void run(String command, int repeat) {
			if ("L".equals(command)) {
				turnLeft(repeat);
			} else if ("R".equals(command)) {
				turnRight(repeat);
			} else {
				go(repeat);
			}
		}

		private void turnRight(int repeat) {
			d = (d + repeat) % 4;
		}

		private void turnLeft(int repeat) {
			d = (d + 3 * repeat) % 4;
		}

		private void go(int repeat) {
			int ny = y;
			int nx = x;
			for (int dist = 1; dist <= repeat; dist++) {
				ny += DIR[d][0];
				nx += DIR[d][1];

                if (!isWithinRange(ny, nx)) {
					throw new RuntimeException(
						String.format("Robot %d crashes into the wall", id)
					);
				}
                
				if (map[ny][nx] != null) {
					throw new RuntimeException(
						String.format("Robot %d crashes into robot %d", id, map[ny][nx].id)
					);
                }
			}

			map[y][x] = null;
			map[ny][nx] = this;
            y = ny;
			x = nx;
		}

		private boolean isWithinRange(int ny, int nx) {
			return 1 <= ny && ny <= b && 1 <= nx && nx <= a;
		}
	}
}
