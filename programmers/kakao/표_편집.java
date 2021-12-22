import java.util.Stack;

/**
 * LEVEL-3
 * 2021 카카오 채용연계형 인턴십 > 표 편집
 */
public class Solution {

    private class Node {
        boolean isExist;
        Node prev;
        Node next;

        public Node() {
            isExist = true;
            prev = null;
            next = null;
        }
    }

    Stack<Node> stack = new Stack<>();

    public String solution(int n, int k, String[] cmd) {
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++)
            nodes[i] = new Node();

        nodes[0].next = nodes[1];
        for (int i = 1; i < n - 1; i++) {
            nodes[i].prev = nodes[i - 1];
            nodes[i].next = nodes[i + 1];
        }
        nodes[n - 1].prev = nodes[n - 2];

        Node cursor = nodes[k];
        for (String c : cmd) {
            String[] command = c.split(" ");
            switch (command[0]) {
                case "U":
                    cursor = cmdU(cursor, Integer.parseInt(command[1]));
                    break;
                case "D":
                    cursor = cmdD(cursor, Integer.parseInt(command[1]));
                    break;
                case "C":
                    cursor = cmdC(cursor);
                    break;
                default:
                    cmdZ();
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Node node : nodes) {
            if (node.isExist) sb.append("O");
            else sb.append("X");
        }
        return sb.toString();
    }

    private Node cmdU(Node cursor, int n) {
        while (n-- > 0)
            cursor = cursor.prev;
        return cursor;
    }

    private Node cmdD(Node cursor, int n) {
        while (n-- > 0)
            cursor = cursor.next;
        return cursor;
    }

    private Node cmdC(Node cursor) {
        cursor.isExist = false;
        stack.add(cursor);
        if (cursor.prev != null) cursor.prev.next = cursor.next;
        if (cursor.next != null)  {
            cursor.next.prev = cursor.prev;
            return cursor.next;
        } else {
            return cursor.prev;
        }
    }

    private void cmdZ() {
        Node restore = stack.pop();
        restore.isExist = true;
        if (restore.prev != null) restore.prev.next = restore;
        if (restore.next != null) restore.next.prev = restore;
    }
}