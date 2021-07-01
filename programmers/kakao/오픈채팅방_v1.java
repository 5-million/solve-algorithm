import java.util.ArrayList;
import java.util.HashMap;

/**
 * LEVEL-2
 * 2019 KAKAO BLIND RECRUITMENT > 오픈채팅방
 */
public class Solution {
    public String[] solution(String[] record) {
        final int OPERATION_INDEX = 0;
        final int UID_INDEX = 1;
        final int NICKNAME_INDEX = 2;

        ArrayList<String> result = new ArrayList<>();

        HashMap<String, String> user = new HashMap<>();
        for (String rec : record) {
            String[] split = rec.split(" ");
            String operation = split[OPERATION_INDEX];
            String uid = split[UID_INDEX];

            if (operation.equals("Enter")) {
                user.put(uid, split[NICKNAME_INDEX]);
            } else if (operation.equals("Change")) {
                user.replace(uid, split[NICKNAME_INDEX]);
            }
        }

        for (String rec : record) {
            String[] split = rec.split(" ");
            String operation = split[OPERATION_INDEX];
            String uid = split[UID_INDEX];
            String nickname = user.get(uid);

            if (operation.equals("Enter")) {
                result.add(nickname + "님이 들어왔습니다.");
            } else if (operation.equals("Leave")) {
                result.add(nickname + "님이 나갔습니다.");
            }
        }

        return result.toArray(new String[result.size()]);
    }
}