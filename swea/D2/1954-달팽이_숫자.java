import java.util.Scanner;

/**
 * D2
 * 1954 - 달팽이 숫자
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PobmqAPoDFAUq
 */
public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		
		int[][] dir = {{0,1}, {1, 0}, {0, -1}, {-1, 0}};
		for (int tc=1; tc<=t; tc++) {
			int n = sc.nextInt();
			
			int input = 1;
			int[][] map = new int[n][n];
			int r = 0, c = 0;
			map[r][c] = input++;
			while (input <= n * n) {
				l1: for (int[] d : dir) {
					while(true) {
						int nr = r + d[0];
						int nc = c + d[1];
						
						if (checkRange(n, nr, nc) && map[nr][nc] == 0) {
							r = nr;
							c = nc;
							map[r][c] = input++;
						} else continue l1;
					}
				}
			}
			
			System.out.println("#" + tc);
			for (r=0; r<n; r++) {
				for (c=0; c<n; c++) {
					System.out.print(map[r][c] + " ");
				}
				System.out.println();
			}
		}
	}
	
	private static Boolean checkRange(int n, int r, int c) {
		if (0 <= r && r < n && 0 <= c && c < n)
			return true;
		return false;
	}
}
