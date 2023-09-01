// blog: https://velog.io/@eello/LeetCode-150.-Evaluate-Reverse-Polish-Notation
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (!isOperator(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                stack.push(calculate(token, stack.pop(), stack.pop()));
            }
        }

        return stack.pop();
    }

    private boolean isOperator(String token) {
        return "+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token);
    }

    private int calculate(String operator, int num2, int num1) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            default:
                return num1 / num2;
        }
    }
}