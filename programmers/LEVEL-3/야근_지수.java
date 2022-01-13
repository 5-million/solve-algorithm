import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * LEVEL-3
 * 연습문제 > 야근 지수
 */
public class Solution {

    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.addAll(Arrays.stream(works).boxed().collect(Collectors.toList()));

        while (n > 0 && !pq.isEmpty()) {
            int work = pq.poll() - 1;
            if (work > 0)
                pq.add(work);
            n--;
        }

        long answer = 0;
        while (!pq.isEmpty()) {
            int work = pq.poll();
            answer += (long) work * work;
        }

        return answer;
    }
}
