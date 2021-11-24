import java.util.ArrayList;
import java.util.List;

/**
 * LEVEL-3
 * 2018 KAKAO BLIND RECRUITMENT > [1차] 추석 트래픽
 */
public class Solution {

    private class Task {
        int start;
        int end;

        public Task(String input) {
            String[] split = input.split(" ");
            String[] time = split[1].split(":");

            end = (int) ((Integer.parseInt(time[0]) * 3600 +
                                Integer.parseInt(time[1]) * 60
                                + Double.parseDouble(time[2])) * 1000);

            int exeTime = (int) (Double.parseDouble(split[2].substring(0, split[2].length() - 1)) * 1000);

            start = end - exeTime + 1;
        }
    }

    public int solution(String[] lines) {
        List<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            tasks.add(new Task(line));
        }

        int answer = 0;
        for (Task task : tasks) {
            answer = Math.max(answer, getTps(tasks, task.start));
            answer = Math.max(answer, getTps(tasks, task.end));
        }

        return answer;
    }

    private int getTps(List<Task> tasks, int start) {
        int end = start + 1000;
        int tps = 0;
        for (Task task : tasks) {
            if (task.start < end && task.end >= start)
                tps++;
        }

        return tps;
    }
}