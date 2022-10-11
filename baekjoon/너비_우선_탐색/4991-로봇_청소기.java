import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * G2
 * 4991 - 로봇 청소기
 * https://www.acmicpc.net/problem/4991
 */
public class Main {

    private static class Pos {
        int y;
        int x;
        int visit;
        int moveCnt;

        public Pos(int y, int x, int visit, int moveCnt) {
            this.y = y;
            this.x = x;
            this.visit = visit;
            this.moveCnt = moveCnt;
        }
    }

    private static int w, h;
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) {
                break;
            }

            map = new char[h][w];

            int cnt = 0;
            Pos start = null;
            for (int i = 0; i < h; i++) {
                String input = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = input.charAt(j);
                    if (map[i][j] == '*' || map[i][j] == 'o') {
                        if (map[i][j] == 'o') {
                            start = new Pos(i, j, 1 << cnt, 0);
                        }
                        map[i][j] = (char) ('0' + cnt++);
                    }
                }
            }

            int result = bfs(start, cnt);
            sb.append(result == Integer.MAX_VALUE ? -1 : result).append("\n");
        }

        System.out.println(sb);
    }

    private static int bfs(Pos start, int cnt) {
        Queue<Pos> q = new ArrayDeque<>();
        boolean[][][] visit = new boolean[1 << cnt][h][w];

        q.add(start);
        visit[start.visit][start.y][start.x] = true;

        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int minCnt = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Pos now = q.poll();

            if (isAllVisit(now.visit, cnt)) {
                minCnt = Math.min(minCnt, now.moveCnt);
            }

            for (int[] d : dir) {
                int ny = now.y + d[0];
                int nx = now.x + d[1];

                if (checkRange(ny, nx) && map[ny][nx] != 'x') {
                    int v = now.visit;

                    if ('0' <= map[ny][nx] && map[ny][nx] <= '9') {
                        v |= 1 << (map[ny][nx] - '0');
                    }

                    if (!visit[v][ny][nx]) {
                        visit[v][ny][nx] = true;
                        q.add(new Pos(ny, nx, v, now.moveCnt + 1));
                    }
                }
            }
        }

        return minCnt;
    }

    private static boolean isAllVisit(int visit, int cnt) {
        for (int i = 0; i < cnt; i++) {
            if ((visit & (1 << i)) == 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkRange(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }
}