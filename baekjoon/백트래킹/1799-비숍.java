import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * G1
 * 1799 - 비숍
 * https://www.acmicpc.net/problem/1799
 */
public class Main {

    static class Bishop {
        int y;
        int x;

        public Bishop(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Bishop> white = new ArrayList<>();
        List<Bishop> black = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    if (i % 2 == j % 2) {
                        white.add(new Bishop(i, j));
                    } else {
                        black.add(new Bishop(i, j));
                    }
                }
            }
        }

        System.out.println(
                combi(white, new boolean[white.size()], 0) + combi(black, new boolean[black.size()], 0)
        );
    }

    private static int combi(List<Bishop> bishops, boolean[] sel, int idx) {
        if (idx == bishops.size()) {
            int cnt = 0;
            for (boolean s : sel) {
                if (s) {
                    cnt++;
                }
            }
            return cnt;
        }

        int ret = 0;
        if (ableToPut(bishops, idx, sel)) {
            sel[idx] = true;
            ret = Math.max(ret, combi(bishops, sel, idx + 1));
            sel[idx] = false;
        }

        return Math.max(ret, combi(bishops, sel, idx + 1));
    }

    private static boolean ableToPut(List<Bishop> bishops, int idx, boolean[] sel) {
        for (int i = 0; i < sel.length; i++) {
            if (sel[i] && Math.abs(bishops.get(i).y - bishops.get(idx).y) == Math.abs(bishops.get(i).x - bishops.get(idx).x))
                return false;
        }
        return true;
    }
}