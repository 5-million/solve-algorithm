import java.util.Stack;

/**
 * LEVEL-2
 * 연습문제 > 올바른 괄호
 */
public class Solution {

    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(')
                stack.add(ch);
            else {
                if (stack.isEmpty()) return false;
                if (stack.peek() == ')') return false;
                stack.pop();
            }
        }

        if (stack.isEmpty()) return true;
        else return false;
    }
}
