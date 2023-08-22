import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 사람 수
		int m = Integer.parseInt(st.nextToken()); // 파티 수
		boolean[][] parties = new boolean[m + 1][n + 1];

		st = new StringTokenizer(br.readLine());
		int knowsCnt = Integer.parseInt(st.nextToken());
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] knows = new boolean[n + 1];
		for (int i = 0; i < knowsCnt; i++) {
			int k = Integer.parseInt(st.nextToken());
			knows[k] = true;
			q.add(k);
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int participants = Integer.parseInt(st.nextToken());
			for (int j = 0; j < participants; j++) {
				parties[i][Integer.parseInt(st.nextToken())] = true;
			}
		}

		boolean[] visit = new boolean[m];
		int visitCnt = 0;
		while (!q.isEmpty()) {
			int know = q.poll();

			for (int party = 0; party < m; party++) {
				if (visit[party] || !parties[party][know]) {
					continue;
				}

				visitCnt++;
				visit[party] = true;
				for (int i = 1; i <= n; i++) {
					if (parties[party][i] && !knows[i]) {
						q.add(i);
						knows[i] = true;
					}
				}
			}
		}

		System.out.println(m - visitCnt);
	}
}
