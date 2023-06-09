class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        for (int x = 1; x < r2; x++) {
            answer += (int) getMaxY(r2, x);
            if (x < r1) {
                double r1MaxY = getMaxY(r1, x);
                answer -= (int) r1MaxY;
                
                // 점이 안쪽 원의 가장자리의 정수 쌍에 있는 경우 해당 점도 두 원 사이의 공간에 있는 점이므로
                if (r1MaxY - (int) r1MaxY == 0.0) {
                    answer += 1;
                }
            }
        }
        
        return answer * 4 + (r2 - r1 + 1) * 4;
    }
    
    private double getMaxY(int r, int x) {
        // r이 최대 100만이기 때문에 r*r은 int형의 범위를 벗어나므로 long 타입으로 캐스팅해주어야한다.
        return Math.sqrt((long) r * r - (long) x * x);
    }
}