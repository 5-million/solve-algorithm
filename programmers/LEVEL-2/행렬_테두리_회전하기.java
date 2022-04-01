/**
 * LEVEL-2
 * 2021 Dev-Matching: 웹 백엔드 개발자(상반기) - 행렬 테두리 회전하기
 * https://programmers.co.kr/learn/courses/30/lessons/77485
 */
public class Solution {

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] matrix = new int[rows + 1][columns + 1];
        int elem = 1;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                matrix[i][j] = elem++;
            }
        }

        for (int i = 0; i < queries.length; i++) {
            answer[i] = rotate(matrix, queries[i]);
        }

        return answer;
    }

    public int rotate(int[][] matrix, int[] query) {
        int x1 = query[0], y1 = query[1];
        int x2 = query[2], y2 = query[3];

        int temp = matrix[x1][y1];
        int min = temp;
        for (int i = x1; i < x2; i++) {
            matrix[i][y1] = matrix[i + 1][y1];
            min = Math.min(min, matrix[i][y1]);
        }

        for (int i = y1; i < y2; i++) {
            matrix[x2][i] = matrix[x2][i + 1];
            min = Math.min(min, matrix[x2][i]);
        }

        for (int i = x2; i > x1; i--) {
            matrix[i][y2] = matrix[i - 1][y2];
            min = Math.min(min, matrix[i][y2]);
        }

        for (int i = y2; i > y1; i--) {
            matrix[x1][i] = matrix[x1][i - 1];
            min = Math.min(min, matrix[x1][i]);
        }

        matrix[x1][y1 + 1] = temp;

        return min;
    }
}
