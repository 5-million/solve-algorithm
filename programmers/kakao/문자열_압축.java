/**
 * LEVEL-2
 * 2020 KAKAO BLIND RECRUITMENT > 문자열 압축
 */
public class Solution {

    public int solution(String s) {
        int answer = s.length();
        
        int len = s.length();
        for (int unit = 1; unit <= len / 2; unit++) {
            int cnt = 1;
            int temp = 0;
            String pre = s.substring(0, unit);
            
            int i = unit;
            for (; i + unit <= len; i += unit) {
                String now = s.substring(i, i + unit);
                if (now.equals(pre))
                    cnt++;
                else {
                    if (cnt > 1)
                        temp += Integer.toString(cnt).length();
                    temp += unit;
                    pre = now;
                    cnt = 1;
                }
            }

            temp += unit;
            if (cnt > 1) temp += Integer.toString(cnt).length();
            if (i < len) temp += len - i;
            answer = Math.min(answer, temp);
        }

        return answer;
    }
}
