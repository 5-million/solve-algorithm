/**
 * LEVEL-2
 * 2021 Dev_Matching: 웹 백엔드 > 행렬 테두리 회전하기
 */
public class Solution {
    private int[][] board;

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        init(rows, columns);

        for (int i = 0; i < queries.length; i++) {
            answer[i] = rotate(queries[i]);
        }

        return answer;
    }

    public void init(int rows, int columns) {
        board = new int[rows][columns];

        int number = 1;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                board[i][j] = number++;
    }

    public int rotate(int[] query) {
        int y1 = query[0] - 1;
        int x1 = query[1] - 1;
        int y2 = query[2] - 1;
        int x2 = query[3] - 1;

        int temp = board[y1][x1];
        int minValue = Integer.MAX_VALUE;

        // left
        for (int y = y1; y < y2; y++) {
            board[y][x1] = board[y+1][x1];
            minValue = Math.min(minValue, board[y][x1]);
        }

        // down
        for (int x = x1; x < x2; x++) {
            board[y2][x] = board[y2][x+1];
            minValue = Math.min(minValue, board[y2][x]);
        }

        // right
        for (int y = y2; y > y1; y--) {
            board[y][x2] = board[y-1][x2];
            minValue = Math.min(minValue, board[y][x2]);
        }

        // up
        for (int x = x2; x > x1; x--) {
            board[y1][x] = board[y1][x-1];
            minValue = Math.min(minValue, board[y1][x]);
        }

        board[y1][x1+1] = temp;
        minValue = Math.min(minValue, temp);

        return minValue;
    }
}