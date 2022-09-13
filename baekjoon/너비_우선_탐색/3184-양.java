import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * S1
 * 3184 - 양
 * https://www.acmicpc.net/problem/3184
 */
public class Main {

    private static final char FENCE = '#';
    private static final char WOLF = 'v';
    private static final char SHEEP = 'o';

    private static class Pos {
        int y;
        int x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static int r, c;
    private static char[][] map;
    private static int wolfCnt, sheepCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            map[i] = br.readLine().toCharArray();
        }

        boolean[][] v = new boolean[r][c];
        for (int y = 0; y < r; y++) {
            for (int x = 0; x < c; x++) {
                if (!v[y][x] && (map[y][x] == WOLF || map[y][x] == SHEEP)) {
                    bfs(new Pos(y, x), v);
                }
            }
        }

        System.out.printf("%d %d\n", sheepCnt, wolfCnt);
    }

    private static void bfs(Pos start, boolean[][] v) {
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        Queue<Pos> q = new ArrayDeque<>();
        q.add(start);
        v[start.y][start.x] = true;

        int wolf = 0, sheep = 0;
        while (!q.isEmpty()) {
            Pos now = q.poll();

            if (map[now.y][now.x] == WOLF) {
                wolf++;
            } else if (map[now.y][now.x] == SHEEP) {
                sheep++;
            }

            for (int[] d : dir) {
                int ny = now.y + d[0];
                int nx = now.x + d[1];

                if (checkRange(ny, nx) && !v[ny][nx] && map[ny][nx] != FENCE) {
                    v[ny][nx] = true;
                    q.add(new Pos(ny, nx));
                }
            }
        }

        if (wolf < sheep) {
            sheepCnt += sheep;
        } else {
            wolfCnt += wolf;
        }
    }

    private static boolean checkRange(int y, int x) {
        return 0 <= y && y < r && 0 <= x && x < c;
    }
}