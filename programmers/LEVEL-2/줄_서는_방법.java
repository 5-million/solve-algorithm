import java.util.Stack;

class Solution {
    
    private boolean[] used;
	private long[] factorial;

	public int[] solution(int n, long k) {
		used = new boolean[n + 1];
		factorial = new long[n + 1];
		factorial[1] = 1;

		for (int i = 2; i < n; i++) {
			factorial[i] = factorial[i - 1] * i;
		}
		
        k--;
		int[] answer = new int[n];
		for (int i = 0; i < n - 1; i++) {
			answer[i] = (int)findKthNumber(k / factorial[n - 1 - i]);
			k %= factorial[n - 1 - i];
		}
        answer[n - 1] = (int)findKthNumber(k);
		
		return answer;
	}

	private long findKthNumber(long k) {
		int cnt = 0;
		for (int i = 1; i < used.length; i++) {
			if (!used[i]) {
				if (cnt == k) {
					used[i] = true;
					return i;
				}
				
				cnt++;
			}
		}
		return -1;
	}
}