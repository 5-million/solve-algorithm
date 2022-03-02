import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * LEVEL-3
 * 탐욕법 > 단속카메라
 */
class Solution {
    
    private class Route {
        int enter;
        int exit;

        public Route(int enter, int exit) {
            this.enter = enter;
            this.exit = exit;
        }
    }

    private final PriorityQueue<Route> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.enter));

    public int solution(int[][] routes) {
        for (int[] route : routes) {
            pq.add(new Route(route[0], route[1]));
        }

        int answer = 1;
        int camera = pq.poll().exit;
        while (!pq.isEmpty()) {
            Route route = pq.poll();
            
            if (camera < route.enter) {
                answer++;
                camera = route.exit;
            } else if (route.exit < camera) {
                camera = route.exit;
            }
        }
        
        return answer;
    }
}