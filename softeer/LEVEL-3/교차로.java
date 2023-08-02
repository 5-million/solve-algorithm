import java.util.*;
import java.io.*;


public class Main {

    private static Queue<int[]>[] road = new Queue[4];
    private static int[] passTime;
    private static int n;

    public static void main(String args[]) throws IOException {
        for (int i = 0; i < 4; i++) {
            road[i] = new ArrayDeque<>();
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        passTime = new int[n];
        Arrays.fill(passTime, -1);
        
        StringTokenizer st;
        for (int id = 0; id < n; id++) {
            st = new StringTokenizer(br.readLine());
            int arrivalTime = Integer.parseInt(st.nextToken());
            int roadNum = st.nextToken().charAt(0) - 'A';

            road[roadNum].add(new int[] {id, arrivalTime});
        }

        int time = 0;
        while (true) {
            int minTime = Integer.MAX_VALUE;
            List<Integer> passable = new ArrayList<>();
            for (int cur = 0; cur < 4; cur++) {
                if (road[cur].isEmpty()) {
                    continue;
                }

                int right = cur == 0 ? 3 : cur - 1;
                if (road[right].isEmpty() || road[right].peek()[1] > Math.max(road[cur].peek()[1], time)) {
                    minTime = Math.min(minTime, road[cur].peek()[1]);
                    passable.add(cur);
                }
            }

            if (passable.isEmpty()) {
                break;
            }

            if (time < minTime) {
                time = minTime;
            }

            for (int roadNum : passable) {
                if (road[roadNum].peek()[1] <= time) {
                    int[] car = road[roadNum].poll();
                    passTime[car[0]] = time;
                }
            }

            time++;
        }

        StringBuilder sb = new StringBuilder();
        for (int pt : passTime) {
            sb.append(pt).append("\n");
        }
        System.out.println(sb);
    }
}