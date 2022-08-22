import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * G1
 * 17472 - 다리 만들기 2
 * https://www.acmicpc.net/problem/17472
 */
public class Main {

    private static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static class Bridge implements Comparable<Bridge> {
        int p1Id;
        Point p1;
        int p2Id;
        Point p2;
        int dist;

        public Bridge(int p1Id, Point p1, int p2Id, Point p2) {
            this.p1Id = p1Id;
            this.p1 = p1;
            this.p2Id = p2Id;
            this.p2 = p2;
            setDist();
        }

        private void setDist() {
            this.dist = Math.abs(p1.y - p2.y) + Math.abs(p1.x - p2.x) - 1;
        }

        @Override
        public int compareTo(Bridge o) {
            return dist - o.dist;
        }
    }

    private static final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static int n, m;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxIslandId = getMaxIslandId();
        int[] parent = new int[maxIslandId + 1];
        for (int i = 1; i <= maxIslandId; i++) {
            parent[i] = i;
        }

        PriorityQueue<Bridge> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0 && isEdge(i, j)) {
                    findConnection(pq, i, j);
                }
            }
        }

        int totalDist = 0;
        while (!pq.isEmpty()) {
            totalDist += union(parent, pq.poll());
        }

        if (!isAllConnected(parent) || totalDist == 0) {
            System.out.println(-1);
        } else System.out.println(totalDist);
    }

    private static int getMaxIslandId() {
        boolean[][] v = new boolean[n][m];
        int id = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !v[i][j]) {
                    v[i][j] = true;
                    map[i][j] = id;
                    bfs(new Point(i, j), id++, v);
                }
            }
        }
        return id -1;
    }

    private static void bfs(Point start, int islandId, boolean[][] v) {
        Queue<Point> q = new LinkedList<>();
        q.offer(start);

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int[] d : dir) {
                int ny = cur.y + d[0];
                int nx = cur.x + d[1];

                if (checkRange(ny, nx) && map[ny][nx] == 1 && !v[ny][nx]) {
                    v[ny][nx] = true;
                    map[ny][nx] = islandId;
                    q.offer(new Point(ny, nx));
                }
            }
        }
    }

    private static boolean checkRange(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }

    private static boolean isEdge(int y, int x) {
        for (int[] d : dir) {
            int ny = y + d[0];
            int nx = x + d[1];

            if (!checkRange(ny, nx))
                continue;

            if (map[ny][nx] == 0)
                return true;
        }

        return false;
    }

    private static void findConnection(PriorityQueue<Bridge> pq, int y, int x) {
        for (int d = 0; d < 4; d++) {
            int ny = y + dir[d][0];
            int nx = x + dir[d][1];

            while (checkRange(ny, nx)) {
                if (map[ny][nx] != 0) {
                    if (map[ny][nx] != map[y][x]) {
                        Bridge bridge = new Bridge(map[y][x], new Point(y, x), map[ny][nx], new Point(ny, nx));
                        if (bridge.dist >= 2) {
                            pq.offer(bridge);
                        }
                    }

                    break;
                }

                ny += dir[d][0];
                nx += dir[d][1];
            }
        }
    }

    private static int union(int[] parent, Bridge bridge) {
        int island1Parent = findParent(parent, bridge.p1Id);
        int island2Parent = findParent(parent, bridge.p2Id);

        if (island1Parent != island2Parent) {
            if (island1Parent > island2Parent) {
                parent[island1Parent] = island2Parent;
            } else parent[island2Parent] = island1Parent;

            return bridge.dist;
        }

        return 0;
    }

    private static int findParent(int[] parent, int islandId) {
        if (parent[islandId] == islandId)
            return islandId;

        parent[islandId] = findParent(parent, parent[islandId]);
        return parent[islandId];
    }

    private static boolean isAllConnected(int[] parent) {
        int p = findParent(parent, 1);
        for (int i = 2; i < parent.length; i++) {
            if (p != findParent(parent, i)) {
                return false;
            }
        }
        return true;
    }
}