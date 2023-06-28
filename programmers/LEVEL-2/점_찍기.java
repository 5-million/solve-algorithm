class Solution {
    
    public long solution(int k, int d) {
		long a = 0;
		long answer = 0;
		while (a * k <= d) {
			int y = (int)Math.sqrt(((long)d * d) - ((a * k) * (a * k)));
			answer += y / k + 1;
			a++;
		}
		
		return answer;
	}
}