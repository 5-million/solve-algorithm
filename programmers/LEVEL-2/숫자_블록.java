import java.util.Arrays;

class Solution {
    
    public int[] solution(long begin, long end) {
		int len = (int)(end - begin + 1);
		int[] answer = new int[len];
		Arrays.fill(answer, 1);

		for (long i = begin; i <= end; i++) {
			int idx = (int)(i - begin);

			int sqrt = (int)Math.sqrt(i);
			int val = 1;
			for (int j = 2; j <= sqrt; j++) {
				if (i % j == 0) {
					val = j;
					int temp = (int)(i / j);

					if (temp <= 10000000) {
						val = temp;
						break;
					}
				}
			}
			answer[idx] = val;
		}

		if (begin == 1) {
			answer[0] = 0;
		}

		return answer;
	}
}