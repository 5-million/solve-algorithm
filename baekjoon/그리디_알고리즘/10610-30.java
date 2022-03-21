import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * greedy
 * 10610 - 30(S5)
 * https://www.acmicpc.net/problem/10610
 */
public class Main {

    public static void main(String[] args) {
        String nStr = new Scanner(System.in).next();
        if (is30Multiple(nStr)) {
            PriorityQueue<Character> pq = new PriorityQueue<>(Comparator.reverseOrder());
            for (char c : nStr.toCharArray()) {
                pq.add(c);
            }

            StringBuilder sb = new StringBuilder();
            while (!pq.isEmpty()) {
                sb.append(pq.poll());
            }
            System.out.print(sb);
        } else {
            System.out.println(-1);
        }
    }

    private static boolean is30Multiple(String str) {
        if (!str.contains("0")) return false;

        long hap = 0;
        for (String s : str.split("")) {
            hap += Integer.parseInt(s);
        }

        return hap % 3 == 0;
    }
}
