import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LEVEL-2
 * 2020 카카오 인턴십 > 수식 최대화
 */
public class Solution {
    static List<List<Character>> operationPermutationList = new ArrayList<>();

    public static long solution(String expression) {
        boolean[] visited = new boolean[3];
        Arrays.fill(visited, false);
        List<Character> operators = new ArrayList<>(Arrays.asList('+', '*', '-'));
        permutation(operators, new ArrayList<>(), visited, 0);


        ArrayList<String> exp = new ArrayList<>();
        String temp = "";
        for (int index = 0; index < expression.length(); index++) {
            if (operators.contains(expression.charAt(index))) {
                exp.add(temp);
                exp.add(Character.toString(expression.charAt(index)));
                temp = "";
            } else {
                temp += expression.charAt(index);
            }

            if (index == expression.length() - 1) {
                exp.add(temp);
            }
        }

        long answer = 0;
        for (List<Character> priority : operationPermutationList) {
            answer = Math.max(answer, Math.abs(calculate(new ArrayList<>(exp), priority)));
        }

        return answer;
    }

    private static void permutation(List<Character> operators, List<Character> output, boolean[] visited, int depth) {
        if (depth == 3) {
            List<Character> temp = new ArrayList<>();
            for (char exp : output) {
                temp.add(exp);
            }

            operationPermutationList.add(temp);

            return;
        }

        for (int i = 0; i < 3; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output.add(operators.get(i));
                permutation(operators, output, visited, depth + 1);
                output.remove(output.size()-1);
                visited[i] = false;
            }
        }
    }

    private static long calculate(List<String> expression, List<Character> priority) {
        List<String> temp = new ArrayList<>();
        for (char prior : priority) {

            for (String exp : expression) {
                if (!temp.isEmpty() && temp.get(temp.size() - 1).equals(Character.toString(prior))) {
                    pop(temp);
                    long number1 = Long.parseLong(pop(temp));
                    long number2 = Long.parseLong(exp);
                    temp.add(Long.toString(operate(prior, number1, number2)));
                } else {
                    temp.add(exp);
                }
            }

            expression = new ArrayList<>(temp);
            temp.clear();
        }
        return Long.parseLong(expression.get(0));
    }

    private static String pop(List<String> list) {
        String str = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        return str;
    }

    private static Long operate(char operator, long number1, long number2) {
        if(operator == '*') return number1 * number2;
        else if(operator =='+') return number1 + number2;
        else return number1 - number2;
    }
}
