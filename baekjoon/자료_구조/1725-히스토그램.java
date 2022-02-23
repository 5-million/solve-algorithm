import java.util.Scanner;
import java.util.Stack;

/**
 * 자료구조 | 세그먼트 트리 | 분할 정복 | 스택 > 히스토그램 
 */
public class Boj1725 {

    private static int n;
    private static int[] histogram;

    public static void main(String[] args) {
        init();
        System.out.println(solution());
    }

    private static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        histogram = new int[n + 2];

        for (int i = 1; i <= n; i++)
            histogram[i] = sc.nextInt();
    }

    private static int solution() {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (int i = 1; i < n + 2; i++) {
            while (!stack.isEmpty() && histogram[stack.peek()] > histogram[i]) {
                int height = histogram[stack.peek()];
                stack.pop();
                int width = i - stack.peek() - 1;
                answer = Math.max(answer, height * width);
            }

            stack.push(i);
        }

        return answer;
    }
}
