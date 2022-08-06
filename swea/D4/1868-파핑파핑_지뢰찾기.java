import java.util.*;

/**
 * D4
 * 1868 - 파핑파핑 지뢰찾기
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AV5LwsHaD1MDFAXc
 */
public class Solution {

    private static final char BOMB = '*';
    private static final char EMPTY = '.';
    private static int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    static class Pos implements Comparable<Pos> {
        int y;
        int x;
        int bombCnt;

        public Pos(int y, int x, int bombCnt) {
            this.y = y;
            this.x = x;
            this.bombCnt = bombCnt;
        }

        @Override
        public int compareTo(Pos o) {
            return this.bombCnt - o.bombCnt;
        }
    }

    private static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            n = sc.nextInt();
            char[][] map = new char[n][n];
            boolean[][] v = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                map[i] = sc.next().toCharArray();
            }

            PriorityQueue<Pos> pq = new PriorityQueue<>();
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (map[r][c] == EMPTY) {
                        pq.add(new Pos(r, c, getBombCnt(map, r, c)));
                    }
                }
            }

            int clickCnt = 0;
            while (!pq.isEmpty()) {
                Pos pos = pq.poll();
                if (!v[pos.y][pos.x]) {
                    clickCnt++;
                    bfs(map, v, pos);
                }
            }


            System.out.printf("#%d %d\n", tc, clickCnt);
        }
    }

    private static void bfs(char[][] map, boolean[][] v, Pos start) {
        Queue<Pos> queue = new LinkedList<>();
        v[start.y][start.x] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            Pos now = queue.poll();
            if (now.bombCnt == 0) {
                for (int[] d : dir) {
                    int ny = now.y + d[0];
                    int nx = now.x + d[1];

                    if (checkRange(ny, nx) && !v[ny][nx] && map[ny][nx] == EMPTY) {
                        v[ny][nx] = true;
                        if (getBombCnt(map, ny, nx) == 0) {
                            queue.add(new Pos(ny, nx, 0));
                        }
                    }
                }
            }
        }
    }

    private static int getBombCnt(char[][] map, int y, int x) {
        int cnt = 0;

        for (int[] d : dir) {
            int ny = y + d[0];
            int nx = x + d[1];

            if (checkRange(ny, nx) && map[ny][nx] == BOMB)
                cnt++;
        }

        return cnt;
    }

    private static boolean checkRange(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}