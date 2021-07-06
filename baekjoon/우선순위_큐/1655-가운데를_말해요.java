import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1655 가운데를 말해요
 */
public class Main {
    static int n;
    static PriorityQueue<Integer> leftHeap = new PriorityQueue<>(Comparator.reverseOrder());
    static PriorityQueue<Integer> rightHeap = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int inputNumber = Integer.parseInt(br.readLine());

            if (leftHeap.size() == rightHeap.size()) leftHeap.add(inputNumber);
            else rightHeap.add(inputNumber);

            if (!leftHeap.isEmpty() && !rightHeap.isEmpty() && leftHeap.peek() > rightHeap.peek()) {
                int maxValue = leftHeap.poll();
                int minValue = rightHeap.poll();

                leftHeap.add(minValue);
                rightHeap.add(maxValue);
            }

            sb.append(leftHeap.peek() + "\n");
        }
        System.out.println(sb);
    }
}