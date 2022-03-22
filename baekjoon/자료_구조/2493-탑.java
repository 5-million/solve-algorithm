import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * data structure
 * 2493 - 탑(G5)
 * https://www.acmicpc.net/problem/2493
 */
public class Main {

    private static final int MAX_HEIGHT = 100000000;

    private static class Tower {
        int height;
        int pos;

        public Tower(int height, int pos) {
            this.height = height;
            this.pos = pos;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Stack<Tower> stack = new Stack<>();
        stack.add(new Tower(MAX_HEIGHT + 1, 0));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int h = Integer.parseInt(st.nextToken());

            while (stack.peek().height < h) {
                stack.pop();
            }

            sb.append(stack.peek().pos).append(" ");
            stack.add(new Tower(h, i));
        }

        System.out.print(sb);
    }
}
