import java.io.*;
import java.util.*;

public class Main {

    private static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;

	// BLOCK[type][direction] = type 블록에 direction 방향으로 들어왔을 때 흐르는 방향 index 리턴
	// if BLOCK[type][direction] = -1 then 불가능한 진입 방향
	private static final int[][] BLOCK = {
		{UP, -1, DOWN, -1}, // '|' block
		{-1, RIGHT, -1, LEFT}, // '-' block
		{UP, RIGHT, DOWN, LEFT}, // '+' block
		{RIGHT, -1, -1, DOWN}, // '1' block
		{-1, -1, RIGHT, UP}, // '2' block
		{-1, UP, LEFT, -1,}, // '3' block
		{LEFT, DOWN, -1, -1} // '4' block
	};

	private static char[] BLOCK_TYPE = {'|', '-', '+', '1', '2', '3', '4'};

	private static final int[][] DIR = {
		{-1, 0}, {0, 1}, {1, 0}, {0, -1}
	};

	private static int r, c;
	private static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		map = new char[r][c];
		int[] start = new int[2];
		for (int y = 0; y < r; y++) {
			char[] input = br.readLine().toCharArray();
			for (int x = 0; x < c; x++) {
				map[y][x] = input[x];
				if (map[y][x] == 'M') {
					start[0] = y;
					start[1] = x;
				}
			}
		}

		int d = 0;
		for (; d < 4; d++) {
			int ny = start[0] + DIR[d][0];
			int nx = start[1] + DIR[d][1];

			if (checkRange(ny, nx) && map[ny][nx] != '.' && map[ny][nx] != 'Z') {
				start[0] = ny;
				start[1] = nx;
				break;
			}
		}

		int[] result = solution(start[0], start[1], d, null);
		System.out.printf("%d %d %c", result[0] + 1, result[1] + 1, BLOCK_TYPE[result[2]]);
	}

	private static int[] solution(int y, int x, int d, int[] pipe) {
		if (!checkRange(y, x)) {
			return null;
		}
		
		if (map[y][x] == 'Z') {
			return pipe;
		}

		int[] ret = null;
		if (map[y][x] == '.') {
			if (pipe != null) {
				return null;
			}

			for (int b = 0; b < BLOCK_TYPE.length; b++) {
				int nd = BLOCK[b][d];
				if (nd == -1) {
					continue;
				}

				int ny = y + DIR[nd][0];
				int nx = x + DIR[nd][1];

				map[y][x] = BLOCK_TYPE[b];
				ret = solution(ny, nx, nd, new int[] {y, x, b});
				if (ret != null) {
					break;
				}
			}
		} else {
			int nd = getDirection(map[y][x], d);
			if (nd != -1) {
				int ny = y + DIR[nd][0];
				int nx = x + DIR[nd][1];

				ret = solution(ny, nx, nd, pipe);
			}
		}

		return ret;
	}

	private static int getDirection(char block, int d) {
		int type = getBlockType(block);
		return BLOCK[type][d];
	}

	private static int getBlockType(char block) {
		for (int i = 0; i < BLOCK_TYPE.length; i++) {
			if (BLOCK_TYPE[i] == block) {
				return i;
			}
		}
		return -1;
	}

	private static boolean checkRange(int y, int x) {
		return 0 <= y && y < r && 0 <= x && x < c;
	}
}
