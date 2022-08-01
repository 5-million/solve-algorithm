import java.util.Scanner;
import java.util.Stack;

/**
 * D4
 * 1218 - 괄호 짝짓기
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14eWb6AAkCFAYD
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int tc = 1; tc <= 10; tc++) {
            int len = sc.nextInt();
            char[] input = sc.next().toCharArray();

            Stack<Character> stack = new Stack<>();
            boolean valid = true;
            for (char ch : input) {
                if (ch == '(' || ch == '[' || ch == '<' || ch == '{') {
                    stack.add(ch);
                } else {
                    if (stack.isEmpty()) valid = false;
                    else if (ch == ')' && stack.peek() != '(') valid = false;
                    else if (ch == ']' && stack.peek() != '[') valid = false;
                    else if (ch == '>' && stack.peek() != '<') valid = false;
                    else if (ch == '}' && stack.peek() != '{') valid = false;
                    else {
                        stack.pop();
                    }
                }

                if (!valid) break;
            }

            if (valid && stack.isEmpty()) {
                System.out.printf("#%d %d\n", tc,  1);
            } else System.out.printf("#%d %d\n", tc, 0);
        }
    }
}