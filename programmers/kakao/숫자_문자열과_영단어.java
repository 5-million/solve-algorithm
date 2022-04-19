/**
 * LEVEL-1
 * 2021 카카오 채용연계형 인턴십 > 숫자 문자열과 영단어
 * https://programmers.co.kr/learn/courses/30/lessons/81301
 */
public class Solution {

    public int solution(String s) {
        String[] number = {"zero","one","two","three","four","five","six","seven","eight","nine"};
        for (int i = 0; i < 10; i++) {
            s = s.replaceAll(number[i], "" + i);
        }

        return Integer.parseInt(s);
    }
}