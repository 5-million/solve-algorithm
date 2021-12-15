import java.util.*;

/**
 * LEVEL-3
 * 2020 카카오 인턴십 > 보석 쇼핑
 */
public class Solution {

    public int[] solution(String[] gems) {
        int left = 0;
        int right = 0;
        int allGems = new HashSet<>(Arrays.asList(gems)).size();

        int[] answer = new int[2];
        answer[0] = 1;
        answer[1] = gems.length;

        Map<String, Integer> map = new HashMap<>();
        while (true) {
            if (allGems == map.size()) {
                if (answer[1] - answer[0] > right - left - 1) {
                    answer[0] = left + 1;
                    answer[1] = right;
                }

                map.replace(gems[left], map.get(gems[left]) - 1);
                if (map.get(gems[left]) == 0)
                    map.remove(gems[left]);
                left++;
            } else if (right == gems.length) {
                break;
            } else {
                map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
                right++;
            }
        }

        return answer;
    }
}