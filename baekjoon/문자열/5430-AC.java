import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * G5
 * 5430 - AC
 * https://www.acmicpc.net/problem/5430
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		while (t-- > 0) {
			String process = br.readLine();
			int n = Integer.parseInt(br.readLine());
			String input = br.readLine();
			
			solution(process, n, input);
		}
	}

	private static void solution(String process, int n, String input) {
		String[] split = input.substring(1, input.length() - 1).split(",");

		boolean d = true;
		int front = 0;
		int back = n == 0 ? n : n - 1;
		int size = n;

		for (char p : process.toCharArray()) {
			if (p == 'R') {
				d = !d;
			} else {
				if (size == 0) {
					System.out.println("error");
					return;
				}

				size--;
				if (d) {
					// 정방향
					front++;
				} else {
					// 역방향
					back--;
				}
			}
		}

		List<String> result = new ArrayList<>();
		for (int i = front; i <= back; i++) {
			result.add(split[i]);
		}

		if (!d) {
			Collections.reverse(result);
		}

		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < result.size(); i++) {
			if (i == 0) {
				sb.append(result.get(i));
			} else {
				sb.append("," + result.get(i));
			}
		}
		sb.append("]");
		
		System.out.println(sb);
	}
}
