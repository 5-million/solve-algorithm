import java.io.*;
import java.util.*;

/**
 * G3
 * 2623 - 음악프로그램
 * https://www.acmicpc.net/problem/2623
 */
public class Main {

    private static int n, m;
    private static List<Integer>[] adjList;
    private static int[] inDegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        inDegree = new int[n + 1];
        adjList = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            int pre = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int cur = Integer.parseInt(st.nextToken());
                adjList[pre].add(cur);
                inDegree[cur]++;

                pre = cur;
            }
        }

        topologicalSort();
    }

    private static void topologicalSort() throws IOException {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        Queue<Integer> seq = new ArrayDeque<>();
        while (!q.isEmpty()) {
            int now = q.poll();
            seq.add(now);

            for (Integer next : adjList[now]) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        if (seq.size() != n) {
            System.out.println(0);
        } else {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            while (!seq.isEmpty()) {
                bw.write("" + seq.poll() + "\n");
            }
            bw.flush();
            bw.close();
        }
    }
}