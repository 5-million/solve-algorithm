import java.util.*;

class Solution {
    
    private static final int DIAMOND = 0;
	private static final int IRON = 1;
	private static final int STONE = 2;

	private int mineralCnt;
	private int[] minerals;

	public int solution(int[] picks, String[] minerals) {
		mineralCnt = minerals.length;
		this.minerals = new int[mineralCnt];

		for (int i = 0; i < mineralCnt; i++) {
			if ("diamond".equals(minerals[i])) {
				this.minerals[i] = DIAMOND;
			} else if ("iron".equals(minerals[i])) {
				this.minerals[i] = IRON;
			} else {
				this.minerals[i] = STONE;
			}
		}

		return dfs(picks, 0, 0);
	}

	private int dfs(int[] picks, int fatigue, int idx) {
		if (check(picks) || idx == mineralCnt) { // 곡괭이를 모두 사용했거나 모든 광물을 캤을 떄
			return fatigue;
		}

		int ret = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			if (picks[i] > 0) {
				int temp = 0;
				int end = Math.min(idx + 5, mineralCnt);
				for (int j = idx; j < end; j++) { // i번째 곡괭이를 사용해서 5개 또는 끝까지 광물 캠
					temp += getFatigue(i, minerals[j]);
				}

				picks[i]--;
				ret = Math.min(ret, dfs(picks, fatigue + temp, end));
				picks[i]++;
			}
		}

		return ret;
	}

	// 모든 곡괭이를 사용했는지
	private boolean check(int[] picks) {
		for (int pick : picks) {
			if (pick > 0) {
				return false;
			}
		}
		return true;
	}

	// 곡괭이와 광물에 따른 피로도
	private int getFatigue(int pick, int mineral) {
		return (int)Math.pow(5, pick < mineral ? 0 : pick - mineral);
	}
}