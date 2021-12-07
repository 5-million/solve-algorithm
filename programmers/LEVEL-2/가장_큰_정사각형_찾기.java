/**
 * LEVEL-2
 * 연습문제 > 가장 큰 정사각형 찾기
 */
public class Solution {

    public int solution(int [][]board)
    {
        int row = board.length;
        int col = board[0].length;
        int[][] dp = new int[row][col];

        int maxSize = board[0][0];
        for (int y = 0; y < row; y++) {
            for (int x = 0; x < col; x++) {
                dp[y][x] = board[y][x];

                if (y == 0 || x == 0)
                    continue;

                int lu = board[y-1][x-1];
                int ru = board[y-1][x];
                int ld = board[y][x-1];
                int rd = board[y][x];

                if (isSquare(lu, ru, ld, rd))
                    dp[y][x] = getMin(dp, y, x) + 1;

                maxSize = Math.max(maxSize, dp[y][x]);
            }
        }

        return maxSize * maxSize;
    }

    private boolean isSquare(int a, int b, int c, int d) {
        boolean ret = a == 1;
        ret = ret && a == b;
        ret = ret && b == c;
        return ret && c == d;
    }

    private int getMin(int[][] arr, int y, int x) {
        int min = Math.min(arr[y - 1][x], arr[y - 1][x - 1]);
        return Math.min(min, arr[y][x - 1]);
    }
}