import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * G3
 * 17135 - 캐슬 디펜스
 * https://www.acmicpc.net/problem/17135
 */
public class Main {

    private static class Pos {
        int y;
        int x;

        public Pos(int y, int x) {
            super();
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Pos) {
                Pos p = (Pos) obj;
                return y == p.y && x == p.x;
            }
            return false;
        }
    }

    private static int n, m, d;
    private static int[][] originMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        originMap = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                originMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(combi(new int[3], 0, 0));
    }

    private static int combi(int[] sel, int idx, int k) {
        if (k == 3) {
            int[][] map = copy();
            int cnt = 0;
            while (!isAllRemove(map)) {
                List<Pos> targets = new ArrayList<>();
                for (int archerIdx : sel) {
                    Pos target = findTarget(map, new Pos(n, archerIdx));
                    if (target != null && !targets.contains(target)) {
                        targets.add(target);
                    }
                }

                for (Pos target : targets) {
                    cnt++;
                    map[target.y][target.x] = 0;
                }

                move(map);
            }

            return cnt;
        }

        if (idx == m)
            return 0;

        int ret = 0;
        sel[k] = idx;
        ret = Math.max(ret, combi(sel, idx + 1, k + 1));
        ret = Math.max(ret, combi(sel, idx + 1, k));
        return ret;
    }

    private static int[][] copy() {
        int[][] temp = new int[n][m];
        for (int i = 0; i < n; i++) {
            temp[i] = Arrays.copyOf(originMap[i], m);
        }
        return temp;
    }

    private static boolean isAllRemove(int[][] map) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static Pos findTarget(int[][] map, Pos archer) {
        int[][] dir = { { 0, -1 }, { -1, 0 }, { 0, 1 } };

        Queue<Pos> q = new LinkedList<>();
        boolean[][] v = new boolean[n][m];

        q.offer(archer);

        int dist = 0;
        while (!q.isEmpty() && dist < d) {
            int qSize = q.size();
            dist++;
            for (int i = 0; i < qSize; i++) {
                Pos cur = q.poll();

                for (int[] di : dir) {
                    int ny = cur.y + di[0];
                    int nx = cur.x + di[1];

                    if (checkRange(ny, nx) && !v[ny][nx]) {
                        Pos next = new Pos(ny, nx);
                        if (map[ny][nx] == 1) {
                            return next;
                        }

                        v[ny][nx] = true;
                        q.offer(next);
                    }
                }
            }
        }

        return null;
    }

    private static boolean checkRange(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }

    private static void move(int[][] map) {
        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < m; j++) {
                map[i][j] = map[i - 1][j];
            }
        }

        for (int j = 0; j < m; j++) {
            map[0][j] = 0;
        }
    }
}