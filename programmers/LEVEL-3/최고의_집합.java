class Solution {
    
    public int[] solution(int n, int s) {
		if (s / n < 1) {
			return new int[] {-1};
		}

		int[] answer = new int[n];
		int idx = 0;
		while (s != 0) {
			answer[idx] = s / n--;
			s -= answer[idx++];
		}
		
		return answer;
	}
}