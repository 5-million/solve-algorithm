import java.util.Scanner;

/**
 * Beginner_Coder
 * 도형만들기2: 1707 - 달팽이사각형
 * http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=980&sca=2020
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] square = new int[n][n];
        square[0][0] = 1;

        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int d = 0;
        int y = 0, x = 0;
        int inputNumber = 2;
        top: while (true) {
            while (true) {
                int ny = y + dir[d][0];
                int nx = x + dir[d][1];

                if (ny >= 0 && ny < n && nx >= 0 && nx < n) {
                    if (square[ny][nx] != 0) break;

                    y = ny;
                    x = nx;
                    square[y][x] = inputNumber++;
                } else break;
            }
            if (inputNumber == (n * n) + 1) break;
            d = (d + 1) % 4;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf(square[i][j] + " ");
            }
            System.out.println();
        }
    }
}