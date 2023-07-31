import java.util.*;
import java.io.*;


public class Main {

	private static final int REMOVED = -1;
	private static int r, c;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		c = Integer.parseInt(br.readLine());
		r = 3 * c;
		int[][] garage = new int[r][c];

		for (int i = r - 1; i >= 0; i--) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				garage[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// System.out.println("init garage: ");
		// for (int i = 0 ; i < r; i++) {
		//     System.out.println(Arrays.toString(garage[i]));
		// }
		// System.out.println("==============");

		// System.out.println(getMaxArea(garage));
		System.out.println(solution(garage, 0, 0));
	}

	private static int solution(int[][] garage, int cnt, int idx) {
		if (idx == 3) {
			// System.out.println(idx);
			// for (int i = 0 ; i < r; i++) {
			//     System.out.println(Arrays.toString(garage[i]));
			// }
			// System.out.println("==============");
			return cnt;
		}

		int[][] temp = down(garage);

		// System.out.println(idx);
		// for (int i = 0 ; i < r; i++) {
		//     System.out.println(Arrays.toString(temp[i]));
		// }
		// System.out.println("==============");

		boolean[][] visit = new boolean[c][c];
		int ret = 0;

		for (int i = 0; i < c; i++) {
			for (int j = 0; j < c; j++) {
				if (visit[i][j]) {
					continue;
				}

				int target = temp[i][j];
				int removeCnt = getRemoveCnt(temp, i, j, visit);
				int area = getMaxArea(temp, i, j);
				// System.out.println("idx: " + idx + ", target: " + target + ", rCnt: " + removeCnt + ", area: " + area);
				// System.out.println("cnt: " + cnt + ", removeCnt: " + removeCnt + ", area: " + area);

				ret = Math.max(ret, solution(temp, cnt + removeCnt + area, idx + 1));

				fill(temp, i, j, target); // 원래 상태로 복구
			}
		}

		return ret;
	}

	private static int[][] down(int[][] garage) {
		int[][] temp = new int[r][c];
		for (int i = 0; i < r; i++) {
			temp[i] = Arrays.copyOf(garage[i], c);
		}

		for (int i = 0; i < c; i++) {
			int space = 0;
			for (int j = 0; j < r; j++) {
				if (temp[j][i] > 0 && space > 0) {
					temp[j - space][i] = temp[j][i];
					temp[j][i] = REMOVED;
				} else if (temp[j][i] == REMOVED) {
					space++;
				}
			}
		}

		return temp;
	}

	private static int getRemoveCnt(int[][] garage, int si, int sj, boolean[][] visit) {
		int target = garage[si][sj];
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {si, sj});

		garage[si][sj] = REMOVED;
		visit[si][sj] = true;

		int removeCnt = 0;
		int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		while (!q.isEmpty()) {
			removeCnt++;
			int[] cur = q.poll();

			for (int[] d : dir) {
				int ni = cur[0] + d[0];
				int nj = cur[1] + d[1];

				if (checkRange(ni, nj) && !visit[ni][nj] && garage[ni][nj] == target) {
					garage[ni][nj] = REMOVED;
					visit[ni][nj] = true;
					q.add(new int[] {ni, nj});
				}
			}
		}

		return removeCnt;
	}

	private static int getMaxArea(int[][] garage, int si, int sj) {
        int ei = si;
        int ej = sj;
        for (int i = si; i < c; i++) {
            for (int j = sj; j < c; j++) {
                if (garage[i][j] == REMOVED) {
                    ei = Math.max(ei, i);
                    ej = Math.max(ej, j);
                }
            }
        }

        return (ei - si + 1) * (ej - sj + 1);
		// int[][] temp = new int[c][c];
		// for (int i = c - 1; i >= 0; i--) {
		// 	for (int j = 0; j < c; j++) {
		// 		if (garage[i][j] != REMOVED) {
		// 			continue;
		// 		}

		// 		temp[i][j] = i == c - 1 ? 1 : temp[i + 1][j] + 1;
		// 	}
		// }

		// for (int i = 0; i < c; i++) {
		//     System.out.println(Arrays.toString(temp[i]));
		// }

		// int area = 0;
		// for (int i = 0; i < c; i++) {
		// 	int w = 0;
		// 	int h = Integer.MAX_VALUE;
		// 	for (int j = 0; j < c; j++) {
		// 		if (temp[i][j] == 0) {
		// 			area = Math.max(area, h * w);
		// 			w = 0;
		// 			h = Integer.MAX_VALUE;
		// 		} else {
		// 			h = Math.min(h, temp[i][j]);
		// 			w++;
		// 		}
		// 	}
		// 	area = Math.max(area, h * w);
		// }

		// return area;
	}

	private static void fill(int[][] garage, int si, int sj, int target) {
		Queue<int[]> q = new ArrayDeque<>();
		// boolean[][] visit = new boolean[c][c];

		garage[si][sj] = target;
		q.add(new int[] {si, sj});
		// visit[si][sj] = true;

		int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int[] d : dir) {
				int ni = cur[0] + d[0];
				int nj = cur[1] + d[1];

				if (checkRange(ni, nj) && garage[ni][nj] == REMOVED) {
					garage[ni][nj] = target;
					// visit[ni][nj] = true;
					q.add(new int[] {ni, nj});
				}
			}
		}
	}

	private static boolean checkRange(int i, int j) {
		return 0 <= i && i < c && 0 <= j && j < c;
	}
}