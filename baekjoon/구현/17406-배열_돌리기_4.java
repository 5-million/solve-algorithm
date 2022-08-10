import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * G4
 * 17406 - 배열 돌리기 4
 * https://www.acmicpc.net/problem/17406
 */
public class Main {

    static class Operation {
        int r;
        int c;
        int s;

        public Operation(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

    private static int[][] arr;
    private static Operation[] operations;
    private static int n, m, k, answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n + 1][m + 1];
        operations = new Operation[k];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            operations[i] = new Operation(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            );
        }

        permu(new boolean[k], 0, new int[k]);
        System.out.println(answer);
    }

    private static void permu(boolean[] v, int idx, int[] sel) {
        if (idx == k) {
            int[][] copy = copy();
            for (int oIdx : sel) {
                rotate(copy, operations[oIdx]);
            }

            answer = Math.min(answer, getArrValue(copy));
            return;
        }

        for (int i = 0; i < k; i++) {
            if (!v[i]) {
                v[i] = true;
                sel[idx] = i;
                permu(v, idx + 1, sel);
                v[i] = false;
            }
        }
    }

    private static void rotate(int[][] arr, Operation operation) {
        int depth = 0;
        int cnt = (2 * operation.s + 1) / 2;
        while (depth < cnt) {
            int sr = operation.r - operation.s + depth;
            int sc = operation.c - operation.s + depth;
            int dr = operation.r + operation.s - depth;
            int dc = operation.c + operation.s - depth;

            int temp = arr[sr][sc];
            for (int i = sr; i < dr; i++) {
                arr[i][sc] = arr[i + 1][sc];
            }

            for (int i = sc; i < dc; i++) {
                arr[dr][i] = arr[dr][i + 1];
            }

            for (int i = dr; i > sr; i--) {
                arr[i][dc] = arr[i - 1][dc];
            }

            for (int i = dc; i > sc; i--) {
                arr[sr][i] = arr[sr][i - 1];
            }

            arr[sr][sc + 1] = temp;
            depth++;
        }
    }

    private static int[][] copy() {
        int[][] temp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                temp[i][j] = arr[i][j];
            }
        }
        return temp;
    }

    private static int getArrValue(int[][] arr) {
        int ret = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= m; j++) {
                sum += arr[i][j];
            }
            ret = Math.min(ret, sum);
        }
        return ret;
    }
}