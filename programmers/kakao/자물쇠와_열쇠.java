/**
 * LEVEL-3
 * 2020 KAKAO BLIND RECRUITMENT > 자물쇠와 열쇠
 */
public class Solution {

    private int originLockSize;
    private int newLockSize;
    private int keySize;
    private int pendingSize;

    public boolean solution(int[][] key, int[][] lock) {
        originLockSize = lock.length;
        keySize = key.length;

        lock = addPending(lock);

        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < pendingSize + originLockSize; i++) {
                for (int j = 0; j < pendingSize + originLockSize; j++) {
                    if(isFit(key, lock, i, j))
                        return true;
                }
            }

            key = rotate(key);
        }

        return false;
    }

    private int[][] addPending(int[][] lock) {
        pendingSize = keySize - 1;
        newLockSize = originLockSize + (pendingSize * 2);
        int[][] temp = new int[newLockSize][newLockSize];

        for (int y = 0; y < originLockSize; y++) {
            System.arraycopy(lock[y], 0, temp[y + pendingSize], pendingSize, originLockSize);
        }

        return temp;
    }

    private boolean isFit(int[][] key, int[][] lock, int y, int x) {
        int[][] temp = new int[newLockSize][newLockSize];
        for (int i = 0; i < newLockSize; i++) {
            for (int j = 0; j < newLockSize; j++) {
                temp[i][j] = lock[i][j];
            }
        }

        for (int i = y; i < y + keySize; i++) {
            for (int j = x; j < x + keySize; j++) {
                temp[i][j] += key[i - y][j - x];
            }
        }

        for (int i = pendingSize; i < pendingSize + originLockSize; i++) {
            for (int j = pendingSize; j < pendingSize + originLockSize; j++) {
                if (temp[i][j] != 1)
                    return false;
            }
        }

        return true;
    }

    private int[][] rotate(int[][] key) {
        int[][] temp = new int[keySize][keySize];
        for (int i = 0; i < keySize; i++) {
            for (int j = 0; j < keySize; j++) {
                temp[i][j] = key[keySize - j - 1][i];
            }
        }

        return temp;
    }
}