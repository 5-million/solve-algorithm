import java.util.Scanner;
import java.util.Stack;

/**
 * 6549 - 히스토그램에서 가장 큰 직사각형
 * https://www.acmicpc.net/problem/6549
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) break;

            int[] histogram = new int[n+2];
            for (int i = 1; i <= n; i++)
                histogram[i] = sc.nextInt();

            System.out.println(getMaxArea(n, histogram));
        }
    }

    private static long getMaxArea(int n, int[] histogram) {
        long maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (int i = 1; i < n + 2; i++) {
            while (!stack.isEmpty() && histogram[stack.peek()] > histogram[i]) {
                int h = histogram[stack.pop()];
                int w = i - stack.peek() - 1;

                maxArea = Math.max(maxArea, (long) h * w);
            }

            stack.add(i);
        }

        return maxArea;
    }
}

