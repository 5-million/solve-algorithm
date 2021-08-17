import java.util.*;

public class Main {

    private static final int MOD = 10000;
    private static final char[] operations = {'D', 'S', 'L', 'R'};

    private static int t;
    private static Scanner sc = new Scanner(System.in);
    private static boolean[] visited;
    private static class Node {
        int value;
        String history;

        public Node(int value, String history) {
            this.value = value;
            this.history = history;
        }
    }

    public static void main(String[] args) {
        t = sc.nextInt();

        for (int i = 0; i < t; i++) {
            visited = new boolean[10000];
            solution();
        }
    }

    private static void solution() {
        int a = sc.nextInt();
        int b = sc.nextInt();

        System.out.println(execute(a, b));
    }

    private static String execute(int start, int goal) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(start, ""));

        String result = "";
        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.value == goal) {
                result = now.history;
                break;
            }

            for (char operation : operations) {
                int next = operate(operation, now.value);

                if (!visited[next]) {
                    queue.add(new Node(next, now.history + operation));
                    visited[next] = true;
                }
            }
        }

        return result;
    }

    private static int operate(char oper, int value) {
        switch (oper) {
            case 'D':
                return operateD(value);

            case 'S':
                return operateS(value);

            case 'L':
                return operateL(value);

            default:
                return operateR(value);
        }
    }

    private static int operateD(int value) {
        return (value * 2) % MOD;
    }

    private static int operateS(int value) {
        return value == 0 ? 9999 : value - 1;
    }

    private static int operateL(int value) {
        return (value % 1000) * 10 + value / 1000;
    }

    private static int operateR(int value) {
        return (value % 10) * 1000 + value / 10;
    }
}