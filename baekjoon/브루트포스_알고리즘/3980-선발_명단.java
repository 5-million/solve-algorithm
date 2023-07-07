import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int SIZE = 11;

    private static int[][] ability;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            ability = new int[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < SIZE; j++) {
                    ability[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            System.out.println(dfs(new boolean[SIZE], 0, 0));
        }
    }

    private static int dfs(boolean[] visit, int idx, int totalAbility) {
        if (idx == SIZE) {
            return totalAbility;
        }

        int ret = 0;
        for (int position = 0; position < SIZE; position++) {
            if (ability[idx][position] > 0 && !visit[position]) {
                visit[position] = true;
                ret = Math.max(ret, dfs(visit, idx + 1, totalAbility + ability[idx][position]));
                visit[position] = false;
            }
        }

        return ret;
    }
}
