import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 모의 SW 역량테스트
 * 5644 - 무선 충전
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRDL1aeugDFAUo
 */
public class Solution {

	private static final int MAP_SIZE = 10;
	private static int[][] dir = { { 0, 0 }, { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static class User {
		int y;
		int x;

		public User(int y, int x) {
			this.y = y;
			this.x = x;
		}

		public void move(int d) {
			this.y += dir[d][0];
			this.x += dir[d][1];
		}
	}

	static class BC {
		int id;
		int y, x;
		int c;
		int p;

		public BC(int id, int y, int x, int c, int p) {
			this.id = id;
			this.y = y;
			this.x = x;
			this.c = c;
			this.p = p;
		}

		public boolean inRange(User user) {
			int d = Math.abs(this.y - user.y) + Math.abs(this.x - user.x);
			return d <= c;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());

			User a = new User(1, 1);
			User b = new User(MAP_SIZE, MAP_SIZE);
			int[][] moveInfo = new int[2][M];
			for (int i = 0; i < 2; i++) {
				moveInfo[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}

			BC[] bcInfo = new BC[A];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bcInfo[i] = new BC(i, y, x, c, p);
			}

			int answer = 0;
			List<BC>[] connectable = new List[2];
			for (int i = 0; i <= M; i++) {
				connectable[0] = new ArrayList<>();
				connectable[1] = new ArrayList<>();
				for (BC bc : bcInfo) {
					if (bc.inRange(a)) {
						connectable[0].add(bc);
					}

					if (bc.inRange(b)) {
						connectable[1].add(bc);
					}
				}

				answer += getMaxChargeVal(connectable);

				if (i == M)
					break;

				a.move(moveInfo[0][i]);
				b.move(moveInfo[1][i]);
			}

			System.out.printf("#%d %d\n", tc, answer);
		}
	}

	private static int getMaxChargeVal(List<BC>[] connectable) {
		int max = 0;
		if (connectable[0].isEmpty()) {
			max = getMaxFromList(connectable[1]);
		} else if (connectable[1].isEmpty()) {
			max = getMaxFromList(connectable[0]);
		} else {
			for (int i = 0; i < connectable[0].size(); i++) {
				BC aBC = connectable[0].get(i);
				for (int j = 0; j < connectable[1].size(); j++) {
					BC bBC = connectable[1].get(j);
					int temp = aBC.p + bBC.p;
					if (aBC == bBC)
						temp /= 2;
					max = Math.max(max, temp);
				}
			}
		}
		return max;
	}
	
	private static int getMaxFromList(List<BC> list) {
		int max = 0;
		for (BC bc : list) {
			max = Math.max(max, bc.p);
		}
		return max;
	}
}
