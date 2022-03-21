import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 1026 - 보물
 * https://www.acmicpc.net/problem/1026
 */
public class Main {

    private static List<Integer> A = new ArrayList<>();
    private static List<Integer> B = new ArrayList<>();
    private static int n;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(getMin());
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        A = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
        B = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).collect(Collectors.toList());
    }

    private static int getMin() {
        PriorityQueue<Integer> pqA = new PriorityQueue<>();
        PriorityQueue<Integer> pqB = new PriorityQueue<>(Comparator.reverseOrder());
        pqA.addAll(A);
        pqB.addAll(B);

        int min = 0;
        for (int i = 0; i < n; i++) {
            min += pqA.poll() * pqB.poll();
        }

        return min;
    }
}
