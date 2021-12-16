import java.util.PriorityQueue;

/**
 * LEVEL-3
 * 힙 > 이중우선순위
 */
public class Solution {

    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (String operation : operations) {
            String[] split = operation.split(" ");
            if (split[0].equals("I")) {
                int number = Integer.parseInt(split[1]);
                minHeap.add(number);
                maxHeap.add(number);
            } else {
                if (split[1].equals("1")) {
                    if (!maxHeap.isEmpty()) {
                        int max = maxHeap.poll();
                        minHeap.remove(max);
                    }
                } else {
                    if (!minHeap.isEmpty()) {
                        int min = minHeap.poll();
                        maxHeap.remove(min);
                    }
                }
            }
        }

        if (maxHeap.isEmpty()) return new int[]{0, 0};
        else return new int[]{maxHeap.poll(), minHeap.poll()};
    }
}
