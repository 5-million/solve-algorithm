import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LEVEL-3
 * 2021 KAKAO BLIND RECRUITMENT > 합승 택시 요금
 */
class Solution {
    private class Connect implements Comparable<Connect> {
        int node;
        int cost;

        public Connect(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compareTo(Connect o) {
            return this.cost - o.cost;
        }
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int result = Integer.MAX_VALUE;
        int[][] map = new int[n + 1][n + 1];
        for (int[] fare : fares) {
            int node1 = fare[0];
            int node2 = fare[1];
            int cost = fare[2];
            map[node1][node2] = cost;
            map[node2][node1] = cost;
        }

        int[] costFromS = dijkstra(n, s, map);
        int[] costFromA = dijkstra(n, a, map);
        int[] costFromB = dijkstra(n, b, map);

        for (int wp = 1; wp <= n; wp++)
            result = Math.min(result, costFromS[wp] + costFromA[wp] + costFromB[wp]);

        return result;
    }

    private int[] dijkstra(int n, int start, int[][] map) {
        int[] cost = new int[n+1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        PriorityQueue<Connect> pq = new PriorityQueue<>();

        cost[start] = 0;
        pq.add(new Connect(start, 0));

        while (!pq.isEmpty()) {
            Connect now = pq.poll();

            if (cost[now.node] < now.cost)
                continue;

            for (int next= 1; next <= n; next++) {
                if (map[now.node][next] != 0 && cost[next] > now.cost + map[now.node][next]) {
                    cost[next] = now.cost + map[now.node][next];
                    pq.add(new Connect(next, cost[next]));
                }
            }
        }

        return cost;
    }
}