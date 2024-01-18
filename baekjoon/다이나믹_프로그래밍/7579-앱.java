import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 실행 중인 앱의 개수
		int m = Integer.parseInt(st.nextToken()); // 확보해야하는 메모리

		int[] memory = new int[n]; // i번째 앱이 사용하고 있는 메모리
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}

		int[] cost = new int[n]; // i번째 앱의 비활성화 비용
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}

		int ans = Integer.MAX_VALUE;
		int maxCost = n * 100 + 1;
		int[][] dp = new int[n + 1][maxCost];
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < maxCost; j++) {
				if (j < cost[i - 1]) {
					dp[i][j] = dp[i - 1][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost[i - 1]] + memory[i - 1]);

					if (dp[i][j] >= m) {
						ans = Math.min(ans, j);
					}
				}
			}
		}

		System.out.println(ans);
	}
}
