import java.util.LinkedList;
import java.util.Queue;

/**
 * LEVEL-2
 * 2021 카카오 채용연계형 인턴십 > 거리두기 확인하기
 * https://programmers.co.kr/learn/courses/30/lessons/81302
 */
public class Solution {

    private static final char PERSON = 'P';
    private static final char EMPTY_TABLE = 'O';
    private static final char PARTITION = 'X';

    private class Position {
        int y;
        int x;

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for (int i = 0; i < 5; i++) {
            answer[i] = checkPlace(convert(places[i]));
        }

        return answer;
    }

    private char[][] convert(String[] place) {
        char[][] ret = new char[5][5];
        for (int i = 0; i < 5; i++) {
            ret[i] = place[i].toCharArray();
        }

        return ret;
    }

    private int checkPlace(char[][] place) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (place[i][j] == PERSON) {
                    if (!isObey(place, new Position(i, j)))
                        return 0;
                }
            }
        }

        return 1;
    }

    private boolean isObey(char[][] place, Position person) {
        int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Queue<Position> queue = new LinkedList<>();
        boolean[][] visit = new boolean[5][5];

        queue.add(person);
        visit[person.y][person.x] = true;

        int dist = 0;
        while (!queue.isEmpty() && dist < 2) {
            dist++;

            int qSize = queue.size();
            for (int i = 0; i < qSize; i++) {
                Position now = queue.poll();

                for (int[] d : direction) {
                    int ny = now.y + d[0];
                    int nx = now.x + d[1];

                    if (0 <= ny && ny < 5 && 0 <= nx && nx < 5 && place[ny][nx] != PARTITION && !visit[ny][nx]) {
                        if (place[ny][nx] == PERSON) {
                            return false;
                        }

                        queue.add(new Position(ny, nx));
                        visit[ny][nx] = true;
                    }
                }
            }
        }

        return true;
    }
}