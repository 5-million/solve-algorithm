/**
 * LEVEL-2
 * 연습문제 > 최댓값과 최솟값
 */
public class Solution {

    public String solution(String s) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (String str : s.split(" ")) {
            int number = Integer.parseInt(str);
            max = Math.max(max, number);
            min = Math.min(min, number);
        }

        return String.format("%d %d", min, max);
    }
}