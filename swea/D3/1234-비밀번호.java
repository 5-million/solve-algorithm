import java.util.Scanner;
import java.util.Stack;

/**
 * D3
 * 1234 - 비밀번호
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV14_DEKAJcCFAYD
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int tc = 1; tc <= 10; tc++) {
            int len = sc.nextInt();
            String encryptedPassword = sc.next();

            System.out.printf("#%d %s\n", tc, getPassword(encryptedPassword));
        }
    }

    private static String getPassword(String encryptedPassword) {
        Stack<Character> stack = new Stack<>();
        for (char ch : encryptedPassword.toCharArray()) {
            if (stack.isEmpty() || stack.peek() != ch) {
                stack.push(ch);
            } else if (stack.peek() == ch) stack.pop();
        }

        StringBuilder password = new StringBuilder();
        while (!stack.isEmpty()) {
            password.append(stack.pop());
        }

        return password.reverse().toString();
    }
}

