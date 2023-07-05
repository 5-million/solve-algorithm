import java.util.*;

class Solution {
    
    private int[] apeach, answer;
	private int maxResult;

	public int[] solution(int n, int[] info) {
		this.apeach = info;
		dfs(0, n, new int[11]);
		return answer == null ? new int[] {-1} : answer;
	}

	private void dfs(int idx, int n, int[] lion) {
		if (idx == 10) {
			lion[idx] += n;
			n = 0;
		}

		if (n == 0) {
			int result = apeachVs(lion);
			if (result > 0) {
				if (result == maxResult) {
					for (int i = 10; i >= 0; i--) {
						if (lion[i] > answer[i]) {
							answer = Arrays.copyOf(lion, 11);
							break;
						} else if (lion[i] < answer[i]) {
							break;
						}
					}
				} else if (result > maxResult) {
					maxResult = result;
					answer = Arrays.copyOf(lion, 11);
				}
			}
            
			lion[idx] = 0;
		} else {
			if (apeach[idx] < n) {
				lion[idx] = apeach[idx] + 1;
				dfs(idx + 1, n - lion[idx], lion);
				lion[idx] = 0;
			}

			dfs(idx + 1, n, lion);
		}
	}

	// 라이언이 이기면 양수, 어피치가 이기면 음수
	// 라이언이 이겼을 때 반환값이 클 수록 높은 점수차로 이김
	private int apeachVs(int[] lion) {
		int apeachScore = 0;
		int lionScore = 0;

		for (int i = 0; i < 11; i++) {
			int score = 10 - i;
			if (apeach[i] < lion[i]) {
				lionScore += score;
			} else if (apeach[i] != 0){
				apeachScore += score;
			}
		}
		
		return lionScore - apeachScore;
	}
}