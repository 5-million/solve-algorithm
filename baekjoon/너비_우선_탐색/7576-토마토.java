import java.util.*;

/**
 * 너비 우선 탐색 > 토마토
 */
public class Boj7576 {

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static int n;
    private static int m;
    private static int[][] grid;
    private static List<Point> ripe = new ArrayList<>();

    private static final int RIPE = 1;
    private static final int CRUDE = 0;

    public static void main(String[] args) {
        init();
        System.out.println(solution());
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();

        grid = new int[n][m];

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                grid[y][x] = sc.nextInt();
                if (grid[y][x] == RIPE)
                    ripe.add(new Point(y, x));
            }
        }
    }

    private static int solution() {
        int time = -1;
        Queue<Point> q = new LinkedList<>(ripe);
        int[][] direc = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (!q.isEmpty()) {
            time++;
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                Point now = q.poll();

                for (int[] d : direc) {
                    int ny = now.y + d[0];
                    int nx = now.x + d[1];

                    if (0 <= ny && ny < n && 0 <= nx && nx < m && grid[ny][nx] == CRUDE) {
                        grid[ny][nx] = RIPE;
                        q.add(new Point(ny, nx));
                    }
                }
            }
        }

        return isAllRipe()? time : -1;
    }

    private static boolean isAllRipe() {
        for (int[] col : grid) {
            for (int tomato : col) {
                if (tomato == CRUDE)
                    return false;
            }
        }

        return true;
    }
}
