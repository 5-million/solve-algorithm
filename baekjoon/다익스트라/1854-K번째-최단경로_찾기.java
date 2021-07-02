import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * K번째 최단경로 찾기
 */
public class Main {
    static class City implements Comparable<City> {
        int nodeNumber;
        int cost;

        public City(int nodeNumber, int cost) {
            this.nodeNumber = nodeNumber;
            this.cost = cost;
        }

        @Override
        public int compareTo(City city) {
            return this.cost - city.cost;
        }
    }

    final static int START = 0;
    static int n;
    static int m;
    static int k;
    static List<City>[] edge;
    static PriorityQueue<Integer>[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        edge = new List[n];
        dist = new PriorityQueue[n];

        for (int i = 0; i < n; i++) {
            edge[i] = new ArrayList<>();
            dist[i] = new PriorityQueue<>(Comparator.reverseOrder());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            edge[a].add(new City(b, c));
        }

        dijkstra();
        print();
    }

    private static void dijkstra() {
        PriorityQueue<City> pq = new PriorityQueue<>();
        pq.add(new City(START, 0));
        dist[START].add(0);

        while (!pq.isEmpty()) {
            City now = pq.poll();

            for (City next : edge[now.nodeNumber]) {
                if (dist[next.nodeNumber].size() < k) {
                    dist[next.nodeNumber].add(now.cost + next.cost);
                    pq.add(new City(next.nodeNumber, now.cost + next.cost));
                } else if (dist[next.nodeNumber].peek() > now.cost + next.cost) {
                    dist[next.nodeNumber].poll();
                    dist[next.nodeNumber].add(now.cost + next.cost);
                    pq.add(new City(next.nodeNumber, now.cost + next.cost));
                }
            }
        }
    }

    private static void print() {
        for (PriorityQueue<Integer> pq : dist) {
            if (pq.size() < k) System.out.println(-1);
            else System.out.println(pq.poll());
        }
    }
}