class Solution {
    
    public int solution(int storey) {
        return dfs(storey, 0);
    }
    
    private int dfs(int storey, int click) {
        if (storey == 0) {
            return click;
        }
        
        int ret = Integer.MAX_VALUE;
        int temp = storey % 10;
        if (temp > 5) {
            temp = 10 - temp;
            storey += temp;
            storey /= 10;
            return dfs(storey, click + temp);
        } else if (temp < 5) {
            storey -= temp;
            storey /= 10;
            return dfs(storey, click + temp);
        } else {
            return Math.min(
                dfs((storey + temp) / 10, click + 5),
                dfs((storey - temp) / 10, click + 5)
            );
        }
    }
}