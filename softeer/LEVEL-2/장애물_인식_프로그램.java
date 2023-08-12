import java.util.*;
import java.io.*;


public class Main {

    private static int n;
    private static int[][] map;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        boolean[][] visit = new boolean[n][n];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (map[y][x] == 1 && !visit[y][x]) {
                    pq.add(getArea(y, x, visit));
                }
            }
        }

        System.out.println(pq.size());
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }

    private static int getArea(int sy, int sx, boolean[][] visit) {
        Queue<int[]> q = new ArrayDeque<>();

        q.add(new int[] {sy, sx});
        visit[sy][sx] = true;

        int area = 0;
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while (!q.isEmpty()) {
            area++;
            int[] cur = q.poll();

            for (int[] d : dir) {
                int ny = cur[0] + d[0];
                int nx = cur[1] + d[1];
 
                if (checkRange(ny, nx) && !visit[ny][nx] && map[ny][nx] == 1) {
                    visit[ny][nx] = true;
                    q.add(new int[] {ny, nx});
                }
            }
        }

        return area;
    }

    private static boolean checkRange(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}