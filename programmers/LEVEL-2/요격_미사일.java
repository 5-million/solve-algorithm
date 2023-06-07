import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        for (int[] target : targets) {
            pq.add(target);
        }
        
        answer += 1;
        int[] range = pq.poll();
        while (!pq.isEmpty()) {
            int[] target = pq.poll();
            
            if (range[1] <= target[0]) {
                answer += 1;
                range = target;
            } else if (range[0] <= target[0]) {
                range[0] = target[0];
                range[1] = Math.min(range[1], target[1]);
            }
        }
        
        return answer;
    }
}