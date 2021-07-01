import java.util.HashMap;

/**
 * LEVEL-2
 * 해시 > 전화번호 목록
 */
class Solution {
    public boolean solution(String[] phoneBook) {
        HashMap<String ,String> map = new HashMap<>();

        for (String number : phoneBook) {
            map.put(number, number);
        }

        for (String phoneNumber : phoneBook) {
            for (int i = 0; i < phoneNumber.length(); i++) {
                if (map.containsKey(phoneNumber.substring(0, i))) {
                    return false;
                }
            }
        }

        return true;
    }
}