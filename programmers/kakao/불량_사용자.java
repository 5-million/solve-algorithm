import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * LEVEL-3
 * 2019 카카오 개발자 겨울 인턴십 > 불량 사용자
 * https://programmers.co.kr/learn/courses/30/lessons/64064
 */
class Solution {
    
    private Set<Integer> combi = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        List<Integer>[] candidate = new List[banned_id.length];

        for (int i = 0; i < banned_id.length; i++) {
            candidate[i] = new ArrayList<>();
            String bannedId = banned_id[i];
            for (int j = 0; j < user_id.length; j++) {
                String userId = user_id[j];

                if (isMatched(userId, bannedId))
                    candidate[i].add(j);
            }
        }

        combination(candidate, 0, 0);
        return combi.size();
    }

    private boolean isMatched(String userId, String bannedId) {
        if (bannedId.length() != userId.length()) return false;
        for (int k = 0; k < bannedId.length(); k++) {
            if (bannedId.charAt(k) != '*' && bannedId.charAt(k) != userId.charAt(k))
                return false;
        }

        return true;
    }

    private void combination(List<Integer>[] candidate, int idx, int bitmask) {
        if (idx == candidate.length) {
            combi.add(bitmask);
            return;
        }

        for (int i = 0; i < candidate[idx].size(); i++) {
            int userId = candidate[idx].get(i);
            if ((1 << userId & bitmask) == 0)
                combination(candidate, idx + 1, 1 << userId | bitmask);
        }
    }
}