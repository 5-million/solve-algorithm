import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LEVEL-3
 * 2020 카카오 인턴십 > 경주로 건설
 */
public class Solution {

    private final int[][] direction = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static class Position {
        int y;
        int x;
        int cost;
        int direc;

        public Position(int y, int x, int cost, int direc) {
            this.y = y;
            this.x = x;
            this.cost = cost;
            this.direc = direc;
        }
    }

    public int solution(int[][] board) {
        int n = board.length;
        int cost1 = board[0][1] == 0 ? bfs(n, board, new Position(0, 1, 100, 1)) : Integer.MAX_VALUE;
        int cost2 = board[1][0] == 0 ? bfs(n, board, new Position(1, 0, 100, 2)) : Integer.MAX_VALUE;

        return Math.min(cost1, cost2);
    }

    public int bfs(int n, int[][] board, Position start) {
        Queue<Position> q = new LinkedList<>();
        int[][] costs = new int[n][n];

        q.add(start);
        for (int[] cost : costs)
            Arrays.fill(cost, Integer.MAX_VALUE);
        costs[0][0] = 0;
        costs[start.y][start.x] = 100;

        while (!q.isEmpty()) {
            Position now = q.poll();

            for (int i = 0; i < 4; i++) {
                int cost = now.cost + 100;
                if (i != now.direc && i != (now.direc + 2) % 4)
                    cost += 500;

                int ny = now.y + direction[i][0];
                int nx = now.x + direction[i][1];

                if (0 <= ny && ny < n && 0 <= nx && nx < n && board[ny][nx] == 0 && costs[ny][nx] > cost) {
                    costs[ny][nx] = cost;
                    q.add(new Position(ny, nx, cost, i));
                }
            }
        }

        return costs[n - 1][n - 1];
    }
}