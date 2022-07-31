import java.util.Arrays;
import java.util.Scanner;

/**
 * D4
 * 1210 - Ladder1
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14ABYKADACFAYh
 */
public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int tc = 1; tc <= 10; tc++) {
            int t = scanner.nextInt();
            int[][] map = new int[100][100];
            boolean[][] visit = new boolean[100][100];

            int y = 0, x = 0;
            for (int r = 0; r < 100; r++) {
                Arrays.fill(visit[r], false);
                for (int c = 0; c < 100; c++) {
                    map[r][c] = scanner.nextInt();
                    if(map[r][c] == 2) {
                        y = r;
                        x = c;
                    }
                }
            }

            int[][] dir = {{0, -1}, {0, 1}, {-1, 0}};
            while(y > 0) {
                for (int[] d : dir) {
                    int ny = y + d[0];
                    int nx = x + d[1];

                    if (checkRange(ny, nx) && !visit[ny][nx] && map[ny][nx] == 1) {
                        visit[ny][nx] = true;
                        y = ny;
                        x = nx;
                        break;
                    }
                }
            }

            System.out.printf("#%d %d\n", tc, x);
        }
    }

    private static boolean checkRange(int y, int x) {
        if (0 <= y && y < 100 && 0 <= x && x < 100)
            return true;
        return false;
    }
}