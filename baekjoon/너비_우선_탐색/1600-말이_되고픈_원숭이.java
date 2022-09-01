import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * G3
 * 1600 - 말이 되고픈 원숭이
 * https://www.acmicpc.net/problem/1600
 */
public class Main {

    private static final int EMPTY = 0;

    private static class Monkey {
        int k;
        int y;
        int x;
        boolean[][] v;

        public Monkey(int k, int y, int x) {
            this.k = k;
            this.y = y;
            this.x = x;
        }
    }

    private static int w, h;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h][w];

        for (int y = 0; y < h; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < w; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs(k));
    }

    private static int bfs(int k) {
        int[][] adjDir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int[][] horseDir = {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};

        Monkey monkey = new Monkey(k, 0, 0);
        Queue<Monkey> q = new ArrayDeque<>();
        boolean[][][] v = new boolean[h][w][k + 1];

        q.add(monkey);
        v[0][0][k] = true;

        int move = -1;
        while (!q.isEmpty()) {
            int qSize = q.size();
            move++;
            for (int i = 0; i < qSize; i++) {
                Monkey cur = q.poll();

                if (cur.y == h - 1 && cur.x == w - 1) {
                    return move;
                }

                for (int[] d : adjDir) {
                    int ny = cur.y + d[0];
                    int nx = cur.x + d[1];

                    if (checkRange(ny, nx) && !v[ny][nx][cur.k] && map[ny][nx] == EMPTY) {
                        v[ny][nx][cur.k] = true;
                        q.add(new Monkey(cur.k, ny, nx));
                    }
                }

                if (cur.k > 0) {
                    for (int[] d : horseDir) {
                        int ny = cur.y + d[0];
                        int nx = cur.x + d[1];

                        if (checkRange(ny, nx) && !v[ny][nx][cur.k - 1] && map[ny][nx] == EMPTY) {
                            v[ny][nx][cur.k - 1] = true;
                            q.add(new Monkey(cur.k - 1, ny, nx));
                        }
                    }
                }
            }
        }

        return -1;
    }

    private static boolean checkRange(int y, int x) {
        return 0 <= y && y < h && 0 <= x && x < w;
    }
}