import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * LEVEL-3
 * 깊이/너비 우선 탐색 > 여행경로
 */
public class Solution {

    private final List<String> routes = new ArrayList<>();

    public String[] solution(String[][] tickets) {
        int n = tickets.length;
        String[] answer = new String[n + 1];

        Stack<String> visited = new Stack();
        visited.add("ICN");
        visit("ICN", new boolean[n], tickets, visited);

        String route = routes.stream().sorted().collect(Collectors.toList()).get(0);
        for (int i = 0; i < n + 1; i++) {
            int beginIndex = i * 3;
            int endIndex = beginIndex + 3;
            answer[i] = route.substring(beginIndex, endIndex);
        }
        return answer;
    }

    private void visit(String city, boolean[] use, String[][] tickets, Stack<String> visited) {
        if (visited.size() == tickets.length + 1) {
            routes.add(String.join("", visited));
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (city.equals(tickets[i][0]) && !use[i]) {
                use[i] = true;
                visited.add(tickets[i][1]);

                visit(tickets[i][1], use, tickets, visited);

                use[i] = false;
                visited.pop();
            }
        }
    }
}
