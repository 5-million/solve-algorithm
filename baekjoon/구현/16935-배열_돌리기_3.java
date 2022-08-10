import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * S1 
 * 16935 - 배열 돌리기 3
 * https://www.acmicpc.net/problem/16935
 */
public class Main {

	static class Rotator {

		public static void rotate1(int[][] arr, int n, int m) {
			for (int i = 0; i < n / 2; i++) {
				for (int j = 0; j < m; j++) {
					int temp = arr[i][j];
					arr[i][j] = arr[n - 1 - i][j];
					arr[n - 1 - i][j] = temp;
				}
			}
		}

		public static void rotate2(int[][] arr, int n, int m) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m / 2; j++) {
					int temp = arr[i][j];
					arr[i][j] = arr[i][m - 1 - j];
					arr[i][m - 1 - j] = temp;
				}
			}
		}

		public static int[][] rotate3(int[][] arr, int n, int m) {
			int[][] temp = new int[m][n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					temp[i][j] = arr[n - 1 - j][i];
				}
			}
			return temp;
		}

		public static int[][] rotate4(int[][] arr, int n, int m) {
			int[][] temp = new int[m][n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					temp[i][j] = arr[j][m - 1 - i];
				}
			}
			return temp;
		}

		public static int[][] rotate5(int[][] arr, int n, int m) {
			int[][] dir = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };
			return rotateSubArr(arr, n, m, dir);
		}

		public static int[][] rotate6(int[][] arr, int n, int m) {
			int[][] dir = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
			return rotateSubArr(arr, n, m, dir);
		}
		
		private static int[][] rotateSubArr(int[][] arr, int n, int m, int[][] dir) {
			int nUnit = n / 2;
			int mUnit = m / 2;

			int[][] temp = new int[n][m];
			int dIdx = 0;
			int[] d = dir[dIdx++];
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					int sr = nUnit * i;
					int sc = mUnit * j;
					for (int r = sr; r < sr + nUnit; r++) {
						for (int c = sc; c < sc + mUnit; c++) {
							temp[r][c] = arr[r + (d[0] * nUnit)][c + (d[1] * mUnit)];
						}
					}

					d = dir[(dIdx++) % 4];
				}
			}

			return temp;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		int[][] arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < r; i++) {
			int o = Integer.parseInt(st.nextToken());
			int temp = n;
			switch (o) {
			case 1:
				Rotator.rotate1(arr, n, m);
				break;
			case 2:
				Rotator.rotate2(arr, n, m);
				break;
			case 3:
				arr = Rotator.rotate3(arr, n, m);
				n = m;
				m = temp;
				break;
			case 4:
				arr = Rotator.rotate4(arr, n, m);
				n = m;
				m = temp;
				break;
			case 5:
				arr = Rotator.rotate5(arr, n, m);
				break;
			default:
				arr = Rotator.rotate6(arr, n, m);
			}
		}

		print(arr);
	}

	private static void print(int[][] arr) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (int[] ints : arr) {
			for (int num : ints) {
				sb.append(num).append(" ");
			}
			sb.append("\n");
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
