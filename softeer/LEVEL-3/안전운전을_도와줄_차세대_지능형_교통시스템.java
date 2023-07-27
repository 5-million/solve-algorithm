import java.util.*;
import java.io.*;


public class Main {

    private static final int DOWN = 0, RIGHT = 1, UP = 2, LEFT = 3;

    private static int[][][] signals = {
        {},
        {{-1, 0, UP}, {0, 1, RIGHT}, {1, 0, DOWN}}, // 1
        {{0, -1, LEFT}, {-1, 0, UP}, {0, 1, RIGHT}}, // 2
        {{-1, 0, UP}, {0, -1, LEFT}, {1, 0, DOWN}}, // 3
        {{0, -1, LEFT}, {1, 0, DOWN}, {0, 1, RIGHT}}, // 4
        {{-1, 0, UP}, {0, 1, RIGHT}}, // 5
        {{0, -1, LEFT}, {-1, 0, UP}}, // 6
        {{0, -1, LEFT}, {1, 0, DOWN}}, // 7
        {{1, 0, DOWN}, {0, 1, RIGHT}}, // 8
        {{0, 1, RIGHT}, {1, 0, DOWN}}, // 9
        {{-1, 0, UP}, {0, 1, RIGHT}}, // 10
        {{-1, 0, UP}, {0, -1, LEFT}}, // 11
        {{0, -1, LEFT}, {1, 0, DOWN}} // 12
    };

    private static int[][] signalSet;
    private static boolean[][] visit;
    private static int n, timeLimit, answer;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        timeLimit = Integer.parseInt(st.nextToken());

        signalSet = new int[n * n + 1][4];
        visit = new boolean[n + 1][n + 1];

        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= n; x++) {
                st = new StringTokenizer(br.readLine());

                int[] set = new int[4];
                for (int i = 0; i < 4; i++) {
                    set[i] = Integer.parseInt(st.nextToken());
                }

                signalSet[(y - 1) * n + x] = set;
            }
        }

        dfs(new int[]{1, 1}, UP, 0);
        System.out.println(answer);
    }

    private static void dfs(int[] cur, int d, int time) {
        if (!visit[cur[0]][cur[1]]) {
            visit[cur[0]][cur[1]] = true;
            answer++;
        }

        if (time == timeLimit) {
            return;
        }

        int signal = getSignal(cur, time);
        if (d != signal % 4) { // 진행방향과 신호가 맞지 않는 경우
            return;
        }

        for (int[] s : signals[signal]) {
            int ny = cur[0] + s[0];
            int nx = cur[1] + s[1];

            if (checkRange(ny, nx)) {
                dfs(new int[]{ny, nx}, s[2], time + 1);
            }
        }
    }

    private static int getSignal(int[] cur, int time) {
        int crossNum = getCrossNum(cur);
        return signalSet[crossNum][time % 4];
    }

    private static int getCrossNum(int[] pos) {
        return n * (pos[0] - 1) + pos[1];
    }

    private static boolean checkRange(int y, int x) {
        return 1 <= y && y <= n && 1 <= x && x <= n;
    }
}