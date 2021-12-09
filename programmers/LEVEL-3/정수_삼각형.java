import java.util.Arrays;

/**
 * LEVEL-3
 * 동적계획법 > 정수 삼각형
 */
public class Solution {

    public int solution(int[][] triangle) {
        int[][] dp = new int[500][500];

        dp[0][0] = triangle[0][0];
        for (int y = 1; y < triangle.length; y++) {
            for (int x = 0; x < y + 1; x++) {
                dp[y][x] = dp[y - 1][x];

                if (x > 0)
                    dp[y][x] = Math.max(dp[y][x], dp[y - 1][x - 1]);

                dp[y][x] += triangle[y][x];
            }
        }

        return Arrays.stream(dp[triangle.length - 1]).max().getAsInt();
    }
}