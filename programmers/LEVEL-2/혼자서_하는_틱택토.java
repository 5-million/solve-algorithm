class Solution {
    
    public int solution(String[] board) {
		int blackCnt = 0, whiteCnt = 0;
		boolean[] win = new boolean[2];
		for (int i = 0; i < 3; i++) {
			if (i == 0) { // 대각선 검사
				if (board[0].charAt(0) != '.' && board[0].charAt(0) == board[1].charAt(1)
					&& board[1].charAt(1) == board[2].charAt(2)) {
					if (board[0].charAt(0) == 'O') {
						win[0] = true;
					} else win[1] = true;
				}

				if (board[2].charAt(0) != '.' && board[2].charAt(0) == board[1].charAt(1)
					&& board[1].charAt(1) == board[0].charAt(2)) {
					if (board[2].charAt(0) == 'O') {
						win[0] = true;
					} else win[1] = true;
				}
			}

			if (board[0].charAt(i) != '.' && isColWin(i, board)) {
				if (board[0].charAt(i) == 'O') {
					win[0] = true;
				} else win[1] = true;
			}

			if (board[i].charAt(0) != '.' && isRowWin(i, board)) {
				if (board[i].charAt(0) == 'O') {
					win[0] = true;
				} else win[1] = true;
			}

			for (int j = 0; j < 3; j++) {
				if (board[i].charAt(j) == 'O') {
					blackCnt++;
				} else if (board[i].charAt(j) == 'X') {
					whiteCnt++;
				}
			}
		}

		if (win[0] && win[1]) { // 둘다 이긴경우
			return 0;
		} else if (win[0]) { // 선공이 이긴 경우
			return blackCnt == whiteCnt + 1 ? 1 : 0;
		} else if (win[1]) { // 후공이 이긴 경우
			return blackCnt == whiteCnt ? 1 : 0;
		}

		// 비긴 경우
		if (blackCnt - whiteCnt == 0 || blackCnt - whiteCnt == 1) {
			return 1;
		} else return 0;
	}

	private boolean isRowWin(int rowNum, String[] board) {
		String row = board[rowNum];
		return row.charAt(0) == row.charAt(1) && row.charAt(1) == row.charAt(2);
	}

	private boolean isColWin(int col, String[] board) {
		return board[0].charAt(col) == board[1].charAt(col) && board[1].charAt(col) == board[2].charAt(col);
	}
}