import java.util.*;

/**
 * LEVEL-2
 * 2017 카카오코드 예선 > 카카오프렌즈 컬러링북
 */
public class Solution {
    class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    boolean[][] visited;

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && picture[i][j] != 0) {
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(m, n, new Point(i, j), picture));
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

        return answer;
    }

    public int bfs(int m, int n, Point start, int[][] picture) {
        Point[] direction = {
                new Point(-1, 0), new Point(0, 1), new Point(1, 0), new Point(0, -1)
        };

        Deque<Point> deque = new ArrayDeque<>();

        deque.add(start);
        visited[start.y][start.x] = true;

        int area = 0;
        while (!deque.isEmpty()) {
            Point now = deque.poll();

            area++;

            for (Point d : direction) {
                int ny = now.y + d.y;
                int nx = now.x + d.x;

                if (0 <= ny && ny < m && 0 <= nx && nx < n && !visited[ny][nx] && picture[ny][nx] == picture[start.y][start.x]) {
                    visited[ny][nx] = true;
                    deque.add(new Point(ny, nx));
                }
            }
        }

        return area;
    }
}