import java.util.*;

class Solution {
    
    private final int MAP_SIZE = 50;
    
    private boolean[][] map = new boolean[MAP_SIZE * 2 + 2][MAP_SIZE * 2 + 2];
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        init(rectangle);
        
        characterX *= 2;
        characterY *= 2;
        itemY *= 2;
        itemX *= 2;
        
        int ans = 0;
        int[][] dir = {{-1, 0}, {0, 1}, {1 ,0}, {0, -1}};
        
        Queue<int[]> que = new ArrayDeque<>();        
        que.add(new int[] {characterY, characterX, 0});
        
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            
            if (cur[0] == itemY && cur[1] == itemX) {
                ans = cur[2] / 2;
                break;
            }
            
            for (int d = 0; d < 4; d++) {
                int ny = cur[0] + dir[d][0];
                int nx = cur[1] + dir[d][1];
                
                if (map[ny][nx]) {
                    que.add(new int[] {ny, nx, cur[2] + 1});
                    map[ny][nx] = false;
                }
            }
        }
        
        return ans;        
    }
    
    private void init(int[][] rectangle) {
        for (int[] rec : rectangle) {
            markRectangleBorder(rec);
        }
        
        for (int[] rec : rectangle) {
            unmarkInsideRectangle(rec);
        }
    }
    
    private void markRectangleBorder(int[] rectangle) {
        int lx = rectangle[0] * 2;
        int ly = rectangle[1] * 2;
        int rx = rectangle[2] * 2;
        int ry = rectangle[3] * 2;
        
        for (int x = lx; x <= rx; x++) {
            map[ly][x] = true;
            map[ry][x] = true;
        }
        
        for (int y = ly; y <= ry; y++) {
            map[y][lx] = true;
            map[y][rx] = true;
        }
    }
    
    private void unmarkInsideRectangle(int[] rectangle) {
        int lx = rectangle[0] * 2;
        int ly = rectangle[1] * 2;
        int rx = rectangle[2] * 2;
        int ry = rectangle[3] * 2;
        
        for (int y = ly + 1; y < ry; y++) {
            for (int x = lx + 1; x < rx; x++) {
                map[y][x] = false;
            }
        }
    }
}