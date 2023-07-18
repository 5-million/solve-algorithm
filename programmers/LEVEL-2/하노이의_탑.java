import java.util.*;

class Solution {
    
    private List<int[]> seq = new ArrayList<>();

	public int[][] solution(int n) {
		hanoi(n, 1, 2, 3);
		int[][] answer = new int[seq.size()][2];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = seq.get(i);
		}
		return answer;
	}

	private void hanoi(int n, int from, int via, int to) {
		if (n == 1) {
			seq.add(new int[] {from, to});
		} else {
			hanoi(n - 1, from, to, via);
			seq.add(new int[] {from, to});
			hanoi(n - 1, via, from, to);
		}
	}
}