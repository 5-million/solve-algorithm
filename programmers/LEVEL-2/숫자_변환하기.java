import java.util.*;

class Solution {
    
    private static final int MAX = 1000000;
    
    public int solution(int x, int y, int n) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visit = new boolean[MAX + 1];
        
        q.add(x);
        visit[x] = true;
        int answer = 0;
        while(!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                int num = q.poll();
                
                if (num == y) {
                    return answer;
                }
                
                if (num + n <= MAX && !visit[num + n]) {
                    visit[num + n] = true;
                    q.add(num + n);
                }
                
                if (num * 2 <= MAX && !visit[num * 2]) {
                    visit[num * 2] = true;
                    q.add(num * 2);
                }
                
                if (num * 3 <= MAX && !visit[num * 3]) {
                    visit[num * 3] = true;
                    q.add(num * 3);
                }
            }
            
            answer++;
        }
        
        return -1;
    }
}