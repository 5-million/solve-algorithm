// blog: https://velog.io/@eello/LeetCode-383.-Ransom-Note
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        char[] letters = new char[26];
        for (char ch : magazine.toCharArray()) {
            letters[ch - 'a']++;
        }

        for (char ch : ransomNote.toCharArray()) {
            int idx = ch - 'a';
            if (letters[idx] == 0) {
                return false;
            }

            letters[idx]--;
        }

        return true;
    }
}