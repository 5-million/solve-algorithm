import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * G3
 * 1726 - 로봇
 * https://www.acmicpc.net/problem/1726
 */
public class Main {

    private static final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static class Robot implements Comparable<Robot> {
        int y;
        int x;
        int d;
        int orderCnt;

        public Robot(int y, int x, int d, int orderCnt) {
            this.y = y;
            this.x = x;
            this.d = d;
            this.orderCnt = orderCnt;
        }

        @Override
        public int compareTo(Robot o) {
            return Integer.compare(orderCnt, o.orderCnt);
        }
    }

    private static int m, n; // m: 세로길이, n: 가로길이
    private static int[][] map;
    private static Robot dest;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[m][n];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        Robot start = new Robot(
                Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken()) - 1,
                getDirIdx(Integer.parseInt(st.nextToken())),
                0
        );

        st = new StringTokenizer(br.readLine());
        dest = new Robot(
                Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken()) - 1,
                 getDirIdx(Integer.parseInt(st.nextToken())),
                0
        );

        System.out.println(bfs(start));
    }

    private static int getDirIdx(int d) {
        switch (d) {
            case 1:
                return 1;
            case 2:
                return 3;
            case 3:
                return 2;
            default:
                return 0;
        }
    }

    private static int bfs(Robot start) {
        PriorityQueue<Robot> pq = new PriorityQueue<>();
        boolean[][][] visit = new boolean[m][n][4];

        pq.add(start);
        visit[start.y][start.x][start.d] = true;

        int orderCnt = 0;
        while (!pq.isEmpty()) {
            Robot robot = pq.poll();

            if (robot.y == dest.y && robot.x == dest.x && robot.d == dest.d) {
                orderCnt = robot.orderCnt;
                break;
            }

            for (int i = 1; i <= 3; i++) {
                int ny = robot.y + (dir[robot.d][0] * i);
                int nx = robot.x + (dir[robot.d][1] * i);

                if (check(ny, nx) && map[ny][nx] == 0) {
                    if (!visit[ny][nx][robot.d]) {
                        visit[ny][nx][robot.d] = true;
                        pq.add(new Robot(ny, nx, robot.d, robot.orderCnt + 1));
                    }
                } else break;
            }

            for (int i = 1; i < 4; i++) {
                int nd = (robot.d + i) % 4;
                int oCnt = Math.min(i, 4 - i);

                if (!visit[robot.y][robot.x][nd]) {
                    visit[robot.y][robot.x][nd] = true;
                    pq.add(new Robot(robot.y, robot.x, nd, robot.orderCnt + oCnt));
                }
            }
        }

        return orderCnt;
    }

    private static boolean check(int y, int x) {
        return 0 <= y && y < m && 0 <= x && x < n;
    }
}