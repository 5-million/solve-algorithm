import java.util.Scanner;

/**
 * D4
 * 1211 - Ladder2
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AV14BgD6AEECFAYh
 */
public class Solution {

    private static final int[][] dir = {{0, -1}, {0, 1}, {1, 0}};
    private static final int LADDER_SIZE = 100;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int tc = 1; tc <= 10; tc++) {
            int t = sc.nextInt();
            int[][] ladder = new int[LADDER_SIZE][LADDER_SIZE];

            for (int i = 0; i < LADDER_SIZE; i++) {
                for (int j = 0; j < LADDER_SIZE; j++) {
                    ladder[i][j] = sc.nextInt();
                }
            }

            int shortestDist = Integer.MAX_VALUE;
            int shortestX = 0;
            for (int x = 0; x < LADDER_SIZE; x++) {
                if (ladder[0][x] == 1) {
                    int dist = getDist(ladder, 0, x);
                    if (dist < shortestDist) {
                        shortestDist = dist;
                        shortestX = x;
                    }
                }
            }

            System.out.printf("#%d %d\n", tc, shortestX);
        }
    }

    private static int getDist(int[][] ladder, int y, int x) {
        boolean[][] v = new boolean[LADDER_SIZE][LADDER_SIZE];
        v[y][x] = true;

        int dist = 1;
        while (y < 99) {
            for (int[] d : dir) {
                int ny = y + d[0];
                int nx = x + d[1];

                if (checkRange(ny, nx) && ladder[ny][nx] == 1 && !v[ny][nx]) {
                    v[ny][nx] = true;
                    y = ny;
                    x = nx;
                    dist++;
                    break;
                }
            }
        }

        return dist;
    }

    private static boolean checkRange(int y, int x) {
        return y < LADDER_SIZE && 0 <= x && x < LADDER_SIZE;
    }
}