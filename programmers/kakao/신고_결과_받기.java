import java.util.*;

/**
 * LEVEL-1
 * 2022 KAKAO BLIND RECRUITMENT > 신고 결과 받기
 * https://programmers.co.kr/learn/courses/30/lessons/92334
 */
public class Solution {

    private Map<String, Report> user = new HashMap<>();

    private class Report {
        int receiveMail = 0;
        Set<String> reporter = new HashSet<>();

        public void sendMail(int k) {
            if (reporter.size() >= k) {
                for (String id : reporter) {
                    user.get(id).receiveMail();
                }
            }
        }

        public void receiveMail() {
            this.receiveMail++;
        }
    }

    public int[] solution(String[] id_list, String[] report, int k) {

        for (String id : id_list) {
            user.put(id, new Report());
        }

        for (String rep : report) {
            String[] r = rep.split(" ");
            user.get(r[1]).reporter.add(r[0]);
        }

        for (Report r : user.values()) {
            r.sendMail(k);
        }
        
        return Arrays.stream(id_list).mapToInt(id -> user.get(id).receiveMail).toArray();
    }
}