/**
 * LEVEL-3
 * 2019 카카오 개발자 겨울 인턴십 > 징검다리 건너기
 * https://programmers.co.kr/learn/courses/30/lessons/64062
 */
public class Solution {

    public int solution(int[] stones, int k) {
        int answer = 0;
        int start = 1, end = 200000000;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (canCrossTheRiver(stones, k, mid)) {
                start = mid + 1;
                answer = mid;
            } else {
                end = mid - 1;
            }
        }

        return answer;
    }

    private boolean canCrossTheRiver(int[] stones, int k, int crossedCount) {
        int cnt = 0;
        for (int stone : stones) {
            if (stone - crossedCount < 0) cnt++;
            else cnt = 0;

            if (cnt == k) return false;
        }

        return true;
    }
}