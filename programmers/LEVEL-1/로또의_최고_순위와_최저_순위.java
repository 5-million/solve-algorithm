import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * LEVEL-1
 * 2021 Dev-Matching: 웹 백엔드 개발자(상반기) - 로또의 최고 순위와 최저 순위
 * https://programmers.co.kr/learn/courses/30/lessons/77484
 */
public class Solution {

    public int[] solution(int[] lottos, int[] win_nums) {
        Set<Integer> set = Arrays.stream(win_nums).boxed().collect(Collectors.toSet());

        int unknown = 0, match = 0;
        for (int lotto : lottos) {
            if (lotto == 0) {
                unknown++;
            } else if (set.contains(lotto)) {
                match++;
            }
        }

        return new int[]{decideRank(unknown + match), decideRank(match)};
    }

    public int decideRank(int matchCount) {
        return matchCount < 2 ? 6 : 7 - matchCount;
    }
}
