import java.util.Arrays;

/**
 * LEVEL-3
 * 연습문제 - N-Queen
 */
public class Solution {

    public int solution(int n) {
        int[] queen = new int[n];
        Arrays.fill(queen, -1);
        return dfs(n, queen, 0);
    }

    private int dfs(int n, int[] queen, int row) {
        if (row == n)
            return 1;

        int ret = 0;
        for (int i = 0; i < n; i++) {
            if (isPossible(queen, i, row)) {
                queen[row] = i;
                ret += dfs(n, queen, row + 1);
                queen[row] = -1;
            }
        }

        return ret;
    }

    private boolean isPossible(int[] queen, int col, int row) {
        for (int i = 0; i < row; i++) {
            if (queen[i] == col) return false;
            if (Math.abs(queen[i] - col) == Math.abs(i - row))
                return false;
        }

        return true;
    }
}