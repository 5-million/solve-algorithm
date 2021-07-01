import java.util.Stack;

/**
 * LEVEL-2
 * 2017 팁스타운 > 짝지어 제거하기
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(solution("baabaa"));
    }

    public static int solution(String s)
    {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        return stack.isEmpty() ? 1 : 0;
    }
}