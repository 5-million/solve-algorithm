import java.io.*;
import java.util.*;

public class Main {

    private static final int MOD = 1_000_000_003;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());

		int[][] dp = new int[n + 1][k + 1]; // n개의 색 중에 서로 이웃하지 않은 k개의 색을 선택하는 방법의 수 (순환을 고려하지 않음)
		for (int i = 0; i <= n; i++) {
			dp[i][0] = 1;
			dp[i][1] = i;
		}

		for (int i = 2; i < n; i++) { // i개의 색의 개수 중에
			for (int j = 2; j <= k; j++) { // 서로 이웃하지 않은 j개의 색을 선택
				dp[i][j] = (dp[i - 2][j - 1] + dp[i - 1][j]) % MOD;
			}
		}

		int ans = (dp[n - 3][k - 1] + dp[n - 1][k]) % MOD;
		System.out.println(ans);
	}
}
