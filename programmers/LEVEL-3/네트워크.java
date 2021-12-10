import java.util.LinkedList;
import java.util.Queue;

/**
 * LEVEL-3
 * 깊이/너비 우선 탐색 > 네트워크
 */
public class Solution {

    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visit = new boolean[n];
        for (int computer = 0; computer < n; computer++) {
            if (!visit[computer]) {
                bfs(n, computers, computer, visit);
                answer++;
            }
        }

        return answer;
    }

    private void bfs(int n, int[][] computers, int start, boolean[] visit) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next = 0; next < n; next++) {
                if (!visit[next] && computers[now][next] == 1) {
                    visit[next] = true;
                    q.add(next);
                }
            }
        }
    }
}