import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * G3
 * 16724 - 피리 부는 사나이
 * https://www.acmicpc.net/problem/16724
 */
public class BOJ16724 {

    private static int n, m;
    private static char[][] map;
    private static boolean[][] visit, done;

    public static void main(String[] args) throws IOException {
        init();

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j]) {
                    ans += dfs(i, j);
                }
            }
        }

        System.out.println(ans);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visit = new boolean[n][m];
        done = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }
    }

    private static int dfs(int y, int x) {
        if (visit[y][x]) { // 이미 방문한 곳이고
            if (done[y][x]) {
                // 이미 만들어진 사이클인 경우 새로운 SAFE ZONE 을 생성하지 않아도 SAFE ZONE으로 들어갈 수 있으므로 0 리턴
                return 0;
            } else return 1; // 사이클이 만들어지지 않았을 때 방문한 노드를 재방문하면 사이클이 생기므로 SAFE ZONE 하나 생성
        }

        visit[y][x] = true;

        int[] next = getNext(y, x);
        int ret = dfs(next[0], next[1]);
        done[y][x] = true; // dfs의 결과로 무조건 사이클이 만들어지거나 사이클로 연결되기 때문에 처리가 끝남 표시

        return ret;
    }

    // 현재 위치를 기준으로 다음 좌표를 리턴하는 함수
    private static int[] getNext(int y, int x) {
        char dir = map[y][x];
        if (dir == 'U') {
            return new int[]{y - 1, x};
        } else if (dir == 'R') {
            return new int[]{y, x + 1};
        } else if (dir == 'D') {
            return new int[]{y + 1, x};
        } else {
            return new int[]{y, x - 1};
        }
    }
}
