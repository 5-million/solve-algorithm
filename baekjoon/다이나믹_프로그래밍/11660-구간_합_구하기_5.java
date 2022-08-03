import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * S1
 * 11660 - 구간 합 구하기 5
 * https://www.acmicpc.net/problem/11660
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int sum = 0;
		int[][] dp = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				dp[i][j] = dp[i][j - 1] + Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				dp[j][i] += dp[j - 1][i];
			}
		}
		
		System.out.println();
		for (int i=1; i<=n; i++) {
			System.out.println(Arrays.toString(dp[i]));
		}

		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int sy = Integer.parseInt(st.nextToken());
			int sx = Integer.parseInt(st.nextToken());
			int dy = Integer.parseInt(st.nextToken());
			int dx = Integer.parseInt(st.nextToken());
			
			int answer = dp[dy][dx] - dp[dy][sx - 1] - dp[sy - 1][dx] + dp[sy - 1][sx - 1];

			System.out.println(answer);
		}
	}
}
