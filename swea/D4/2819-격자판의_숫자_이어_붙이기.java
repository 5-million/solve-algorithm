import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * D4
 * 2819 - 격자판의 숫자 이어 붙이기
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AV7I5fgqEogDFAXB
 */
public class Solution {

    private static final int GRID_SIZE = 4;
    private static Set<Integer> set;
    private static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int tc = 1; tc <= t; tc++) {
            int[][] grid = new int[GRID_SIZE][GRID_SIZE];
            set = new HashSet<>();

            for (int r = 0; r < GRID_SIZE; r++) {
                for (int c = 0; c < GRID_SIZE; c++) {
                    grid[r][c] = sc.nextInt();
                }
            }

            for (int r = 0; r < GRID_SIZE; r++) {
                for (int c = 0; c < GRID_SIZE; c++) {
                    dfs(grid, "" + grid[r][c], r, c);
                }
            }

            System.out.printf("#%d %d\n", tc, set.size());
        }
    }

    private static void dfs(int[][] grid, String num, int y, int x) {
        if (num.length() == 7) {
            set.add(Integer.parseInt(num));
            return;
        }

        for (int[] d : dir) {
            int ny = y + d[0];
            int nx = x + d[1];

            if (checkRange(ny, nx)) {
                dfs(grid, num + grid[ny][nx], ny, nx);
            }
        }
    }

    private static boolean checkRange(int y, int x) {
        if (0 <= y && y < GRID_SIZE && 0 <= x && x < GRID_SIZE)
            return true;
        return false;
    }
}