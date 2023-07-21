class Solution {
    
    public long solution(int[] sequence) {
		long answer = Long.MIN_VALUE;

		// 0번째: 1, -1, 1, -1, ...
		// 1번째: -1, 1, -1, 1, ...
		int[][] pulseSeq = new int[2][sequence.length];
		long[][] dp = new long[2][sequence.length];
		for (int i = 0; i < sequence.length; i++) {
			if (i % 2 == 0) {
				pulseSeq[0][i] = sequence[i];
				pulseSeq[1][i] = -sequence[i];
			} else {
				pulseSeq[0][i] = -sequence[i];
				pulseSeq[1][i] = sequence[i];
			}

			if (i == 0) {
				dp[0][i] = pulseSeq[0][i];
				dp[1][i] = pulseSeq[1][i];
			} else {
				dp[0][i] = Math.max(0, dp[0][i - 1]) + pulseSeq[0][i];
				dp[1][i] = Math.max(0, dp[1][i - 1]) + pulseSeq[1][i];
			}

			answer = Math.max(answer, dp[0][i]);
			answer = Math.max(answer, dp[1][i]);
		}

		return answer;
	}
}