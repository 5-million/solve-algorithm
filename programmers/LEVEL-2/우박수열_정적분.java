import java.util.*;

class Solution {
    
    public double[] solution(int k, int[][] ranges) {
		List<Integer> seq = new ArrayList<>();
		while (k != 1) {
			seq.add(k);

			if (k % 2 == 0) {
				k /= 2;
			} else k = (k * 3) + 1;
		}
		seq.add(1);

		double[] sum = new double[seq.size()];
		for (int i = 1; i < seq.size(); i++) {
			sum[i] = sum[i - 1]
				+ Math.min(seq.get(i), seq.get(i - 1))
				+ Math.abs(seq.get(i) - seq.get(i - 1)) * 0.5;
		}

		double[] answer = new double[ranges.length];
		int idx = 0;
		for (int[] range : ranges) {
			int begin = range[0];
			int end = seq.size() - 1 + range[1];

			double val = -1.0;
			if (begin <= end) {
				val = sum[end] - sum[begin];
			}

			answer[idx++] = val;
		}

		return answer;
	}
}