import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int t : tangerine) {
			map.put(t, map.getOrDefault(t, 0) + 1);
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		pq.addAll(map.values());

		int answer = 0;
		while (k > 0) {
			k -= pq.poll();
            answer++;
		}
		
		return answer;
	}
}