import java.util.*;
import java.io.*;


public class Main {

    private static long b;
    private static int[] performances;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        b = Long.parseLong(st.nextToken());

        performances = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            performances[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(performances);

        
        long min = 1, max = 2000000000;
        long answer = 0;
        while (min <= max) {
            long mid = (min + max) / 2;

            if (isUpgradeable(mid)) {
                answer = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean isUpgradeable(long minPerformance) {
        long budget = b;
        for (int performance : performances) {
            if (performance >= minPerformance) {
                break;
            }
            
            long diff = minPerformance - performance;
            budget -= (diff * diff);
            if (budget < 0) {
                return false;
            }
        }

        return true;
    }
}