import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LEVEL-2
 * 찾아라 프로그래밍 마에스터 > 게임 맵 최단거리
 */
public class Solution {
    static class Position {
        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }

        int y;
        int x;
    }

    final Position[] DESTINATION = new Position[] {
            new Position(-1, 0),
            new Position(0, 1),
            new Position(1, 0),
            new Position(0, -1)
    };

    public int solution(int[][] maps) {
        int N = maps.length;
        int M = maps[0].length;

        Deque<Position> deque = new ArrayDeque<>();

        maps[0][0] = 1;
        deque.add(new Position(0, 0));

        int answer = 0;
        while (!deque.isEmpty()) {
            int dequeSize = deque.size();
            answer++;
            for (int i = 0; i < dequeSize; i++) {
                Position now = deque.poll();

                for (int j = 0; j < 4; j++) {
                    int ny = now.y + DESTINATION[j].y;
                    int nx = now.x + DESTINATION[j].x;

                    if (0 <= ny && ny < N && 0 <= nx && nx < M && maps[ny][nx] == 1) {
                        if (ny == N - 1 && nx == M - 1) {
                            return answer+1;
                        }

                        maps[ny][nx] = 2;
                        deque.add(new Position(ny ,nx));
                    }
                }
            }
        }
        return -1;
    }

}