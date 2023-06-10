import java.util.*;

public class Solution {

	private class Task {
		String name;
		int start;
		int remainTime;

		public Task(String[] plan) {
			this.name = plan[0];
			String[] split = plan[1].split(":");
			this.start = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
			this.remainTime = Integer.parseInt(plan[2]);
		}

		public void progress() {
			this.remainTime--;
		}

		public boolean isComplete() {
			return remainTime == 0;
		}
	}

	public String[] solution(String[][] plans) {
		int planCnt = plans.length;
		String[] answer = new String[planCnt];

        // 정렬과 동시에 시작하기 전의 과제 목록에 추가
		PriorityQueue<Task> tasks = new PriorityQueue<>(Comparator.comparingInt(t -> t.start));
		for (String[] plan : plans) {
			tasks.add(new Task(plan));
		}

		Stack<Task> stack = new Stack<>();

		int time = tasks.peek().start;
		int completeCnt = 0;
		while (completeCnt != planCnt) {
			if (!stack.isEmpty()) { // 진행 중인 과제가 있으면
				stack.peek().progress(); // 과제 진행

				if (stack.peek().isComplete()) { // 진행 중인 과제가 완료되었으면
					Task complete = stack.pop(); // 과제 제거
					answer[completeCnt++] = complete.name; // 완료된 과제 +1
				}
			}

			if (!tasks.isEmpty() && tasks.peek().start == time) { // 시작하지 않은 과제가 있고 과제 시작시간이 되면
				stack.add(tasks.poll()); // 진행 중인 과제 목록에 추가
			}
			
			time++;
		}
		
		return answer;
	}
}