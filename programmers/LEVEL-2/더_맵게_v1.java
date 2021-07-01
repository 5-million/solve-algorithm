import java.util.PriorityQueue;

/**
 * LEVEL-2
 * Heap > LEVEL-2 더 맵게
 */
public class Solution {
    public int solution(int[] scoville, int K) {
        int mixCount = 0;
        int newFoodScoville = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int sco : scoville) {
            priorityQueue.add(sco);
        }

        while (priorityQueue.peek() < K) {
            if (priorityQueue.size() < 2) {
                return -1;
            }

            mixCount++;

            int food1 = priorityQueue.poll();
            int food2 = priorityQueue.poll();

            newFoodScoville = food1 + (food2 * 2);
            priorityQueue.add(newFoodScoville);
        }

        return mixCount;
    }
}