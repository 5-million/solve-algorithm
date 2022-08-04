import java.util.Scanner;
import java.util.Stack;

/**
 * D4
 * 1224 - 계산기3
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?problemLevel=4&contestProbId=AV14tDX6AFgCFAYD
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int tc = 1; tc <= 10; tc++) {
            int n = sc.nextInt();
            System.out.printf("#%d %d\n", tc, calculate(getPostfix(sc.next())));
        }
    }

    private static String getPostfix(String str) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char ch : str.toCharArray()) {
            if ('0' <= ch && ch <= '9') postfix.append(ch);
            else if (ch == ')' || ch == '+') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if ( ch == ')') stack.pop();
                else stack.push(ch);
            } else if (ch == '*') {
                while (!stack.isEmpty() && stack.peek() == '*') {
                    postfix.append(stack.pop());
                }
                stack.push(ch);
            } else stack.push(ch);
        }

        while (!stack.isEmpty())
            postfix.append(stack.pop());
        
        return postfix.toString();
    }

    private static int calculate(String postfix) {
        Stack<Integer> stack = new Stack<>();
        for (char ch : postfix.toCharArray()) {
            if ('0' <= ch && ch <= '9') {
                stack.push(Integer.parseInt(String.valueOf(ch)));
            } else {
                if (ch == '+') {
                    stack.push(stack.pop() + stack.pop());
                } else {
                    stack.push(stack.pop() * stack.pop());
                }
            }
        }
        
        return stack.pop();
    }
}