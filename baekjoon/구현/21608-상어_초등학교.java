import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * G5
 * 21608 - 상어 초등학교
 * https://www.acmicpc.net/problem/21608
 */
public class Main {

    private static final int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    private static class EmptySeat implements Comparable<EmptySeat> {
        int y;
        int x;
        int bestFriendCnt;
        int emptySeatCnt;

        public EmptySeat(int y, int x, int bestFriendCnt, int emptySeatCnt) {
            this.y = y;
            this.x = x;
            this.bestFriendCnt = bestFriendCnt;
            this.emptySeatCnt = emptySeatCnt;
        }

        @Override
        public int compareTo(EmptySeat o) {
            if (bestFriendCnt != o.bestFriendCnt) {
                return o.bestFriendCnt - bestFriendCnt;
            } else if (emptySeatCnt != o.emptySeatCnt) {
                return o.emptySeatCnt - emptySeatCnt;
            } else if (y != o.y) {
                return y - o.y;
            } else return x - o.x;
        }
    }

    private static int n;
    private static int[][] classroom;
    private static List<Integer>[] bfList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        classroom = new int[n][n];
        int student = n * n;
        bfList = new List[student + 1];
        for (int i = 0; i <= student; i++) {
            bfList[i] = new ArrayList<>();
        }

        int[] sequence = new int[student];
        for (int i = 0; i < student; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int stu = Integer.parseInt(st.nextToken());
            sequence[i] = stu;
            while (st.hasMoreTokens()) {
                bfList[stu].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int stu :sequence){
            placeSeat(stu);
        }

        int satisfaction = 0;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                satisfaction += getSatisfaction(y, x);
            }
        }
        System.out.println(satisfaction);
    }

    private static void placeSeat(int student) {
        PriorityQueue<EmptySeat> pq = new PriorityQueue<>();

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (classroom[y][x] != 0)
                    continue;

                int bfCnt = 0, emptyCnt = 0;
                for (int[] d : dir) {
                    int ny = y + d[0];
                    int nx = x + d[1];

                    if (checkRange(ny, nx)) {
                        if (classroom[ny][nx] == 0) {
                            emptyCnt++;
                        } else if (bfList[student].contains(classroom[ny][nx])) {
                            bfCnt++;
                        }
                    }
                }
                pq.add(new EmptySeat(y, x, bfCnt, emptyCnt));
            }
        }

        EmptySeat emptySeat = pq.poll();
        classroom[emptySeat.y][emptySeat.x] = student;
    }

    private static boolean checkRange(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }

    private static int getSatisfaction(int y, int x) {
        int student = classroom[y][x];
        int bfCnt = 0;
        for (int[] d : dir) {
            int ny = y + d[0];
            int nx = x + d[1];

            if (checkRange(ny, nx) && bfList[student].contains(classroom[ny][nx])) {
                bfCnt++;
            }
        }

        return bfCnt == 0 ? 0 : (int) Math.pow(10, bfCnt - 1);
    }
}