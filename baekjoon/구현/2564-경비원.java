import java.util.Scanner;

/**
 * S1
 * 2564 - 경비원
 * https://www.acmicpc.net/problem/2564
 */
public class Main {

    private static class Position {

        int d;
        int y;
        int x;

        public Position(int d, int y, int x) {
            this.d = d;
            this.y = y;
            this.x = x;
        }
    }

    private static int c;
    private static int r;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        c = sc.nextInt();
        r = sc.nextInt();

        int n = sc.nextInt();
        Position[] store = new Position[n];
        for (int i = 0; i < n; i++) {
            int d = sc.nextInt();
            store[i] = getPosition(d, sc);
        }

        Position dong = getPosition(sc.nextInt(), sc);

        int answer = 0;
        for (Position s : store) {
            answer += getShortestDist(dong, s);
        }
        System.out.println(answer);
    }

    private static Position getPosition(int d, Scanner sc) {
        switch (d) {
            case 1: // 북
                return new Position(d, 0, sc.nextInt());

            case 2: // 남
                return new Position(d, r, sc.nextInt());

            case 3: // 서
                return new Position(d, sc.nextInt(), 0);

            default: // 동
                return new Position(d, sc.nextInt(), c);
        }
    }

    private static int getShortestDist(Position dong, Position store) {
        int temp;
        if (dong.d == 1) temp = 2;
        else if (dong.d == 2) temp = 1;
        else if (dong.d == 3) temp = 4;
        else temp = 3;

        if (temp == store.d) {
            if (store.d == 1 || store.d == 2) {
                return r + Math.min(dong.x + store.x, c - dong.x + c - store.x);
            } else {
                return c + Math.min(dong.y + store.y, r - dong.y + r - store.y);
            }
        } else {
            return Math.abs(dong.y - store.y) + Math.abs(dong.x - store.x);
        }
    }
}