import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * G1
 * 1194 - 달이 차오른다, 가자.
 * https://www.acmicpc.net/problem/1194
 */
public class Main {

    private static class Pos {
        int y;
        int x;
        int key;
        int move;

        public Pos(int y, int x, int key, int move) {
            this.y = y;
            this.x = x;
            this.key = key;
            this.move = move;
        }
    }

    private static int n, m;
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        Queue<Pos> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == '0') {
                    q.add(new Pos(i, j, 0, 0));
                }
            }
        }

        System.out.println(bfs(q));
    }

    public static int bfs(Queue<Pos> q) {
        boolean[][][] v = new boolean[n][m][1 << 6];
        Pos start = q.peek();
        v[start.y][start.x][start.key] = true;

        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while (!q.isEmpty()) {
            Pos now = q.poll();

            for (int[] d : dir) {
                int ny = now.y + d[0];
                int nx = now.x + d[1];

                if (checkRange(ny, nx)) {
                    int nextKey = now.key;
                    if (map[ny][nx] >= 'a' && map[ny][nx] <= 'z') {
                        nextKey = nextKey | 1 << map[ny][nx] - 'a';
                    }

                    if (map[ny][nx] >= 'A' && map[ny][nx] <= 'Z') {
                        if ((now.key & 1 << map[ny][nx] - 'A') == 0) {
                            continue;
                        }
                    }

                    if (map[ny][nx] == '#') {
                        continue;
                    }

                    if (map[ny][nx] == '1') {
                        return now.move + 1;
                    }

                    if (!v[ny][nx][nextKey]) {
                        v[ny][nx][nextKey] = true;
                        q.add(new Pos(ny, nx, nextKey, now.move + 1));
                    }
                }
            }
        }

        return -1;
    }

    private static boolean checkRange(int y, int x) {
        return 0 <= y & y < n && 0 <= x && x < m;
    }
}