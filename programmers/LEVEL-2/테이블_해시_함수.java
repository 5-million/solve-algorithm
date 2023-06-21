import java.util.*;

class Solution {
    
	public int solution(int[][] data, int col, int row_begin, int row_end) {
		Arrays.sort(data, (a, b) -> a[col - 1] != b[col - 1] ? a[col - 1] - b[col - 1] : b[0] - a[0]);

		int hash = getS(data[row_begin - 1], row_begin);
        
		for (int row = row_begin ; row < row_end; row++) {
			hash = hash ^ getS(data[row], row + 1);
		}

		return hash;
	}

	private int getS(int[] data, int mod) {
		int S = 0;
		for (int d : data) {
			S += (d % mod);
		}

		return S;
	}
}