package com.programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * LEVEL-2
 * 2018 KAKAO BLIND RECRUITMENT > 방금그곡
 */
public class Solution {

    private class Music implements Comparable<Music>{
        int startMinute;
        int endMinute;
        int playTime;
        String title;
        List<String> melody = new ArrayList<>();

        public Music(int startMinute, int endMinute, String title, String melody) {
            this.startMinute = startMinute;
            this.endMinute = endMinute;
            this.title = title;

            this.playTime = endMinute - startMinute;
            makeMelodyByPlayTime(melody);
        }

        private void makeMelodyByPlayTime(String melody) {
            List<String> melodyList = melodyStringToList(melody);

            for (int i = 0; i < playTime; i++)
                this.melody.add(melodyList.get(i % melodyList.size()));
        }

        @Override
        public int compareTo(Music o) {
            return o.playTime - this.playTime;
        }
    }

    List<Music> musicList = new ArrayList<>();

    public String solution(String m, String[] musicinfos) {
        makeMusicList(musicinfos);

        Collections.sort(musicList);
        List<String> inputMelody = melodyStringToList(m);

        for (Music music : musicList) {
            for (int i = 0; i <= music.melody.size() - inputMelody.size(); i++) {
                if (music.melody.subList(i, i + inputMelody.size()).equals(inputMelody))
                    return music.title;
            }
        }

        return "(None)";
    }

    private void makeMusicList(String[] musicinfos) {
        for (String musicinfo : musicinfos) {
            String[] split = musicinfo.split(",");
            musicList.add(
                    new Music(
                            convertToMinute(split[0]),
                            convertToMinute(split[1]),
                            split[2],
                            split[3]
                    )
            );
        }
    }

    private List<String> melodyStringToList(String melody) {
        List<String> melodyList = new ArrayList<>();
        for (int i = 0; i < melody.length(); i++) {
            if (melody.charAt(i) == '#') {
                String s = melodyList.get(melodyList.size() - 1);
                s += "#";
                melodyList.set(melodyList.size() - 1, s);
            } else
                melodyList.add(Character.toString(melody.charAt(i)));
        }

        return new ArrayList<>(melodyList);
    }

    private int convertToMinute(String time) {
        String[] split = time.split(":");
        int minute = Integer.parseInt(split[0]) * 60;
        minute += Integer.parseInt(split[1]);

        return minute;
    }
}