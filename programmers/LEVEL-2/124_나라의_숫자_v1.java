/**
 * LEVEL-2
 * 연습문제 > 124 나라의 숫자
 */
public class Solution {
    public String solution(int n) {
        String answer = "";

        while (n > 0) {
            Integer rem = n % 3;
            n /= 3;

            if (rem == 0) {
                rem = 4;
                n -= 1;
            }

            answer = rem.toString() + answer;
        }

        return answer;
    }
}
