import java.util.Scanner;

/**
 * 모의 SW 역량테스트
 * 1949 - 등산로 조성
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PoOKKAPIDFAUq
 */
public class Solution {

    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int tc = 1; tc <= t; tc++) {
            n = sc.nextInt();
            int k = sc.nextInt();
            int[][] map = new int[n][n];

            int max = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                    max = Math.max(max, map[i][j]);
                }
            }

            int answer = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == max) {
                        boolean[][] v = new boolean[n][n];
                        v[i][j] = true;
                        answer = Math.max(answer, len(map, v, 1, i, j, k));
                    }
                }
            }

            System.out.printf("#%d %d\n", tc, answer);
        }
    }

    private static int len(int[][] map, boolean[][] v, int l, int y, int x, int k) {
        int ret = 0;
        int h = map[y][x];
        for (int[] d : dir) {
            int ny = y + d[0];
            int nx = x + d[1];

            if (checkRange(ny, nx) && !v[ny][nx]) {
                v[ny][nx] = true;

                if (h > map[ny][nx]) {
                    ret = Math.max(ret, len(map, v, l + 1, ny, nx, k));
                } else {
                    if (k != 0) {
                        for (int i = 1; i <= k; i++) {
                            if (h > map[ny][nx] - i) {
                                map[ny][nx] -= i;
                                ret = Math.max(ret, len(map, v, l + 1, ny, nx, 0));
                                map[ny][nx] += i;
                                break;
                            }
                        }
                    }
                }

                v[ny][nx] = false;
            }
        }

        return ret == 0 ? l : ret;
    }

    private static boolean checkRange(int y, int x) {
        if (0 <= y && y < n && 0 <= x && x < n) {
            return true;
        } return false;
    }
}