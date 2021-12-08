/**
 * LEVEL-2
 * 연습문제 > 숫자의 표현
 */
public class Solution {

    public int solution(int n) {
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (recur(i, 0, n))
                answer++;
        }

        return answer;
    }

    private boolean recur(int number, int hap, int target) {
        if (hap == target) return true;
        if (hap > target) return false;

        return recur(number + 1, hap + number, target);
    }
}