// blog: https://velog.io/@eello/LeetCode-125.-Valid-Palindrome
class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (!isAlphanumeric(s.charAt(left))) {
                left++;
            } else if (!isAlphanumeric(s.charAt(right))) {
                right--;
            } else if (s.charAt(left) != s.charAt(right)) {
                return false;
            } else {
                left++;
                right--;
            }
        }

        return true;
    }

    private boolean isAlphanumeric(char ch) {
        return ('0' <= ch && ch <= '9') || ('a' <= ch && ch <= 'z');
    }
}