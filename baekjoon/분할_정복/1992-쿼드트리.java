import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * S1
 * 1992 - 쿼드트리
 * https://www.acmicpc.net/problem/1992
 */
public class Main {
	
	private static StringBuilder sb = new StringBuilder();
	private static int n;
	private static int[][] video;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		video = new int[n][n];
		for (int i=0; i<n; i++) {
			video[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}
		
		compress(0, 0, n);
		
		System.out.println(sb);
	}
	
	private static void compress(int y, int x, int size) {
		if (isAllSame(y, x, size)) {
			sb.append(video[y][x]);
			return;
		}
		
		sb.append("(");
		int half = size / 2;
		compress(y, x, half);
		compress(y, x + half, half);
		compress(y + half, x, half);
		compress(y + half, x + half, half);
		sb.append(")");
	}
	
	private static boolean isAllSame(int y, int x, int size) {
		for (int i=y; i < y + size; i++) {
			for (int j=x; j <x + size; j++) {
				if (video[y][x] != video[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
