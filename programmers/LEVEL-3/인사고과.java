import java.util.*;

class Solution {
    
    public int solution(int[][] scores) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
			if (a[0] != b[0]) {
				return b[0] - a[0];
			} else return a[1] - b[1];
		});

		for (int[] score : scores) {
			pq.add(score);
		}

		int wanho = scores[0][0] + scores[0][1];
		int rank = 1;
		int max = 0;
		while (!pq.isEmpty()) {
			int[] score = pq.poll();
			if (score[1] >= max) {
				max = score[1];

				int sum = score[0] + score[1];
				if (sum > wanho) {
					rank++;
				}
			} else {
				if (score == scores[0]) {
					return -1;
				}
			}
		}
		
		return rank;
	}
}