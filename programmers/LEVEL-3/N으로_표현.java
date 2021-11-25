import java.util.HashSet;
import java.util.Set;

/**
 * LEVEL-3
 * 동적계획법 > N으로 표현
 */
public class Solution {

    public int solution(int N, int number) {
        Set<Integer>[] dp = new Set[9];

        int m = 0;
        for (int i = 0; i < 8; i++) {
            dp[i] = new HashSet<>();
            
            m += Math.pow(10, i);
            dp[i].add(N * m);

            for (int j = 0; j < i; j++) {
                for (Integer n1 : dp[j]) {
                    for (Integer n2 : dp[i - j - 1]) {
                        dp[i].add(n1 * n2);
                        dp[i].add(n1 + n2);
                        dp[i].add(n1 - n2);
                        if (n2 != 0) dp[i].add(n1 / n2);
                    }
                }
            }

            if (dp[i].contains(number))
                return i + 1;
        }

        return -1;
    }
}