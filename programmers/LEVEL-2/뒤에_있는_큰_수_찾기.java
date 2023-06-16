import java.util.*;

class Solution {
    
    public int[] solution(int[] numbers) {
		int n = numbers.length;
		int[] answer = new int[n];
		Stack<Integer> stack = new Stack<>();

		for (int i = n - 1; i >= 0; i--) {
			int now = numbers[i];

			while (!stack.isEmpty() && stack.peek() <= now) {
				stack.pop();
			}

			if (stack.isEmpty()) {
				answer[i] = -1;
			} else {
				answer[i] = stack.peek();
			}

			stack.add(now);
		}
		
		return answer;
	}
}