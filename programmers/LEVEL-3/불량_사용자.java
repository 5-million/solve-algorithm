import java.util.*;
import java.util.stream.Collectors;

/**
 * LEVEL-3
 * 2019 카카오 개발자 겨울 인턴십 > 불량 사용자
 */
public class Solution {

    private Set<String> combinations = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        int bannedLen = banned_id.length;
        List<String>[] candidates = new List[bannedLen];

        for (int i = 0; i < bannedLen; i++)
            candidates[i] = new ArrayList<>();

        for (int i = 0; i < bannedLen; i++) {
            for (String user : user_id) {
                if (isPossible(user, banned_id[i]))
                    candidates[i].add(user);
            }

            candidates[i].sort(Comparator.naturalOrder());
        }

        combination(bannedLen, 0, candidates, new Stack<>());
        return combinations.size();
    }

    private boolean isPossible(String userId, String bannedId) {
        if (userId.length() != bannedId.length())
            return false;

        char[] charsUserId = userId.toCharArray();
        char[] charsBannedId = bannedId.toCharArray();

        int match = 0;
        for (int i = 0; i < charsUserId.length; i++) {
            if (charsBannedId[i] == '*' || charsUserId[i] == charsBannedId[i])
                match++;
        }

        return match == charsUserId.length;
    }

    private void combination(int n, int index, List<String>[] candidates, Stack<String> stack) {
        if (n == index) {
            String combi = stack.stream().sorted().collect(Collectors.joining());
            combinations.add(combi);
            return;
        }

        for (String candi : candidates[index]) {
            if (!stack.contains(candi)) {
                stack.push(candi);
                combination(n, index + 1, candidates, stack);
                stack.pop();
            }
        }
    }
}
