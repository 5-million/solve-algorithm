import java.util.*;

class Solution {
    
    public long solution(int[] weights) {
		int[] weightsCnt = new int[2001];
		for (int weight : weights) {
			weightsCnt[weight]++;
		}
        
        Arrays.sort(weights);

		long answer = 0;
		for (int w : weights) {
			weightsCnt[w]--;
			
			answer += weightsCnt[w];
			answer += weightsCnt[w * 2];

			if (w % 2 == 0) {
				answer += weightsCnt[w * 3 / 2];
			}

			if (w % 3 == 0) {
				answer += weightsCnt[w * 4 / 3];
			}
		}

		return answer;
	}
}