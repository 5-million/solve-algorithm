import java.util.*;

class Solution {
    
    private int n, m;
    private int[][] map;
    
    public int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        map = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maps[i].charAt(j) == 'X') {
                    map[i][j] = 0;
                } else map[i][j] = maps[i].charAt(j) - '0';
            }
        }
        
        List<Integer> result = new ArrayList<>();
        boolean[][] visit = new boolean[n][m];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                if (map[y][x] != 0 && !visit[y][x]) {
                    result.add(bfs(new int[]{y, x}, visit));
                }
            }
        }
        
        result.sort((a, b) -> a - b);
        
        if (result.size() == 0) {
            return new int[]{-1};
        }
        
        int[] answer = new int[result.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
    
    private int bfs(int[] start, boolean[][] visit) {
        Queue<int[]> q = new ArrayDeque<>();
        
        q.add(start);
        visit[start[0]][start[1]] = true;
        
        int day = 0;
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            day += map[cur[0]][cur[1]];
            
            for (int[] d : dir) {
                int ny = cur[0] + d[0];
                int nx = cur[1] + d[1];
                
                if (checkRange(ny, nx) && !visit[ny][nx] && map[ny][nx] > 0) {
                    visit[ny][nx] = true;
                    q.add(new int[]{ny, nx});
                }
            }
        }
        
        return day;
    }
    
    private boolean checkRange(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}