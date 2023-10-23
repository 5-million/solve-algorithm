import java.io.*;
import java.util.*;

public class Main {

	private static int n;
	private static Set<Integer> answer = new TreeSet<>();
	private static int[] secondLine;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		secondLine = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			secondLine[i] = Integer.parseInt(br.readLine());
		}


		for (int i = 1; i <= n; i++) {
			solution(i, i, new boolean[n + 1], new ArrayList<>());
		}

		System.out.println(answer.size());
		for (Integer num : answer) {
			System.out.println(num);
		}
	}

	private static void solution(int start, int num, boolean[] visit, List<Integer> s) {
		visit[num] = true;
		s.add(num);

		if (visit[secondLine[num]]) {
			if (secondLine[num] == start) {
				answer.addAll(s);
			}

			return;
		}

		solution(start, secondLine[num], visit, s);
	}
}
