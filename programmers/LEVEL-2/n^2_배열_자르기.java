import java.util.ArrayList;
import java.util.List;

/**
 * LEVEL-2
 * 월간 코드 챌린지 시즌 3 > n^2 배열 자르기
 */
public class Solution {

    public int[] solution(int n, long left, long right) {
        List<Integer> answer = new ArrayList<>();

        for (long i = left; i <= right; i++)
            answer.add((int) (Math.max(i / n, i % n) + 1));

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
