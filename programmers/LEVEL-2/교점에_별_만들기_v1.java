import java.util.ArrayList;
import java.util.List;

/**
 * LEVEL-2
 * 위클래 챌린지 > 교점에 별 만들기
 */
class Solution {
    private class Position {
        long y;
        long x;

        public Position(long y, long x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object obj) {
            if (super.equals(obj)) return true;

            if (obj instanceof Position)
                if (this.y == ((Position) obj).y && this.x == ((Position) obj).x)
                    return true;

            return false;
        }
    }

    // ax + by = c
    private class Line {
        int a;
        int b;
        int c;

        public Line(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = -1 * c;
        }

        public Position findMeet(Line line) {
            if (a * line.b - b * line.a == 0) return null;

            if ((c * line.a - line.c * a) % (b * line.a - line.b * a) != 0)
                return null;

            double y = (c * line.a - line.c * a) / (b * line.a - line.b * a);
            double x;

            if (a == 0) {
                if ((line.c - line.b * y) % line.a != 0) return null;
                x = (line.c - line.b * y) / line.a;
            } else {
                if ((c - b * y) % a != 0) return null;
                x = (c - b * y) / a;
            }

            if (y == (int) y && x == (int) x)
                return new Position((long) y, (long) x);
            else return null;
        }
    }

    public String[] solution(int[][] line) {
        List<String> answer = new ArrayList<>();
        List<Line> lines = new ArrayList<>();
        for (int[] l : line)
            lines.add(new Line(l[0], l[1], l[2]));

        List<Position> meets = new ArrayList<>();
        for (int i = 0; i < lines.size() - 1; i++) {
            for (int j = i + 1; j < lines.size(); j++) {
                Position meet = lines.get(i).findMeet(lines.get(j));
                if (meet != null) meets.add(meet);
            }
        }

        long minY = Integer.MAX_VALUE;
        long maxY = Integer.MIN_VALUE;
        long minX = Integer.MAX_VALUE;
        long maxX = Integer.MIN_VALUE;
        for (Position meet : meets) {
            minY = Math.min(minY, meet.y);
            maxY = Math.max(maxY, meet.y);
            minX = Math.min(minX, meet.x);
            maxX = Math.max(maxX, meet.x);
        }

        for (long i = maxY; i >= minY; i--) {
            String temp = "";
            for (long j = minX; j <= maxX; j++) {
                if (meets.contains(new Position(i, j))) temp += "*";
                else temp += ".";
            }
            answer.add(temp);
        }

        return answer.toArray(new String[0]);
    }
}