import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * G2
 * 2250 - 트리의 높이와 너비
 * https://www.acmicpc.net/problem/2250
 */
public class P2250 {

    private static class Level implements Comparable<Level> {
        private int level;
        private int left;
        private int right;

        public Level(int level) {
            this.level = level;
        }

        public int getWidth() {
            // right == 0 -> 루트 노드인 경우 너비 = 1
            return right == 0 ? 1 : right - left + 1;
        }

        @Override
        public int compareTo(Level o) {
            if (o.getWidth() != this.getWidth()) {
                return o.getWidth() - this.getWidth();
            } else return this.level - o.level;
        }
    }

    private static int n, col;
    private static int[][] child; // 0번째 인덱스 -> 왼쪽 / 1번째 인덱스 -> 오른쪽
    private static boolean[] isRoot;
    private static List<Level> levels = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();
        inorder(getRoot(), 1);

        // 조건에 맞게 정렬하여 하나만 뽑아내기 위해 우선순위 큐 사용
        PriorityQueue<Level> pq = new PriorityQueue<>();
        // 루트 노드만 존재할 경우 임의로 넣어놓은 객체가 우선순위가 높으므로 임의의 객체를 제외한 리스트를 우선순위큐에 add
        pq.addAll(levels.subList(1, levels.size()));
        Level answer = pq.poll();

        System.out.println(answer.level + " " + answer.getWidth());
    }
    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        child = new int[n + 1][2];
        isRoot = new boolean[n + 1];
        Arrays.fill(isRoot, true);
        
        levels.add(new Level(0));

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            child[node][0] = left;
            if (left != -1) {
                isRoot[left] = false;
            }

            child[node][1] = right;
            if (right != -1) {
                isRoot[right] = false;
            }
        }
    }
    
    private static int getRoot() {
        for (int i = 1; i <= n; i++) {
            if (isRoot[i]) {
                return i;
            }
        }
        return -1;
    }

    private static void inorder(int now, int level) {
        Level nowLevel;
        if (levels.size() <= level) {
            nowLevel = new Level(level);
            levels.add(nowLevel);
        } else {
            nowLevel = levels.get(level);
        }

        if (child[now][0] != -1) { // 왼쪽 자식 먼저 순회
            inorder(child[now][0], level + 1);
        }

        // 자신을 찍음
        if (nowLevel.left == 0) { // left == 0이면 처음 방문한 레벨이므로
            nowLevel.left = ++col;
        } else { // 처음 방문한 레벨이 아니면
            // right 열 값을 현재 col로 초기화 -> 인오더로 방문했을 때 col값은 이전 방문한 right보다 항상 크기 때문
            nowLevel.right = ++col;
        }

        if(child[now][1] != -1) { // 오른쪽 자식으로 순회
            inorder(child[now][1], level + 1);
        }
    }
}
