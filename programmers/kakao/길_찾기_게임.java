import java.util.*;

/**
 * LEVEL-3
 * 2019 KAKAO BLIND RECRUITMENT > 길 찾기 게임
 */
public class Solution {

    private class Node {
        int id;
        int y;
        int x;
        Node left;
        Node right;

        public Node(int id, int y, int x) {
            this.id = id;
            this.y = y;
            this.x = x;
            this.left = null;
            this.right = null;
        }

        public void setLeft(int level, int min, int max) {
            if (nodesByLevel.get(level) == null || nodesByLevel.get(level).isEmpty()) return;

            for (Node node : nodesByLevel.get(level)) {
                if (min < node.x && node.x < this.x) {
                    this.left = node;
                    node.setLeft(level + 1, min, node.x);
                    node.setRight(level + 1, node.x, max);
                    break;
                }
            }
        }

        public void setRight(int level, int min, int max) {
            if (nodesByLevel.get(level) == null || nodesByLevel.get(level).isEmpty()) return;

            for (Node node : nodesByLevel.get(level)) {
                if (this.x < node.x && node.x < max) {
                    this.right = node;
                    node.setLeft(level + 1, min, node.x);
                    node.setRight(level + 1, node.x, max);
                    break;
                }
            }
        }
    }

    Map<Integer, List<Node>> nodesByLevel = new HashMap<>();

    public int[][] solution(int[][] nodeinfo) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.y - o1.y);
        for (int i = 0; i < nodeinfo.length; i++)
            pq.add(new Node(i + 1, nodeinfo[i][1], nodeinfo[i][0]));

        Node root = pq.poll();

        int level = 0;
        int prevY = root.y;
        while (!pq.isEmpty()) {
            if (pq.peek().y == prevY) {
                nodesByLevel.get(level).add(pq.poll());
            } else {
                level++;
                nodesByLevel.put(level, new ArrayList<>());
                prevY = pq.peek().y;
            }
        }

        root.setLeft(1, -1, root.x);
        root.setRight(1, root.x, Integer.MAX_VALUE);

        List<Integer> preorderVisit = new ArrayList<>();
        List<Integer> postorderVisit = new ArrayList<>();
        preorder(root, preorderVisit);
        postorder(root, postorderVisit);
        return new int[][]{
                preorderVisit.stream().mapToInt(Integer::intValue).toArray(),
                postorderVisit.stream().mapToInt(Integer::intValue).toArray()
        };
    }

    private void preorder(Node node, List<Integer> preorderVisit) {
        preorderVisit.add(node.id);
        if (node.left != null) preorder(node.left, preorderVisit);
        if (node.right != null) preorder(node.right, preorderVisit);
    }

    private void postorder(Node node, List<Integer> postorderVisit) {
        if (node.left != null) postorder(node.left, postorderVisit);
        if (node.right != null) postorder(node.right, postorderVisit);
        postorderVisit.add(node.id);
    }
}