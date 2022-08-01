import java.util.Scanner;
import java.util.Stack;

/**
 * D4
 * 5432 - 쇠막대기 자르기
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWVl47b6DGMDFAXm
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int tc = 1; tc <= t; tc++) {
            String input = sc.next();
            input = input.replaceAll("\\(\\)", "L");

            Stack<Character> stack = new Stack<>();
            int cnt = 0;
            for (char ch : input.toCharArray()) {
                if (ch == '(') stack.add(ch);
                else if (ch == 'L') cnt += stack.size();
                else if (ch == ')') {
                    cnt++;
                    stack.pop();
                }
            }

            System.out.printf("#%d %d\n", tc, cnt);
        }
    }
}