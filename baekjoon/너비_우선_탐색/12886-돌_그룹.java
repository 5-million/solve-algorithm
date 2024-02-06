import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int total = a + b + c;
		int avg = total / 3;

		if (total % 3 != 0) {
			System.out.println(0);
			return;
		}

		boolean[][] visit = new boolean[1501][1501];
		solution(total, a, b, visit);

		System.out.println(visit[avg][avg] ? 1 : 0);
	}

	private static void solution(int total, int a, int b, boolean[][] visit) {
		if (visit[a][b]) {
			return;
		}

		visit[a][b] = true;
		visit[b][a] = true;

		int c = total - (a + b);
		if (a == b && b == c) {
			return;
		}


		if (a != b) {
			int x = Math.min(a, b);
			int y = Math.max(a, b);
			solution(total, x + x, y - x, visit);
		}

		if (a != c) {
			int x = Math.min(a, c);
			int y = Math.max(a, c);
			solution(total, x + x, y - x, visit);
		}

		if (b != c) {
			int x = Math.min(b, c);
			int y = Math.max(b, c);
			solution(total, x + x, y - x, visit);
		}
	}
}
