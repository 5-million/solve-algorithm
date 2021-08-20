ASDASDASDimport java.util.*;

public class Main {

    private static final int EMPTY = 0;
    private static final int RED = 1;
    private static final int BLUE = 2;
    private static Scanner sc = new Scanner(System.in);
    private static int k;
    private static int v;
    private static int e;
    private static class Node {
        int number;
        int color;

        public Node(int number, int color) {
            this.number = number;
            this.color = color;
        }
    }

    public static void main(String[] args) {
        k = sc.nextInt();
        for (int i = 0; i < k; i++) {
            if (solution()) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    private static boolean solution() {
        v = sc.nextInt();
        e = sc.nextInt();

        ArrayList<Integer>[] graph = new ArrayList[v+1];
        for (int i = 1; i <= v; i++)
            graph[i] = new ArrayList<>();
        int[] color = new int[v+1];

        fillGraph(graph);

        for (int start = 1; start <= v; start++) {
            if (color[start] == EMPTY) {
                if(!bfs(graph, color, start))
                    return false;
            }
        }

        return true;
    }

    private static void fillGraph(ArrayList<Integer>[] graph) {
        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            graph[a].add(b);
            graph[b].add(a);
        }
    }

    private static boolean bfs(ArrayList<Integer>[] graph, int[] color, int start) {
        Queue<Node> q = new LinkedList<>();

        q.add(new Node(start, RED));
        color[start] = RED;

        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                Node now = q.poll();

                for (int next : graph[now.number]) {
                    if (color[next] == now.color)
                        return false;

                    if (color[next] == EMPTY) {
                        color[next] = now.color == RED ? BLUE : RED;
                        q.add(new Node(next, color[next]));
                    }
                }
            }
        }

        return true;
    }
}