import java.util.Stack;

/**
 * LEVEL-2
 * 2020 KAKAO BLIND RECRUITMENT > 괄호 변환
 */
public class Solution {

    private static final char OPEN_BRACKET = '(';
    private static final char CLOSE_BRACKET = ')';

    private class UV {
        String u;
        String v;

        public UV() {
            u = "";
            v = "";
        }

        public UV(String u, String v) {
            this.u = u;
            this.v = v;
        }
    }

    public String solution(String p) {
        if (p.equals("")) return p;

        UV uv = divide(p);

        if (isCorrect(uv.u))
            return uv.u + solution(uv.v);
        else {
            StringBuilder sb = new StringBuilder();
            sb.append(OPEN_BRACKET);
            sb.append(solution(uv.v));
            sb.append(CLOSE_BRACKET);
            sb.append(reverse(uv.u));
            return sb.toString();
        }
    }

    private UV divide(String str) {
        int openCtn = 0;
        int closeCnt = 0;

        UV uv = new UV();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == OPEN_BRACKET)
                openCtn++;
            else
                closeCnt++;

            if (openCtn == closeCnt) {
                uv.u = str.substring(0, i + 1);
                uv.v = str.substring(i + 1);
                break;
            }
        }

        return uv;
    }

    private boolean isCorrect(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == OPEN_BRACKET)
                stack.push(OPEN_BRACKET);
            else {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }

        return true;
    }

    private String reverse(String str) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < str.length() - 1; i++) {
            if (str.charAt(i) == OPEN_BRACKET) sb.append(CLOSE_BRACKET);
            else sb.append(OPEN_BRACKET);
        }

        return sb.toString();
    }
}
