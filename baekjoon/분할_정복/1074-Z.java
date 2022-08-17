import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * S1
 * 1074 - Z
 * https://www.acmicpc.net/problem/1074
 */
public class Main {
	
	private static int answer, count;
	private static int n, r, c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		rec(0, 0, n);
		
		System.out.println(answer);
	}
	
	private static void rec(int y, int x, int size) {
		if (answer != 0) return;
		if (!(r < y + size && c < x + size)) {
			count += size * size;
			return;
		}

		if (size == 1) {
			if (y == r && x == c) {
				answer = count;
			}
			
			count++;
			return;
		}
		
		int half = size / 2;
		rec(y, x, half);
		rec(y, x + half, half);
		rec(y + half, x, half);
		rec(y + half, x + half, half);
	}
}
