import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * G5
 * 13023 - ABCDE
 * https://www.acmicpc.net/problem/13023
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Integer>[] friend = new List[n];
        for (int i = 0; i < n; i++) {
            friend[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            friend[a].add(b);
            friend[b].add(a);
        }

        boolean[] v = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (dfs(friend, i, v, 0)) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }

    private static boolean dfs(List<Integer>[] friend, int f, boolean[] v, int cnt) {
        if (cnt == 4) return true;

        v[f] = true;
        for (Integer next : friend[f]) {
            if (!v[next]) {
                if (dfs(friend, next, v, cnt + 1)) {
                    v[f] = false;
                    return true;
                }
            }
        }

        v[f] = false;
        return false;
    }
}