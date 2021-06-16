import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;
    static int cityCount;
    static int[][] costs;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        init();

        int ans = INF;
        for (int start = 0; start < cityCount; start++) {
            ans = Math.min(ans, getMinimumCost(start, start, 0));
        }

        System.out.println(ans);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        cityCount = Integer.parseInt(br.readLine());
        costs = new int[cityCount][cityCount];
        visit = new boolean[cityCount];
        Arrays.fill(visit, false);

        for (int i = 0; i < cityCount; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < cityCount; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static int getMinimumCost(int start, int now, int cost) {
        if (getTrueCount() == cityCount-1) {
            if (costs[now][start] != 0) {
                return cost + costs[now][start];
            } else {
                return INF;
            }
        }

        int ret = INF;
        for (int nxt = 0; nxt < cityCount; nxt++) {
            if (nxt != start && costs[now][nxt] != 0 && !visit[nxt]) {
                visit[nxt] = true;
                ret = Math.min(ret, getMinimumCost(start, nxt, cost + costs[now][nxt]));
                visit[nxt] = false;
            }
        }

        return ret;
    }

    private static int getTrueCount() {
        int count = 0;
        for (boolean v : visit) {
            if (v) count++;
        }

        return count;
    }
}