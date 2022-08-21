import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G2
 * 17136 - 색종이 붙이기
 * https://www.acmicpc.net/problem/17136
 */
public class Main {

	private static final int INF = Integer.MAX_VALUE;
	private static final int MAX_COLOR_PAPER_SIZE = 5;
	private static final int PAPER_SIZE = 10;
	private static int[] colorPaper = { 0, 5, 5, 5, 5, 5};
	private static boolean[][] paper = new boolean[PAPER_SIZE][PAPER_SIZE];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < PAPER_SIZE; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < PAPER_SIZE; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					paper[i][j] = true;
				}
			}
		}

		int result = dfs(0);
		System.out.println(result == INF ? -1 : result);
	}

	private static int dfs(int cnt) {
		for (int r = 0; r < PAPER_SIZE; r++) {
			for (int c = 0; c < PAPER_SIZE; c++) {
				if (paper[r][c]) {
					int ret = INF;
					
					int maxSize = getMaxSquareSize(r, c);
					for (int size = maxSize; size > 0; size--) {
						if (colorPaper[size] > 0) {
							colorPaper[size]--;
							changePaper(r, c, size, false);
							ret = Math.min(ret, dfs(cnt + 1));
							changePaper(r, c, size, true);
							colorPaper[size]++;
						}
					}

					return ret;
				}
			}
		}
		return cnt;
	}

	private static void changePaper(int y, int x, int size, boolean val) {
		for (int r = y; r < y + size; r++) {
			for (int c = x; c < x + size; c++) {
				paper[r][c] = val;
			}
		}
	}

	private static int getMaxSquareSize(int y, int x) {
		loop1: for (int size = MAX_COLOR_PAPER_SIZE; size > 1; size--) {
			for (int r = y; r < y + size; r++) {
				for (int c = x; c < x + size; c++) {
					if (!checkRange(r, c) || !paper[r][c])
						continue loop1;
				}
			}
			return size;
		}
		return 1;
	}

	private static boolean checkRange(int y, int x) {
		return y < PAPER_SIZE && x < PAPER_SIZE;
	}
}
