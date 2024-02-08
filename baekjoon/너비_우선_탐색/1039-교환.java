import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		char[] input = st.nextToken().toCharArray();
		int k = Integer.parseInt(st.nextToken());

		int m = input.length;
		int[] number = new int[m];
		for (int i = 0; i < m; i++) {
			number[i] = input[i] - '0';
		}

		if (m == 1 || (m == 2 && number[m - 1] == 0)) {
			System.out.println(-1);
			return;
		}

		System.out.println(solution(number, 0, k));
	}

	private static int solution(int[] number, int idx, int k) {
		int m = number.length;

		if (k == 0) {
			return arr2Int(number, m);
		}

		if (m == idx) {
			for (int i = 0; i < m - 1; i++) {
				for (int j = i + 1; j < m; j++) {
					if (number[i] == number[j]) {
						return arr2Int(number, m);
					}
				}
			}

			if (k % 2 == 1) {
				int temp = number[m - 2];
				number[m - 2] = number[m - 1];
				number[m - 1] = temp;
			}
			return arr2Int(number, m);
		}

		int max = 0;
		for (int i = idx + 1; i < m; i++) {
			if (number[idx] < number[i]) {
				max = Math.max(max, number[i]);
			}
		}

		int ret = 0;
		if (max != 0) {
			for (int i = idx + 1; i < m; i++) {
				if (number[i] == max) {
					int temp = number[i];
					number[i] = number[idx];
					number[idx] = temp;

					ret = Math.max(ret, solution(number, idx + 1, k - 1));

					number[idx] = number[i];
					number[i] = temp;
				}
			}
		} else {
			ret = Math.max(ret, solution(number, idx + 1, k));
		}

		return ret;
	}

	private static int arr2Int(int[] arr, int m) {
		int number = 0;
		for (int i = 0; i < m; i++) {
			number += arr[i] * (int) Math.pow(10, m - i - 1);
		}
		return number;
	}
}
