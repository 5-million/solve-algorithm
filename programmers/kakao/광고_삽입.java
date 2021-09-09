/**
 * LEVEL-3
 * 2021 KAKAO BLIND RECRUITMENT > 광고 삽입
 */
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTimeAsSec = toSec(play_time);
        int advTimeAsSec = toSec(adv_time);
        int[] section = new int[playTimeAsSec + 1];

        for (String log : logs) {
            String[] split = log.split("-");
            int start = toSec(split[0]);
            int end = toSec(split[1]);

            for (int i = start; i < end; i++)
                section[i]++;
        }

        int head = 0;
        int tail = advTimeAsSec;
        int advStartAt = 0;
        long sectionHap = 0;
        long maxSectionHap = 0;

        for (int i = 0; i < advTimeAsSec; i++)
            sectionHap += section[i];

        maxSectionHap = sectionHap;
        while (tail <= playTimeAsSec) {
            sectionHap = sectionHap - section[head] + section[tail];

            if (maxSectionHap < sectionHap) {
                advStartAt = head + 1;
                maxSectionHap = sectionHap;
            }

            head++;
            tail++;
        }

        return secToTime(advStartAt);
    }

    private int toSec(String time) {
        int sec;

        String[] split = time.split(":");
        sec = Integer.parseInt(split[0]) * 3600;
        sec += Integer.parseInt(split[1]) * 60;
        sec += Integer.parseInt(split[2]);

        return sec;
    }

    private String secToTime(int sec) {
        int hh = sec / 3600;
        sec %= 3600;
        int mm = sec / 60;
        int ss = sec % 60;

        return String.format("%02d:%02d:%02d", hh, mm, ss);
    }
}