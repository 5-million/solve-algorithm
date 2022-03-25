import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * greedy
 * 2812 - 크게 만들기(G4)
 * https://www.acmicpc.net/problem/2812
 */
public class Main {

    public static void main(String[] args) throws IOException {
        int n, k;
        String number;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        k = Integer.parseInt(s[1]);
        number = br.readLine();

        System.out.println(getMaxNumber(number, k));
    }

    private static StringBuilder getMaxNumber(String number, int k) {
        Stack<Character> stack = new Stack<>();
        for (char c : number.toCharArray()) {
            if (!stack.isEmpty() && k != 0) {
                while (!stack.isEmpty() && stack.peek() < c && 0 < k) {
                    stack.pop();
                    k--;
                }
            }

            stack.push(c);
        }

        while (k > 0) {
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse();
    }
}
