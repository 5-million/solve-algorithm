import java.util.ArrayList;
import java.util.List;

/**
 * LEVEL-2
 * 위클래 챌린지 > 교점에 별 만들기
 */
public class Solution {

    private class Meet {
        long y;
        long x;

        public Meet(long y, long x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object obj) {
            if (super.equals(obj)) return true;

            if (obj instanceof Meet) {
                if (this.y == ((Meet) obj).y && this.x == ((Meet) obj).x)
                    return true;
            }

            return false;
        }

        @Override
        public String toString() {
            return String.format("[%d, %d]", this.x, this.y);
        }
    }

    private List<Meet> meets = new ArrayList<>();
    private long minY = Long.MAX_VALUE;
    private long maxY = Long.MIN_VALUE;
    private long minX = Long.MAX_VALUE;
    private long maxX = Long.MIN_VALUE;

    public String[] solution(int[][] line) {
        List<String> answer = new ArrayList<>();

        for (int i = 0; i < line.length - 1; i++)
            for (int j = i + 1; j < line.length; j++)
                findMeet(line[i], line[j]);

        for (long i = maxY; i >= minY; i--) {
            String temp = "";
            for (long j = minX; j <= maxX; j++) {
                if (meets.contains(new Meet(i, j))) temp += "*";
                else temp += ".";
            }
            answer.add(temp);
        }

        return answer.toArray(new String[0]);
    }

    private void findMeet(int[] line1, int[] line2) {
        double A = line1[0];
        double B = line1[1];
        double E = line1[2];
        double C = line2[0];
        double D = line2[1];
        double F = line2[2];

        double MOD = A * D - B * C;

        if (MOD == 0) return;

        double x = (B * F - E * D) / MOD;
        double y = (E * C - A * F) / MOD;

        long lx = (long) x;
        long ly = (long) y;
        if (x == lx && y == ly) {
            meets.add(new Meet(ly, lx));

            minY = Math.min(minY, ly);
            maxY = Math.max(maxY, ly);
            minX = Math.min(minX, lx);
            maxX = Math.max(maxX, lx);
        }
    }
}