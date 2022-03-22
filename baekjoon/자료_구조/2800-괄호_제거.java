import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * data structure
 * 2800 - 괄호 제거(G5)
 * https://www.acmicpc.net/problem/2800
 */
public class Main {

    private static class Bracket {
        int open;
        int close;

        public Bracket(int open) {
            this.open = open;
        }

        public void setClose(int close) {
            this.close = close;
        }
    }

    private static final List<Bracket> brackets = new ArrayList<>();
    private static final List<List<Bracket>>[] comb = new List[11];

    public static void main(String[] args) throws IOException {
        String formula = new BufferedReader(new InputStreamReader(System.in)).readLine();

        Stack<Bracket> stack = new Stack<>();
        for (int i = 0; i < formula.length(); i++) {
            char c = formula.charAt(i);
            if (c == '(') stack.add(new Bracket(i));
            else if (c == ')') {
                Bracket bracket = stack.pop();
                bracket.setClose(i);
                brackets.add(bracket);
            }
        }

        for (int i = 1; i <= brackets.size(); i++) {
            comb[i] = new ArrayList<>();
            dfs(i, 0, new Stack<>());
        }

        PriorityQueue<String> ans = new PriorityQueue<>();
        for (int i = 1; i <= brackets.size(); i++) {
            for (int j = 0; j < comb[i].size(); j++) {
                char[] chars = formula.toCharArray();
                for (Bracket bracket : comb[i].get(j)) {
                    chars[bracket.open] = ' ';
                    chars[bracket.close] = ' ';
                }
                ans.add(String.valueOf(chars).replaceAll("\\s", ""));
            }
        }

        String pre = "";
        while (!ans.isEmpty()) {
            String f = ans.poll();
            if (!pre.equals(f)) {
                System.out.println(f);
                pre = f;
            }
        }
    }

    private static void dfs(int n, int idx, Stack<Bracket> stack) {
        if (stack.size() == n) {
            comb[n].add(new ArrayList<>(stack));
            return;
        }

        for (int i = idx; i < brackets.size(); i++) {
            stack.add(brackets.get(i));
            dfs(n, i + 1, stack);
            stack.pop();
        }
    }
}
