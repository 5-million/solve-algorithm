import java.util.LinkedList;
import java.util.Queue;

/**
 * LEVEL-2
 * 위클리 챌린지 > 전력망을 둘로 나누기
 */
public class Solution {

    private final int[][] grid = new int[101][101];

    public int solution(int n, int[][] wires) {
        int answer = n;
        init(wires);

        for (int[] wire : wires) {
            int n1 = wire[0];
            int n2 = wire[1];

            grid[n1][n2] = 0;
            grid[n2][n1] = 0;

            answer = Math.min(answer, Math.abs(2 * bfs(n) - n));

            grid[n1][n2] = 1;
            grid[n2][n1] = 1;
        }

        return answer;
    }

    private void init(int[][] wires) {
        for (int[] wire : wires) {
            int n1 = wire[0];
            int n2 = wire[1];

            grid[n1][n2] = 1;
            grid[n2][n1] = 1;
        }
    }

    private int bfs(int n) {
        int nodeCount = 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visit = new boolean[101];

        q.add(1);
        visit[1] = true;

        while (!q.isEmpty()) {
            int now = q.poll();
            nodeCount++;

            for (int next = 1; next <= n; next++)
                if (grid[now][next] == 1 && !visit[next]) {
                    q.add(next);
                    visit[next] = true;
                }
        }

        return nodeCount;
    }
}
