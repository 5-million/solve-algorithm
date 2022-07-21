import java.util.Scanner;

/**
 * D3
 * 4615 - 재미있는 오셀로 게임
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWQmA4uK8ygDFAXj
 */
public class Solution {

    private static final int BLACK = 1;
    private static final int WHITE = 2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int tc = 1; tc <= t; tc++) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] board = createBoard(n);

            for (int i = 0; i < m; i++) {
                int y = sc.nextInt() - 1;
                int x = sc.nextInt() - 1;
                int stone = sc.nextInt();

                board[y][x] = stone;

                int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
                for (int[] d : dir) {
                    int j = 1;

                    while (true) {
                        int ny = y + d[0] * j;
                        int nx = x + d[1] * j;
                        j += 1;

                        if (ny < 0 || ny >= n || nx < 0 || nx >= n || board[ny][nx] == 0) {
                            break;
                        }

                        if (board[ny][nx] == stone) {
                            for (int k = 1; k < j; k++) {
                                board[y + d[0] * k][x + d[1] * k] = stone;
                            }
                            break;
                        }
                    }
                }
            }

            int blackCnt = 0, whiteCnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == BLACK) blackCnt++;
                    else if (board[i][j] == WHITE) whiteCnt++;
                }
            }

            System.out.printf("#%d %d %d\n", tc, blackCnt, whiteCnt);
        }
    }

    private static int[][] createBoard(int n) {
        int[][] board = new int[n][n];
        board[n / 2 - 1][n / 2 - 1] = 2;
        board[n / 2][n / 2] = 2;
        board[n / 2 - 1][n / 2] = 1;
        board[n / 2][n / 2 - 1] = 1;

        return board;
    }
}
