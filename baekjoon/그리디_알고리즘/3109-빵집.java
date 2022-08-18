import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G2
 * 3109 - 빵집
 * https://www.acmicpc.net/problem/3109
 */
public class Main {

    private static final int[][] dir = {{-1, 1}, {0, 1}, {1, 1}};
    private static int r, c;
    private static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                if (input[j] == '.') {
                    map[i][j] = true;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < r; i++) {
            answer += dfs(i, 0);
        }
        System.out.println(answer);
    }

    private static int dfs(int y, int x) {
        map[y][x] = false;
        if (x == c - 1) return 1;
        
        for (int[] d : dir) {
            int ny = y + d[0];
            int nx = x + d[1];

            if (check(ny, nx)) {
                if (dfs(ny, nx) == 1) {
                    return 1;
                }
            }
        }
        return 0;
    }

    private static boolean check(int y, int x) {
        return 0 <= y && y < r && map[y][x];
    }
}