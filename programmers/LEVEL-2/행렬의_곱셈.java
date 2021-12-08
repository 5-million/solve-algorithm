/**
 * LEVEL-2
 * 연습문제 > 행렬의 곱셈
 */
public class Solution {

    public int[][] solution(int[][] arr1, int[][] arr2) {
        int r = arr1.length;
        int c = arr2[0].length;
        int range = arr1[0].length;

        int[][] answer = new int[r][c];

        for (int y = 0; y < r; y++) {
            for (int x = 0; x < c; x++) {
                for (int k = 0; k < range; k++) {
                    answer[y][x] += arr1[y][k] * arr2[k][x];
                }
            }
        }

        return answer;
    }
}