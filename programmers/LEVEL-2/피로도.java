/**
 * LEVEL-2
 * 위클리 챌린지 > 피로도
 */
public class Solution {

    public int solution(int k, int[][] dungeons) {
        return recur(dungeons, k, 0, new boolean[8]);
    }

    private int recur(int[][] dungeons, int k, int clear, boolean[] visit) {
        int ret = clear;
        for (int i = 0; i < dungeons.length; i++) {
            if (!visit[i] && k >= dungeons[i][0]) {
                visit[i] = true;
                ret = Math.max(ret, recur(dungeons, k - dungeons[i][1], clear + 1, visit));
                visit[i] = false;
            }
        }

        return ret;
    }
}
