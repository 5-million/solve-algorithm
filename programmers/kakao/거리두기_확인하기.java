import java.util.LinkedList;
import java.util.Queue;

/**
 * LEVEL-2
 * 2021 카카오 채용연계형 인터십 > 거리두기 확인하기
 */
public class Prog81302 {
    private class Position {
        int y;
        int x;

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public int[] solution(String[][] places) {
        int[] answer = {0, 0, 0, 0, 0};
        for (int i = 0; i < 5; i++) {
            String[][] place = new String[5][5];

            for (int j = 0; j < 5; j++)
                place[j] = places[i][j].split("");

            if (executeAll(place))
                answer[i] = 1;
        }

        return answer;
    }

    private boolean executeAll(String[][] place) {
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                if (place[y][x].equals("P"))
                    if (!bfs(place, new Position(y, x)))
                        return false;
            }
        }

        return true;
    }

    private boolean bfs(String[][] place, Position start) {
        int[][] direction = {
                {-1, 0}, {0, 1}, {1, 0}, {0, -1}
        };

        boolean[][] visited = new boolean[5][5];

        Queue<Position> qu = new LinkedList();
        qu.add(start);
        visited[start.y][start.x] = true;

        int distance = 0;
        while (!qu.isEmpty()) {
            distance++;
            if (distance == 3)
                break;

            int quSize = qu.size();
            for (int i = 0; i < quSize; i++) {
                Position now = qu.poll();

                for (int[] d : direction) {
                    int dy = now.y + d[0];
                    int dx = now.x + d[1];

                    if (dy >= 0 && dy < 5 && dx >= 0 && dx < 5 && !visited[dy][dx]) {
                        if (distance < 3 && place[dy][dx].equals("P"))
                            return false;

                        if (place[dy][dx].equals("O")) {
                            qu.add(new Position(dy, dx));
                            visited[dy][dx] = true;
                        }
                    }
                }
            }
        }

        return true;
    }
}