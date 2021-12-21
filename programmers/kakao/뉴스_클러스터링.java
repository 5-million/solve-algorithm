import java.util.ArrayList;
import java.util.List;

/**
 * LEVEL-2
 * 2018 KAKAO BLIND RECRUITMENT > [1차] 뉴스 클러스터링
 */
public class Solution {

    public int solution(String str1, String str2) {
        List<String> set1 = makeSet(str1);
        List<String> set2 = makeSet(str2);

        if (set1.isEmpty() && set2.isEmpty()) return 65536;

        double kyo = 0, hap = 0;
        for (String s1 : set1) {
            for (String s2 : set2) {
                if (s2.equals(s1)) {
                    set2.remove(s1);
                    kyo++;
                    break;
                }
            }
            hap++;
        }
        hap += set2.size();
        return (int) Math.floor(kyo / hap * 65536);
    }

    private List<String> makeSet(String str) {
        List<String> set = new ArrayList<>();
        str = str.toLowerCase();
        for (int i = 0; i < str.length() - 1; i++) {
            char ch1 = str.charAt(i);
            char ch2 = str.charAt(i + 1);

            if (!check(ch1) || !check(ch2)) continue;
            set.add(ch1 + String.valueOf(ch2));
        }

        return set;
    }

    private boolean check(char ch) {
        return 96 < ch && ch < 123;
    }
}
