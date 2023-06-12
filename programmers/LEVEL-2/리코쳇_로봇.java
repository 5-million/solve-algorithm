import java.util.*;

class Solution {
    
    private int n, m; // n * m;
    private char[][] map;
    
    public int solution(String[] board) {
        n = board.length;
        m = board[0].length();
        map = new char[n][m];
        
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = board[i].charAt(j);
                
                if (map[i][j] == 'R') {
                    queue.add(new int[]{i, j});
                    visit[i][j] = true;
                }
            }
        }
        
        int move = 0;
        int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        while (!queue.isEmpty()) {
            int qSize = queue.size();
            move++;
            while (qSize-- > 0) {
                int[] cur = queue.poll();
                
                for (int[] d : dir) {
                    int[] next = getNext(cur, d);
                    int ny = next[0];
                    int nx = next[1];
                    
                    if (checkRange(ny, nx) && !visit[ny][nx]) {
                        if (map[ny][nx] == 'G') {
                            return move;
                        }
                        
                        visit[ny][nx] = true;
                        queue.add(new int[]{ny, nx});
                    }
                }
            }
        }
        
        return -1;
    }
    
    private int[] getNext(int[] pos, int[] d) {
        int[] cur = new int[]{pos[0], pos[1]};
        while (true) {
            int ny = cur[0] + d[0];
            int nx = cur[1] + d[1];
            
            if (!checkRange(ny, nx) || map[ny][nx] == 'D') {
                return cur;
            }
            
            cur[0] = ny;
            cur[1] = nx;
        }
    }
    
    private boolean checkRange(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
}