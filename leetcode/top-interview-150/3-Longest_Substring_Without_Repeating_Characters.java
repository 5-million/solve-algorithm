// blog: https://velog.io/@eello/LeetCode-3.-Longest-Substring-Without-Repeating-Characters
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int len = s.length();

        if (len <= 1) {
            return len;
        }

        int[] repeat = new int[128];
        int left = 0, right = 0;
        repeat[s.charAt(left)]++;
        
        int answer = 0;
        while (true) {
            if (right == len - 1) {
                break;
            }

            if (right < len - 1 && repeat[s.charAt(right + 1)] == 0) {
                repeat[s.charAt(++right)]++;
                answer = Math.max(answer, right - left + 1);
            } else {
                repeat[s.charAt(left++)]--;
            }
        }

        return answer;
    }
}