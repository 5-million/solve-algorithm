import java.util.*;

class Solution {
    
    public int solution(int[] cards) {
		int n = cards.length;
		boolean[] visit = new boolean[n];
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		for (int i = 0; i < n; i++) {
			if (visit[i]) {
				continue;
			}
			
			int box = i;
			int score = 0;
			while (!visit[box]) {
				visit[box] = true;
				box = cards[box] - 1;
				score++;
			}

			pq.add(score);
		}

		return pq.peek() == n ? 0 : pq.poll() * pq.poll();
	}
}