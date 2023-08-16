import java.util.*;

class Solution {

    private int[][] dir = {{1, 0}, {0, -1}, {0, 1}, {-1 ,0}}; // d l r u
    private char[] direction = {'d', 'l', 'r', 'u'};
    private int n, m, r, c, k;
    private String answer;
    private boolean[][][] visit;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        this.r = r - 1;
        this.c = c - 1;
        this.k = k;
        visit = new boolean[n][m][k + 1];

        dfs(x - 1, y - 1, new char[k], 0);
        return answer == null ? "impossible" : answer;
    }

    private void dfs(int y, int x, char[] route, int idx) {
        if (idx == k && y == r && x == c) {
            // k번째에 탈출지점에 있음
            String temp = String.valueOf(route);

            if (answer == null || temp.compareTo(answer) > 0) {
                answer = temp;
            }
        }

        if (idx == k) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int ny = y + dir[i][0];
            int nx = x + dir[i][1];
            int remain = k - idx;
            if (checkRange(ny, nx) && !visit[ny][nx][remain]) {
                if (answer != null && answer.charAt(idx) < direction[i]) {
                    return;
                }
                visit[ny][nx][remain] = true;
                route[idx] = direction[i];
                dfs(ny, nx, route, idx + 1);
            }
        }
    }

    private boolean checkRange(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}