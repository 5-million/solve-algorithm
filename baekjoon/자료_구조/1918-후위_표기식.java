import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * G2
 * 1918 - 후위 표기식
 * https://www.acmicpc.net/problem/1918
 */
public class 후위_표기식_1918 {

	private static Map<Character, Integer> operation = new HashMap<>();

	public static void main(String[] args) throws IOException {
		initOperation();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		Stack<Character> stack = new Stack<>();

		String result = "";
		for (char ch : input.toCharArray()) {
			if (isAlphabet(ch)) {
				result += ch;
			} else {
				if (ch == ')') {
					while (stack.peek() != '(') {
						result += stack.pop();
					}

					stack.pop();
				} else if (ch == '(') {
					stack.push(ch);
				} else {
					while (!stack.isEmpty() && operation.get(stack.peek()) >= operation.get(ch)) {
						result += stack.pop();
					}

					stack.push(ch);
				}
			}
		}

		while (!stack.isEmpty()) {
			result += stack.pop();
		}

		System.out.println(result);
	}

	private static void initOperation() {
		operation.put('(', 0);
		operation.put('+', 1);
		operation.put('-', 1);
		operation.put('*', 2);
		operation.put('/', 2);
	}

	private static boolean isAlphabet(char ch) {
		return 'A' <= ch && ch <= 'Z';
	}
}
