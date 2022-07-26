import java.util.Scanner;

/**
 * BC
 * 1733 - 오목
 * http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&code=1733&sca=2060
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[][] board = new int[20][20];
		for (int i=1; i<=19; i++) {
			for (int j=1; j<=19; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		
		
		for (int i=1; i<=19; i++) {
			for (int j=1; j<=19; j++) {
				if (board[i][j] == 0)
					continue;
				
				if (isOmok(board, i, j)) {
					System.out.println(board[i][j]);
					System.out.printf("%d %d\n", i, j);
					return;
				}
			}
		}
		
		System.out.println(0);
	}
	
	private static boolean isOmok(int[][] board, int y, int x) {
		int stone = board[y][x];
		int[][] dir = {{-1, 1}, {0, 1}, {1, 1}, {1, 0}};
		
		f1: for (int[] d : dir) {
			int r = y;
			int c = x;
			for (int k=1; k<5; k++) {
				r += d[0];
				c += d[1];
				
				if (r < 1 || r > 19 || c < 1 || c > 19 || board[r][c] != stone)
					continue f1;
			}
			
			int pr = y - d[0];
			int pc = x - d[1];
			if (0 < pr && pr < 20 && 0 < pc && pc < 20 && board[pr][pc] == stone)
				continue;
			
			r += d[0];
			c += d[1];
			if (0 < r && r < 20 && 0 < c && c < 20) {
				if (board[r][c] != stone) return true;
			} else return true;
		}
		
		return false;
	}
}
