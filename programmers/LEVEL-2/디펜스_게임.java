import java.util.*;

class Solution {
    
    public int solution(int n, int k, int[] enemy) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

		int round = 0;
		for (int e : enemy) {
			n -= e;
			pq.add(e);

			if (n < 0) {
				if (k > 0 && !pq.isEmpty()) {
					n += pq.poll();
					k--;
				} else {
					break;
				}
			}
			
			round++;
		}
		
		return round;
	}
}