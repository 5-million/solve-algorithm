import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	private static class Block implements Comparable<Block> {
		int y;
		int x;
		int color;

		public Block(int y, int x, int color) {
			this.y = y;
			this.x = x;
			this.color = color;
		}

		@Override
		public int compareTo(Block target) {
			if (target.color != this.color) { // 무지개 색 블록은 우선순위가 떨어지도록
				return target.color - this.color;
			} else if (this.y != target.y) {
				return this.y - target.y;
			} else {
				return this.x - target.x;
			}
		}
	}

	private static class BlockGroup implements Comparable<BlockGroup> {
		private int rainbowBlockCount;
		private PriorityQueue<Block> blocks = new PriorityQueue<>();

		public int getSize() {
			return blocks.size();
		}

		public Block getStandardBlock() {
			return blocks.peek();
		}

		public void addBlock(Block block) {
			if (block.color == RAINBOW_BLOCK) {
				this.rainbowBlockCount++;
			}

			blocks.add(block);
		}

		public int removeAndGetScore() {
			int size = getSize();

			while (!blocks.isEmpty()) {
				Block block = blocks.poll();
				map[block.y][block.x] = EMPTY_BLOCK;
			}

			return size * size;
		}

		@Override
		public int compareTo(BlockGroup target) {
			if (target.getSize() != this.getSize()) {
				return target.getSize() - this.getSize();
			}  else if (this.rainbowBlockCount != target.rainbowBlockCount) {
				return target.rainbowBlockCount - this.rainbowBlockCount;
			} else {
				Block thisStandardBlock = this.getStandardBlock();
				Block targetStandardBlock = target.getStandardBlock();

				if (targetStandardBlock.y != thisStandardBlock.y) {
					return targetStandardBlock.y - thisStandardBlock.y;
				} else {
					return targetStandardBlock.x - thisStandardBlock.x;
				}
			}
		}
	}

	private static final int RAINBOW_BLOCK = 0;
	private static final int EMPTY_BLOCK = -2;
	private static final int[][] DIR = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	private static int n, m;
	private static int[][] map;

	public static void main(String[] args) throws IOException {
		int score = 0;
		init();
		BlockGroup biggestBlockGroup = findBiggestBlockGroup();
		while (biggestBlockGroup != null) {
			score += biggestBlockGroup.removeAndGetScore();
			down();
			rotate();
			down();

			biggestBlockGroup = findBiggestBlockGroup();
		}

		System.out.println(score);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];

		for (int y = 0; y < n; y++) {
			String[] input = br.readLine().split(" ");
			for (int x = 0; x < n; x++) {
				map[y][x] = Integer.parseInt(input[x]);
			}
		}
	}

	private static BlockGroup findBiggestBlockGroup() {
		PriorityQueue<BlockGroup> blockGroups = new PriorityQueue<>();
		boolean[][] visit = new boolean[n][n];

		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				if (!visit[y][x] && map[y][x] > 0) { // 방문하지 않은 블록이면서 검은색 또는 무지개 블록이 아닌 블록
					BlockGroup blockGroup = makeBlockGroup(visit, y, x);

					if (blockGroup.getSize() >= 2) { // 블록 그룹의 블록 개수가 2개 이상이면 추가
						blockGroups.add(blockGroup);
					}
				}
			}
		}

		return blockGroups.poll();
	}

	private static BlockGroup makeBlockGroup(boolean[][] visit, int y, int x) {
		BlockGroup blockGroup = new BlockGroup();
		int color = map[y][x];
		Queue<int[]> q = new ArrayDeque<>();

		q.add(new int[] {y, x});
		visit[y][x] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			blockGroup.addBlock(new Block(cur[0], cur[1], map[cur[0]][cur[1]]));

			for (int[] d : DIR) {
				int ny = cur[0] + d[0];
				int nx = cur[1] + d[1];

				if (checkRange(ny, nx) && !visit[ny][nx] && (map[ny][nx] == RAINBOW_BLOCK || map[ny][nx] == color)) {
					visit[ny][nx] = true;
					q.add(new int[] {ny, nx});
				}
			}
		}

		return blockGroup;
	}

	private static boolean checkRange(int y, int x) {
		return 0 <= y && y < n && 0 <= x && x  <n;
	}

	private static void down() {
		for (int x = n - 1; x >= 0; x--) {
			for (int y = n - 1; y >= 0; y--) {
				if (map[y][x] >= 0) {
					int nextY = getNextY(y, x);

					if (y != nextY) {
						map[nextY][x] = map[y][x];
						map[y][x] = EMPTY_BLOCK;
					}
				}
			}
		}
	}

	private static int getNextY(int y, int x) {
		while (y + 1 < n && map[y + 1][x] == EMPTY_BLOCK) {
			y++;
		}

		return y;
	}

	private static void rotate() {
		int[][] temp = new int[n][n];
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				temp[y][x] = map[x][n - y - 1];
			}
		}

		map = temp;
	}
}
