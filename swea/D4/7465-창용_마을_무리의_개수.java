import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * D4
 * 7465 - 창용 마을 무리의 개수
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWngfZVa9XwDFAQU
 */
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(parent, a, b);
            }

            Set<Integer> set = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                set.add(findParent(parent, i));
            }

            System.out.printf("#%d %d\n", tc, set.size());
        }
    }

    private static int findParent(int[] parent, int i) {
        if (parent[i] != i) {
            parent[i] = findParent(parent, parent[i]);
        }
        return parent[i];
    }

    private static void union(int[] parent, int a, int b) {
        int ap = findParent(parent, a);
        int bp = findParent(parent, b);

        if (ap != bp) {
            if (ap > bp) parent[ap] = bp;
            else parent[bp] = ap;
        }
    }
}