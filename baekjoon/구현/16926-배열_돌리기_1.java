import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * S1
 * 16926 - 배열 돌리기 1
 * https://www.acmicpc.net/problem/16926
 */
public class Main {

	private static int n, m, r;
	private static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int cycle = Math.min(n, m) / 2;
		for (int i = 0; i < r; i++) {
			for (int d = 0; d < cycle; d++) {
				rotate(d);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private static void rotate(int depth) {
		int temp = arr[depth][depth];
		for (int i = depth; i < m - 1 - depth; i++) {
			arr[depth][i] = arr[depth][i + 1];
		}

		for (int i = depth; i < n - 1 - depth; i++) {
			arr[i][m - depth - 1] = arr[i + 1][m - depth - 1];
		}

		for (int i = m - 1 - depth; i > depth; i--) {
			arr[n - 1 - depth][i] = arr[n - 1 - depth][i - 1];
		}

		for (int i = n - 1 - depth; i > depth; i--) {
			arr[i][depth] = arr[i - 1][depth];
		}
		arr[depth + 1][depth] = temp;
	}
}
