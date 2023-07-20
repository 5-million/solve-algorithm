class Solution {
    
    public int solution(int[][] board, int[][] skill) {
		int n = board.length;
		int m = board[0].length;

		int[][] temp = new int[n + 1][m + 1];
		for (int[] s : skill) {
			int r1 = s[1];
			int c1 = s[2];
			int r2 = s[3];
			int c2 = s[4];
			int degree = s[0] == 1 ? -s[5] : s[5];

			temp[r1][c1] += degree;
			temp[r2 + 1][c1] += (-1 * degree);
			temp[r1][c2 + 1] += (-1 * degree);
			temp[r2 + 1][c2 + 1] += degree;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				temp[i][j] += temp[i - 1][j];
			}
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				temp[j][i] += temp[j][i - 1];
			}
		}

		int answer = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				board[i][j] += temp[i][j];
				if (board[i][j] >= 1) {
					answer++;
				}
			}
		}

		return answer;
	}
}