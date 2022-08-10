import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
/**
 * 모의 SW 역량테스트
 * 1953 - 탈주범 검거
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpLlKAQ4DFAUq
 */
public class Solution {
     
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
 
    static class Position {
        int y;
        int x;
 
        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
 
    // 상:0, 우:1, 하:2, 좌:3
    private static final int[][][] dir = {
            {},
            {{-1, 0, UP}, {0, 1, RIGHT}, {1, 0, DOWN}, {0, -1, LEFT}}, // 상하좌우
            {{-1, 0, UP}, {1, 0, DOWN}}, // 상하
            {{0, 1, RIGHT}, {0, -1, LEFT}}, // 좌우
            {{-1, 0, UP}, {0, 1, RIGHT}}, // 상우
            {{1, 0, DOWN}, {0, 1, RIGHT}}, // 하우
            {{1, 0, DOWN}, {0, -1, LEFT}}, // 하좌
            {{-1, 0, UP}, {0, -1, LEFT}} // 상좌
    };
     
    private static final Set<Integer>[] connectable = new Set[4];
     
    private static void setConnectable() {
        for (int i=0; i<4; i++) {
            connectable[i] = new HashSet<>();
        }
         
        connectable[UP].addAll(Arrays.asList(new Integer[]{1,2,5,6}));
        connectable[RIGHT].addAll(Arrays.asList(new Integer[]{1,3,6,7}));
        connectable[DOWN].addAll(Arrays.asList(new Integer[]{1,2,4,7}));
        connectable[LEFT].addAll(Arrays.asList(new Integer[]{1, 3,4,5}));
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        setConnectable();
         
        int t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
 
            int[][] underground = new int[n][m];
            for (int y = 0; y < n; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < m; x++) {
                    underground[y][x] = Integer.parseInt(st.nextToken());
                }
            }
 
            Queue<Position> queue = new LinkedList<>();
            boolean[][] v = new boolean[n][m];
            queue.add(new Position(r,  c));
            v[r][c] = true;
            int time = 0, answer = 1;
            while (time < l - 1 && !queue.isEmpty()) {
                time++;
                int qSize = queue.size();
                for (int i = 0; i < qSize; i++) {
                    Position now = queue.poll();
                    for (int[] d : dir[underground[now.y][now.x]]) {
                        int ny = now.y + d[0];
                        int nx = now.x + d[1];
                         
                        if (checkRange(n, m, ny, nx) && !v[ny][nx] && underground[ny][nx] != 0 && connectable[d[2]].contains(underground[ny][nx])) {
                            v[ny][nx] = true;
                            answer++;
                            queue.add(new Position(ny, nx));
                        }
                    }
                }
            }
 
            System.out.printf("#%d %d\n", tc, answer);
        }
    }
 
    private static boolean checkRange(int n, int m, int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}
