import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LEVEL-3
 * 그래프 > 가장 먼 노드
 */
public class Solution {

    public int solution(int n, int[][] edge) {
        int[] distance = new int[n + 1];
        ArrayList<Integer>[] vertex = new ArrayList[n + 1];
        boolean[] visit = new boolean[n + 1];

        for (int i = 0; i < n + 1; i++)
            vertex[i] = new ArrayList<>();

        for (int[] v : edge) {
            vertex[v[0]].add(v[1]);
            vertex[v[1]].add(v[0]);
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visit[1] = true;

        int d = 0;
        while (!q.isEmpty()) {
            d++;
            int qSize = q.size();

            for (int i = 0; i < qSize; i++) {
                int now = q.poll();

                for (Integer next : vertex[now])
                    if (!visit[next]) {
                        q.add(next);
                        visit[next] = true;
                        distance[next] = d;
                    }
            }
        }

        int answer = 0;
        for (int dist : distance)
            if (d - 1 == dist)
                answer++;

        return answer;
    }
}
