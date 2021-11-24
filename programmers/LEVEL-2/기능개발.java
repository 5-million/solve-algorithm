import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * LEVEL-2
 * 스택/큐 > 기능개발
 */
public class Solution {

    private class Task {
        int progress;
        int speed;
        int timeToCompletion;

        public Task(int progress, int speed) {
            this.progress = progress;
            this.speed = speed;

            timeToCompletion = (int) Math.ceil((float)(100 - progress) / speed);
        }
    }

    public int[] solution(int[] progresses, int[] speeds) {
        Stack<Integer> answer = new Stack<>();

        Queue<Task> queue = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++)
            queue.add(new Task(progresses[i], speeds[i]));

        answer.push(1);

        int ttc = queue.poll().timeToCompletion;
        while (!queue.isEmpty()) {
            Task task = queue.poll();
            if (task.timeToCompletion <= ttc) {
                answer.push(answer.pop() + 1);
            } else {
                ttc = task.timeToCompletion;
                answer.push(1);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}