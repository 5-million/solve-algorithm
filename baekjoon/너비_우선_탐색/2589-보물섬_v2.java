import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static class Position {
        public int y;
        public int x;

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int n; // 세로
    static int m; // 가로
    static char[][] treasureMap;
    static int[][] direction = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        treasureMap = new char[n][m];

        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                treasureMap[i][j] = row.charAt(j);
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (treasureMap[i][j] == 'L')
                    answer = Math.max(answer, getFurthestDistance(new Position(i, j)));
            }
        }

        System.out.println(answer);
        br.close();
    }

    static int getFurthestDistance(Position start) {
        ArrayDeque<Position> deque = new ArrayDeque<>();
        int[][] visit = new int[n][m];

        deque.add(start);
        visit[start.y][start.x] = 1;

        int time = -1;
        while (!deque.isEmpty()) {
            int dequeSize = deque.size();
            time++;
            for (int i = 0; i < dequeSize; i++) {
                Position now = deque.pop();

                for (int j = 0; j < 4; j++) {
                    int ny = now.y + direction[j][0];
                    int nx = now.x + direction[j][1];

                    if (ny < 0 || ny >= n || nx < 0 || nx >= m)
                        continue;

                    if (treasureMap[ny][nx] == 'L' && visit[ny][nx] == 0) {
                        visit[ny][nx] = 1;
                        deque.add(new Position(ny, nx));
                    }
                }
            }
        }

        return time;
    }
}