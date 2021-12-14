/**
 * LEVEL-3
 * 2019 카카오 개발자 겨울 인턴십 > 징검다리 건너기
 */
public class Solution {

    public int solution(int[] stones, int k) {
        int answer = 0;
        int left = 1;
        int right = 200000000;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canCross(stones, k, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return answer;
    }

    private boolean canCross(int[] stones, int k, int friends) {
        int cnt = 0;
        for (int stone : stones) {
            if (stone < friends)
                cnt++;
            else cnt = 0;

            if (cnt == k)
                return false;
        }

        return true;
    }
}
