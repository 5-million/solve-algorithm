import java.util.*;

class Solution {
    
    public int solution(int[] queue1, int[] queue2) {
		int n = queue1.length;

		long sum1 = 0, sum2 = 0;
		Queue<Integer> q1 = new ArrayDeque<>(), q2 = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			sum1 += queue1[i];
			q1.add(queue1[i]);

			sum2 += queue2[i];
			q2.add(queue2[i]);
		}

		int answer = 0;
		while (answer < n * 4) {
			if (sum1 == sum2) {
				return answer;
			} else if (sum1 < sum2) {
				int pop = q2.poll();
				sum2 -= pop;
				sum1 += pop;
				q1.add(pop);
			} else {
				int pop = q1.poll();
				sum1 -= pop;
				sum2 += pop;
				q2.add(pop);
			}

			answer++;
		}

		return -1;
	}
}