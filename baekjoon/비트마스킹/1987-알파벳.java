import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G4
 * 1987 - 알파벳
 * https://www.acmicpc.net/problem/1987
 */
public class Main {

    private static final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int r, c;
    private static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        board = new char[r][c];

        for (int i = 0; i < r; i++) {
            board[i] = br.readLine().toCharArray();
        }

        System.out.println(dfs(0, 0, 1 << (board[0][0] - 'A'), 1));
    }

    private static int dfs(int y, int x, int bitmask, int cnt) {
        int ret = cnt;
        for (int[] d : dir) {
            int ny = y + d[0];
            int nx = x + d[1];

            if (checkRange(ny, nx) && (bitmask & 1 << (board[ny][nx] - 'A')) == 0) {
                ret = Math.max(ret, dfs(ny, nx, bitmask | 1 << (board[ny][nx] - 'A'), cnt + 1));
            }
        }

        return ret;
    }

    private static boolean checkRange(int y, int x) {
        return 0 <= y && y < r && 0 <= x && x < c;
    }
}