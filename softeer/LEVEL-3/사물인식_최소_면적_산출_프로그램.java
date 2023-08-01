import java.util.*;
import java.io.*;


public class Main {

    private static int n, k;
    private static List<int[]>[] dots;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dots = new List[k + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int color = Integer.parseInt(st.nextToken());
            
            if (dots[color] == null) {
                dots[color] = new ArrayList<>();
            }

            dots[color].add(new int[]{y, x});
        }

        int[] lu = {-1000, 1000};
        int[] rd = {1000, -1000};
        solution(1, lu, rd);
        System.out.println(answer);
    }

    private static void solution(int color, int[] lu, int[] rd) {
        if (color > k) {
            answer = Math.min(answer, getArea(lu, rd));
            return;
        }

        for (int[] dot : dots[color]) {
            int[] tlu = new int[2];
            int[] trd = new int[2];

            tlu[0] = Math.max(lu[0], dot[0]);
            tlu[1] = Math.min(lu[1], dot[1]);
            trd[0] = Math.min(rd[0], dot[0]);
            trd[1] = Math.max(rd[1], dot[1]);

            if (answer > getArea(tlu, trd)) {
                solution(color + 1, tlu, trd);
            }
        }
    }

    private static int getArea(int[] lu, int[] rd) {
        return (lu[0] - rd[0]) * (rd[1] - lu[1]);
    }
}