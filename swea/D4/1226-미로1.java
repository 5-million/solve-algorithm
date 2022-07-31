import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * D4
 * 1226 - 미로1
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14vXUqAGMCFAYD
 */
public class Solution {

    private static final int MAP_SIZE = 16;
    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static class Position {
        int y;
        int x;

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Position) {
                Position pos = (Position) obj;
                return this.y == pos.y && this.x == pos.x;
            } else return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            int tc = sc.nextInt();
            int[][] map = new int[MAP_SIZE][MAP_SIZE];
            boolean[][] visit = new boolean[MAP_SIZE][MAP_SIZE];

            Position start = null, dest = null;
            for (int r = 0; r < 16; r++) {
                Arrays.fill(visit[r], false);
                String[] line = sc.next().split("");
                for (int c = 0; c < 16; c++) {
                    map[r][c] = Integer.parseInt(line[c]);

                    if (map[r][c] == 2) {
                        start = new Position(r, c);
                        visit[r][c] = true;
                    }
                        
                    if (map[r][c] == 3) dest = new Position(r, c);
                }
            }

            System.out.printf("#%d %d\n", tc, canEscape(map, visit, start, dest) ? 1 : 0);
        }
    }

    private static boolean canEscape(int[][] map, boolean[][] visit, Position start, Position dest) {
        Queue<Position> qu = new LinkedList<>();
        qu.add(start);

        while (!qu.isEmpty()) {
            Position now = qu.poll();

            if (now.equals(dest)) {
                return true;
            }

            for (int[] d : dir) {
                int ny = now.y + d[0];
                int nx = now.x + d[1];

                if (checkRange(ny, nx) && !visit[ny][nx] && map[ny][nx] != 1) {
                    qu.add(new Position(ny, nx));
                    visit[ny][nx] = true;
                }
            }
        }

        return false;
    }

    private static boolean checkRange(int y, int x) {
        if (0 <= y && y < MAP_SIZE && 0 <= x && x <= MAP_SIZE)
            return true;
        return false;
    }
}