import java.util.LinkedList;
import java.util.Queue;

/**
 * LEVEL-3
 * 깊이/너비 우선 탐색 > 단어 변환
 */
public class Solution {

    public int solution(String begin, String target, String[] words) {
        int len = words.length;
        boolean[] visit = new boolean[len];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < len; i++)
            if (isConvertible(begin, words[i])) {
                q.add(i);
                visit[i] = true;
            }

        int answer = 1;
        while (!q.isEmpty()) {
            int qSize = q.size();

            for (int i = 0; i < qSize; i++) {
                String now = words[q.poll()];

                if (now.equals(target))
                    return answer;

                for (int next = 0; next < len; next++)
                    if (!visit[next] && isConvertible(now, words[next])) {
                        visit[next] = true;
                        q.add(next);
                    }
            }

            answer++;
        }

        return 0;
    }

    private boolean isConvertible(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        int diff = 0;
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]) diff++;
            if (diff > 1) return false;
        }

        return true;
    }
}