import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 모의 SW 역량테스트
 * 5658 - 보물상자 비밀번호
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRUN9KfZ8DFAUo&
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int tc = 1; tc <= t; tc++) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int unit = n / 4;

            LinkedList<String> deq = new LinkedList<>(Arrays.asList(sc.next().split("")));
            PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> o2 - o1));
            for (int i = 0; i < unit; i++) {
                String hex = "";
                for (String s : deq) {
                    hex += s;
                    if (hex.length() == unit) {
                        int num = Integer.parseInt(hex, 16);
                        if (!pq.contains(num)) pq.add(num);
                        hex = "";
                    }
                }
                deq.addFirst(deq.pollLast());
            }

            while (k-- > 1) {
                pq.poll();
            }

            System.out.printf("#%d %d\n", tc, pq.poll());
        }
    }
}