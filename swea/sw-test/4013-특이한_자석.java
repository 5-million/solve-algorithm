import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
/**
 * 모의 SW 역량테스트
 * 4013 - 특이한 자석
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeV9sKkcoDFAVH
 */
public class Solution {
 
    private static final int CLOCK_WISE = 1;
    private static final int COUNTER_CLOCK_WISE = -1;
    private static final int TEETH_NUMBER = 8;
 
    private static class Gear {
        int scoreIdx = 0;
        int[] magnetic = new int[TEETH_NUMBER];
 
        public int getScoreMagnetics() {
            return magnetic[scoreIdx];
        }
 
        public int getLeft() {
            return magnetic[(scoreIdx - 2 + TEETH_NUMBER) % TEETH_NUMBER];
        }
 
        public int getRight() {
            return magnetic[(scoreIdx + 2) % TEETH_NUMBER];
        }
 
        public void rotate(int rotateDir) {
            if (rotateDir == CLOCK_WISE) rotateClockWise();
            else rotateCounterClockWise();
        }
        
        private void rotateClockWise() {
            scoreIdx = (scoreIdx - 1 + TEETH_NUMBER) % TEETH_NUMBER;
        }
 
        private void rotateCounterClockWise() {
            scoreIdx = (scoreIdx + 1) % TEETH_NUMBER;
        }
 
    }
 
    private static Gear[] gears;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
 
        for (int tc = 1; tc <= t; tc++) {
            int k = Integer.parseInt(br.readLine());
            gears = new Gear[4];
 
            for (int i = 0; i < gears.length; i++) {
                gears[i] = new Gear();
                StringTokenizer st = new StringTokenizer(br.readLine());
 
                for (int j = 0; j < TEETH_NUMBER; j++) {
                    gears[i].magnetic[j] = Integer.parseInt(st.nextToken());
                }
            }
 
            while (k-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int gearIdx = Integer.parseInt(st.nextToken()) - 1;
                int rotateDir = Integer.parseInt(st.nextToken());
 
                rotate(gearIdx, rotateDir);
            }
 
            System.out.printf("#%d %d\n", tc, calculateScore());
        }
    }
 
    private static void rotate(int gearIdx, int rotateDir) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] v = new boolean[gears.length];
 
        q.add(gearIdx);
        v[gearIdx] = true;
 
        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                int curGearIdx = q.poll();
 
                Gear cur = gears[curGearIdx];
                int left = cur.getLeft();
                int right = cur.getRight();
 
                if (0 <= curGearIdx - 1 && !v[curGearIdx - 1] && gears[curGearIdx - 1].getRight() != left) {
                    v[curGearIdx - 1] = true;
                    q.add(curGearIdx - 1);
                }
 
                if (curGearIdx + 1 < gears.length && !v[curGearIdx + 1] && right != gears[curGearIdx + 1].getLeft()) {
                    v[curGearIdx + 1] = true;
                    q.add(curGearIdx + 1);
                }
 
                cur.rotate(rotateDir);
            }
            rotateDir *= -1;
        }
    }
 
    private static int calculateScore() {
        int score = 0;
        for (int i = 0; i < gears.length; i++) {
            if (gears[i].getScoreMagnetics() == 1)
                score += Math.pow(2, i);
        }
        return score;
    }
}