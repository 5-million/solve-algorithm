import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * LEVEL-3
 * 그래프 > 순위
 */
public class Solution {

    private class Node {
        int front;
        int back;

        public Node(int front, int back) {
            this.front = front;
            this.back = back;
        }

        public int isDeterminable(int n) {
            return n - 1 == front + back ? 1 : 0;
        }
    }

    private Node[] nodes;
    private List<Integer>[] graph;

    public int solution(int n, int[][] results) {
        init(n, results);

        for (int i = 1; i <= n; i++)
            bfs(n, i);

        int answer = 0;
        for (Node node : nodes)
            answer += node.isDeterminable(n);

        return answer;
    }

    private void init(int n, int[][] results) {
        graph = new List[n + 1];
        nodes = new Node[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
            nodes[i] = new Node(0, 0);
        }

        for (int[] result : results)
            graph[result[1]].add(result[0]);
    }

    public void bfs(int n, int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visit = new boolean[n + 1];

        q.add(start);
        visit[start] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (Integer front : graph[now]) {
                if (!visit[front]) {
                    visit[front] = true;
                    q.add(front);
                    nodes[start].front++;
                    nodes[front].back++;
                }
            }
        }
    }
}