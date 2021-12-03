import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LEVEL-2
 * 2018 KAKAO BLIND RECRUITMENT > [3차]압축
 */
public class Solution {

    public int[] solution(String msg) {
        List<Integer> answer = new ArrayList<>();

        List<String> dict =
                Arrays.stream("ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("")).collect(Collectors.toList());

        for (int i = 0; i < msg.length();) {
            String w = msg.substring(i, i + 1);
            for (int j = i + 1; j <= msg.length(); j++) {
                String subMsg = msg.substring(i, j);
                if (!dict.contains(subMsg)) {
                    dict.add(subMsg);
                    break;
                }

                w = subMsg;
            }
            i += w.length();
            answer.add(dict.indexOf(w) + 1);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
