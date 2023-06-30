import java.util.*;

class Solution {
    
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> wantCnt = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantCnt.put(want[i], number[i]);
        }
        
        for (int i = 0; i < 10; i++) {
            String item = discount[i];
            if (wantCnt.containsKey(item)) {
                wantCnt.replace(item, wantCnt.get(item) - 1);
            }
        }
        
        int answer = check(wantCnt) ? 1 : 0;
        int left = 1;
        int right = left + 9;
        while (right < discount.length) {
            String preItem = discount[left - 1];
            if (wantCnt.containsKey(preItem)) {
                wantCnt.replace(preItem, wantCnt.get(preItem) + 1);
            }
            
            String nextItem = discount[right];
            if (wantCnt.containsKey(nextItem)) {
                wantCnt.replace(nextItem, wantCnt.get(nextItem) - 1);
            }
            
            answer += check(wantCnt) ? 1 : 0;
            left++;
            right++;
        }
        
        return answer;
    }
    
    private boolean check(Map<String, Integer> wantCnt) {
        for (int val : wantCnt.values()) {
            if (val != 0) {
                return false;
            }
        }
        
        return true;
    }
}
