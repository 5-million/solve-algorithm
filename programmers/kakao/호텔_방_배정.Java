import java.util.HashMap;
import java.util.Map;

/**
 * LEVEL-4
 * 2019 카카오 개발자 겨울 인턴십 > 호텔 방 배정
 * https://programmers.co.kr/learn/courses/30/lessons/64063
 */
public class Solution {

    Map<Long, Long> parent = new HashMap<>();

    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        for (int i = 0; i < room_number.length; i++) {
            answer[i] = findEmptyRoom(room_number[i]);
        }
        return answer;
    }

    private long findEmptyRoom(long want) {
        if (!parent.containsKey(want)) {
            parent.put(want, want + 1);
            return want;
        }

        long next = parent.get(want);
        long emptyRoom = findEmptyRoom(next);
        parent.replace(want, emptyRoom);
        return emptyRoom;
    }
}
