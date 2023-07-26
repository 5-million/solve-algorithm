class Solution {
    
    public int solution(String name) {
		int answer = 0;

		int len = name.length();
		int move = len - 1;
		for (int i = 0; i < len; i++) {
			answer += getUpDownCount(name.charAt(i));

			if (i < len - 1 && name.charAt(i + 1) == 'A') {
				int endA = i + 1;
				while (endA < len && name.charAt(endA) == 'A') {
					endA++;
				}

				move = Math.min(move, i * 2 + len - endA);
				move = Math.min(move, 2 * (len - endA) + i);
			}
		}
		
		return answer + move;
	}

	private int getUpDownCount(char target) {
		return Math.min(target - 'A', 1 + 'Z' - target);
	}
}