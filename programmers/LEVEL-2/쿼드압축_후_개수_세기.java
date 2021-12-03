/**
 * LEVEL-2
 * 쿼드압축 후 개수 세기
 */
class Solution {
    int[][] arr;
    int[] count = new int[2];

    public int[] solution(int[][] arr) {
        this.arr = arr;
        int size = arr.length;

        quad(0, 0, size);

        return count;
    }

    private void quad(int y, int x, int size) {
        if (size == 1) {
            count[arr[y][x]]++;
            return;
        }

        if (isSame(y, x, size)) {
            count[arr[y][x]]++;
        } else {
            int halfSize = size / 2;
            quad(y, x, halfSize); // left up
            quad(y, x + halfSize, halfSize); // right up
            quad(y + halfSize, x, halfSize);// left down
            quad(y + halfSize, x + halfSize, halfSize);// right down
        }
    }

    private boolean isSame(int y, int x, int size) {
        int num = arr[y][x];
        for (int i = y; i < y + size; i++)
            for (int j = x; j < x + size; j++)
                if (arr[i][j] != num)
                    return false;

        return true;
    }
}